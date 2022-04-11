package com.katrin.first.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katrin.first.R
import com.katrin.first.model.BreathSession

class StartFragment : Fragment() {
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
    }
}