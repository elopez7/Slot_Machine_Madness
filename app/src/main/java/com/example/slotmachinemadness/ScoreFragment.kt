package com.example.slotmachinemadness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.slotmachinemadness.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).playerName, ScoreFragmentArgs.fromBundle(requireArguments()).winningPrize)
        viewModel = ViewModelProvider(this, viewModelFactory)[ScoreViewModel::class.java]

        viewModel.playerName.observe(viewLifecycleOwner, Observer { userName -> binding.scoreFragmentTextViewCongratulationsMessage.text = getString(R.string.score_fragment_text_view_congratulations, userName) })
        viewModel.finalWinnings.observe(viewLifecycleOwner, Observer { total -> binding.scoreFragmentTextViewTotalWinnings.text = getString(R.string.placeholder_money, total) })

        binding.scoreFragmentButtonGamble.setOnClickListener { gamble() }
        binding.scoreFragmentButtonPlayAgain.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment(viewModel.playerName.value!!)) }
        binding.scoreFragmentButtonBackToTitle.setOnClickListener { findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToTitleFragment()) }

        return binding.root
    }

    private fun gamble() {
        viewModel.gamble()
        binding.scoreFragmentButtonGamble.isVisible = false
    }
}