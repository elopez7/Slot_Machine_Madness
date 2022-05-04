package com.example.slotmachinemadness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.slotmachinemadness.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        binding.titleFramentButtonPlay.setOnClickListener { processInput() }

        return binding.root
    }

    private fun processInput() {
        val playerName = binding.titleFragmentEditTextPlayerName.text
        if(playerName.isNullOrBlank()) {
            Toast.makeText(requireContext(), getString(R.string.toast_name_empty_warning), Toast.LENGTH_LONG).show()
            return
        }
        goToNextFragment()
    }

    private fun goToNextFragment() {
        findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment(binding.titleFragmentEditTextPlayerName.text.toString()))
    }
}