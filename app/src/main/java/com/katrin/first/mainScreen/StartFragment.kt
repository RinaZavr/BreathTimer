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

class StartFragment : Fragment() {
    private var timer : Int = 0
    private var breathOne : Int = 0
    private var delayOne : Int = 0
    private var breathTwo : Int = 0
    private var delayTwo : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.home_button_breath).setOnClickListener {
            val session = BreathSession(20, 5, 5, 5, 5)
            val bundle = Bundle()
            bundle.putSerializable("params", session)
            findNavController().navigate(R.id.action_startFragment_to_repeatFragment, bundle)
        }
        view.findViewById<Button>(R.id.home_button_info).setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_infoFragment)
        }
        view.findViewById<ImageView>(R.id.home_icon_breath1).setOnClickListener {
            var dialogView = layoutInflater.inflate(R.layout.dialog_number_picker, null)
            var numberPicker = dialogView.findViewById<NumberPicker>(R.id.dialog_picker)
            numberPicker.minValue = 0
            numberPicker.maxValue = 9
            numberPicker.value = breathOne
            var builder = AlertDialog.Builder(context)
            builder.setView(dialogView)
            builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    breathOne = numberPicker.value
                    view.findViewById<TextView>(R.id.home_timer_breath1).text = breathOne.toString()
                }
            })

            val dialogWindow = builder.create()
            dialogWindow.show()
        }
    }
}