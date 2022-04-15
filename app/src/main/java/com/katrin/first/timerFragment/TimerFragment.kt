package com.katrin.first.timerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katrin.first.R

class TimerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.timer_button_breath).setOnClickListener {
            findNavController().popBackStack(R.id.startFragment, false)
        }
        view.findViewById<Button>(R.id.timer_button_back).setOnClickListener {
            findNavController().popBackStack()
        }
        view.findViewById<Button>(R.id.timer_button_shema).setOnClickListener {
            findNavController().navigate(R.id.action_timerFragment_to_shemsFragment)
        }
    }
}