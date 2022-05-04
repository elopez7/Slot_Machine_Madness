package com.example.slotmachinemadness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.slotmachinemadness.databinding.FragmentGameBinding
import com.example.slotmachinemadness.databinding.FragmentSlotBinding
import kotlin.random.Random


class SlotFragment : Fragment() {

    private lateinit var binding: FragmentSlotBinding

    private val images = listOf<Int>(
        R.drawable.ic_android_head,
        R.drawable.ic_diamond,
        R.drawable.ic_bug,
        R.drawable.ic_crossed_dollar_sign,
        R.drawable.ic_dollar_sign,
        R.drawable.ic_dollar_sign_round,
        R.drawable.ic_hundred_dollar_bill,
        R.drawable.ic_musical_note
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_slot, container, false)
        setSlotImage()
        return binding.root
    }

    fun setSlotImage() : Int {
        val imageIndex = Random.nextInt(images.size)
        binding.slotFragmentImageViewIcon.setImageResource(images[imageIndex])
        return images[imageIndex]
    }

}