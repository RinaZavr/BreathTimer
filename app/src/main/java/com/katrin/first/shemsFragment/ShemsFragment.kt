package com.katrin.first.shemsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.katrin.first.R

class ShemsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shems, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.shems_button_back).setOnClickListener {
            findNavController().popBackStack()
        }
        view.findViewById<Button>(R.id.shems_button_relax).setOnClickListener {
            findNavController().navigate(R.id.action_shemsFragment_to_relaxFragment)
        }
        view.findViewById<Button>(R.id.shems_button_balance).setOnClickListener {
            findNavController().navigate(R.id.action_shemsFragment_to_balanceFragment)
        }
        view.findViewById<Button>(R.id.shems_button_cheer).setOnClickListener {
            findNavController().navigate(R.id.action_shemsFragment_to_cheerFragment)
        }
    }
}