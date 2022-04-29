package com.katrin.first.repeatScreen.view

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katrin.first.R
import com.katrin.first.model.BreathSession
import com.katrin.first.model.RepeatFragmentState
import com.katrin.first.repeatScreen.viewModel.IRepeatViewModel
import com.katrin.first.repeatScreen.viewModel.RepeatViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@AndroidEntryPoint
class RepeatFragment : Fragment() {
    @Inject
    lateinit var viewModel: IRepeatViewModel
    private val subs = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repeat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val params = arguments?.getSerializable("params") as BreathSession

        view.findViewById<Button>(R.id.repeat_button_back).setOnClickListener {
            findNavController().popBackStack()
        }
        view.findViewById<Button>(R.id.repeat_button_info).setOnClickListener {
            findNavController().navigate(R.id.action_repeatFragment_to_infoFragment)
        }
        view.findViewById<Button>(R.id.repeat_button_repeat).setOnClickListener {
            viewModel.restartSession()
        }
        view.findViewById<Button>(R.id.repeat_button_metronom).setOnClickListener {
            viewModel.soundOnOff()
        }

        viewModel.startSession(params)
        viewModel.state.subscribe(::updateState).addTo(subs)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onDestroy()
        subs.clear()
    }

    private fun updateState(state: RepeatFragmentState) {
        val repeatButton = view?.findViewById<Button>(R.id.repeat_button_repeat)
        val repeatMetronom = view?.findViewById<ImageView>(R.id.repeat_image_metronom)
        when(state.buttonEnable) {
            true -> repeatButton?.visibility = View.VISIBLE
            false -> repeatButton?.visibility = View.INVISIBLE
        }
        when(state.metronomValue) {
            true -> repeatMetronom?.setImageResource(R.drawable.icon_metro_dark)
            false -> repeatMetronom?.setImageResource(R.drawable.icon_metro)
        }
        val textTimer = view?.findViewById<TextView>(R.id.repeat_text_timer)
        val textFirstBreath = view?.findViewById<TextView>(R.id.repeat_timer_breath1)
        val textSecondBreath = view?.findViewById<TextView>(R.id.repeat_timer_breath2)
        val textFirstDelay = view?.findViewById<TextView>(R.id.repeat_timer_delay1)
        val textSecondDelay = view?.findViewById<TextView>(R.id.repeat_timer_delay2)
        textTimer?.text = String.format("%02d:%02d", state.time/60, state.time%60)
        textFirstBreath?.text = state.firstBreath.toString()
        textSecondBreath?.text = state.secondBreath.toString()
        textFirstDelay?.text = state.firstDelay.toString()
        textSecondDelay?.text = state.secondDelay.toString()
    }
}