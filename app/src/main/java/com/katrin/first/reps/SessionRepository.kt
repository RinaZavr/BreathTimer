package com.katrin.first.reps

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.katrin.first.model.BreathSession
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val context: Context
) : ISessionRepository {
    private val gson = Gson()
    private val storage = context.getSharedPreferences("SessionPrefs", Context.MODE_PRIVATE)

    override fun saveSessionParameters(session: BreathSession) {
        val jsonStr = gson.toJson(session)
        storage.edit()
            .putString(sessionKeyName, jsonStr)
            .apply()
    }

    override fun getSessionParameters(): BreathSession {
        val jsonStr = storage.getString(sessionKeyName, null)
        return when(jsonStr == null) {
            true -> BreathSession()
            false -> gson.fromJson(jsonStr, BreathSession::class.java)
        }
    }

    override fun saveMetronom(value: Boolean) {
        storage.edit()
            .putBoolean(metronomKeyName, value)
            .apply()
    }

    override fun getMetronom(): Boolean {
        return storage.getBoolean(metronomKeyName, false)
    }

    companion object {
        const val sessionKeyName = "sessionKey"
        const val metronomKeyName = "metronomKey"
    }

}