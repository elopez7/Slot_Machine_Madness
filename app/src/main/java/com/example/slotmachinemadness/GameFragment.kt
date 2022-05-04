package com.example.slotmachinemadness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.slotmachinemadness.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var viewModelFactory: GameViewModelFactory

    private lateinit var gameSlot1: SlotFragment
    private lateinit var gameSlot2: SlotFragment
    private lateinit var gameSlot3: SlotFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModelFactory = GameViewModelFactory(GameFragmentArgs.fromBundle(requireArguments()).playerName)
        viewModel = ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]

        //initializeSlotFragments()
        setUpObservers()
        setOnClickListeners()

        return binding.root
    }

    private fun initializeSlotFragments() {
        gameSlot1 = binding.gameFragmentSlot1.getFragment<SlotFragment>()
        gameSlot2 = binding.gameFragmentSlot2.getFragment<SlotFragment>()
        gameSlot3 = binding.gameFragmentSlot3.getFragment<SlotFragment>()
    }

    private fun setUpObservers() {
        viewModel.userName.observe(viewLifecycleOwner, Observer { userName -> binding.gameFragmentTextViewPlayerName.text = userName.toString() })
        viewModel.totalMoney.observe(viewLifecycleOwner, Observer { totalMoney -> binding.gameFragmentTextViewTotalMoney.text = totalMoney.toString() })
        viewModel.totalWinnings.observe(viewLifecycleOwner, Observer { winnings -> binding.gameFragmentTextViewTotalWinnings.text = winnings.toString() })
        viewModel.spinsLeft.observe(viewLifecycleOwner, Observer { spins -> binding.gameFragmentTextViewSpinsLeft.text = spins.toString() })
    }

    private fun setOnClickListeners() {
        binding.gameFragmentButtonSpin.setOnClickListener { handleSpin() }
        binding.gameFragmentButtonBet.setOnClickListener { betMoney() }
        binding.gameFragmentButtonEnd.setOnClickListener { endGame() }
    }

    private fun betMoney() {
        val totalMoney = viewModel.totalMoney.value!!
        if(totalMoney > 0){
            viewModel.increaseSpins()
            return
        }
        Toast.makeText(requireContext(), R.string.insufficient_funds_warning, Toast.LENGTH_SHORT).show()
    }

    private fun handleSpin() {
        val spinsLeft = viewModel.spinsLeft.value!!
        if(spinsLeft > 0) {
            spinWheel()
            return
        }
        Toast.makeText(requireContext(), R.string.no_spins_left_text, Toast.LENGTH_SHORT).show()
    }

    private fun spinWheel() {
        viewModel.decreaseSpins()
        handleResults()
    }

    private fun handleResults() {
        val result1 = binding.gameFragmentSlot1.getFragment<SlotFragment>().setSlotImage()
        val result2 = binding.gameFragmentSlot2.getFragment<SlotFragment>().setSlotImage()
        val result3 = binding.gameFragmentSlot3.getFragment<SlotFragment>().setSlotImage()
        viewModel.compareResults(result1, result2, result3)
    }

    private fun endGame() {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(binding.gameFragmentTextViewPlayerName.text.toString(), viewModel.totalMoney.value!!))
    }
}