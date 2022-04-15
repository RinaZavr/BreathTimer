package com.katrin.first.infoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katrin.first.R

class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.info_button_back).setOnClickListener {
            findNavController().popBackStack()
        }
        view.findViewById<Button>(R.id.info_button_shema).setOnClickListener {
            findNavController().navigate(R.id.action_infoFragment_to_shemsFragment)
        }
        view.findViewById<Button>(R.id.info_button_about_timer).setOnClickListener {
            findNavController().navigate(R.id.action_infoFragment_to_timerFragment)
        }
        view.findViewById<Button>(R.id.info_button_recomend).setOnClickListener {
            findNavController().navigate(R.id.action_infoFragment_to_recomendFragment)
        }
        view.findViewById<Button>(R.id.info_button_about_author).setOnClickListener {
            findNavController().navigate(R.id.action_infoFragment_to_authorFragment)
        }
    }
}