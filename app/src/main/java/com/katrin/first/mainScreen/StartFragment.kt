package com.katrin.first.mainScreen

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katrin.first.R
import com.katrin.first.model.BreathSession
import com.katrin.first.reps.ISessionRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : Fragment() {
    private var timer: String = ""
    private var totalTime: Int = 0
    private var breathOne: Int = 0
    private var delayOne: Int = 0
    private var breathTwo: Int = 0
    private var delayTwo: Int = 0
    private var totalTimerText: TextView? = null
    private var breathOneText: TextView? = null
    private var delayOneText: TextView? = null
    private var breathTwoText: TextView? = null
    private var delayTwoText: TextView? = null

    @Inject
    lateinit var storage: ISessionRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val savedSession = storage.getSessionParameters()

        totalTime = savedSession.time
        breathOne = savedSession.firstBreath
        delayOne = savedSession.firstDelay
        breathTwo = savedSession.secondBreath
        delayTwo = savedSession.secondDelay

        totalTimerText = view.findViewById<TextView>(R.id.home_text_timer)
        breathOneText = view.findViewById<TextView>(R.id.home_timer_breath1)
        delayOneText = view.findViewById<TextView>(R.id.home_timer_delay1)
        breathTwoText = view.findViewById<TextView>(R.id.home_timer_breath2)
        delayTwoText = view.findViewById<TextView>(R.id.home_timer_delay2)

        totalTimerText?.text = String.format("%02d:%02d", totalTime/60, totalTime%60)
        breathOneText?.text = breathOne.toString()
        delayOneText?.text = delayOne.toString()
        breathTwoText?.text = breathTwo.toString()
        delayTwoText?.text = delayTwo.toString()

        view.findViewById<Button>(R.id.home_button_breath).setOnClickListener {
            val session = BreathSession(totalTime, breathOne, delayOne, breathTwo, delayTwo)
            storage.saveSessionParameters(session)
            val bundle = Bundle()
            bundle.putSerializable("params", session)
            findNavController().navigate(R.id.action_startFragment_to_repeatFragment, bundle)
        }
        view.findViewById<Button>(R.id.home_button_info).setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_infoFragment)
        }
        view.findViewById<ImageView>(R.id.home_icon_breath1).setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_number_picker, null)
            val numberPicker = dialogView.findViewById<NumberPicker>(R.id.dialog_picker)
            numberPicker.minValue = 0
            numberPicker.maxValue = 9
            numberPicker.value = breathOne
            val builder = AlertDialog.Builder(context)
            builder.setView(dialogView)
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    breathOne = numberPicker.value
                    breathOneText?.text = breathOne.toString()
                }
            })

            val dialogWindow = builder.create()
            dialogWindow.show()
        }

        view.findViewById<ImageView>(R.id.home_icon_delay1).setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_number_picker, null)
            val numberPicker = dialogView.findViewById<NumberPicker>(R.id.dialog_picker)
            numberPicker.minValue = 0
            numberPicker.maxValue = 9
            numberPicker.value = delayOne
            val builder = AlertDialog.Builder(context)
            builder.setView(dialogView)
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    delayOne = numberPicker.value
                    delayOneText?.text = delayOne.toString()
                }
            })

            val dialogWindow = builder.create()
            dialogWindow.show()
        }

        view.findViewById<ImageView>(R.id.home_icon_breath2).setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_number_picker, null)
            val numberPicker = dialogView.findViewById<NumberPicker>(R.id.dialog_picker)
            numberPicker.minValue = 0
            numberPicker.maxValue = 9
            numberPicker.value = breathTwo
            val builder = AlertDialog.Builder(context)
            builder.setView(dialogView)
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    breathTwo = numberPicker.value
                    breathTwoText?.text = breathTwo.toString()
                }
            })

            val dialogWindow = builder.create()
            dialogWindow.show()
        }

        view.findViewById<ImageView>(R.id.home_icon_delay2).setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_number_picker, null)
            val numberPicker = dialogView.findViewById<NumberPicker>(R.id.dialog_picker)
            numberPicker.minValue = 0
            numberPicker.maxValue = 9
            numberPicker.value = delayTwo
            val builder = AlertDialog.Builder(context)
            builder.setView(dialogView)
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    delayTwo = numberPicker.value
                    delayTwoText?.text = delayTwo.toString()
                }
            })

            val dialogWindow = builder.create()
            dialogWindow.show()
        }

        view.findViewById<Button>(R.id.home_button_timer).setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_number_twopicker, null)
            val numberPickerFirst = dialogView.findViewById<NumberPicker>(R.id.dialog_picker_first)
            val numberPickerSecond = dialogView.findViewById<NumberPicker>(R.id.dialog_picker_second)
            numberPickerFirst.minValue = 0
            numberPickerFirst.maxValue = 59
            numberPickerSecond.minValue = 0
            numberPickerSecond.maxValue = 59
            val builder = AlertDialog.Builder(context)
            builder.setView(dialogView)
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    totalTime = numberPickerFirst.value * 60 + numberPickerSecond.value
                    timer = String.format("%02d:%02d", numberPickerFirst.value, numberPickerSecond.value)
                    totalTimerText?.text = timer
                }
            })

            val dialogWindow = builder.create()
            dialogWindow.show()
        }
    }
}