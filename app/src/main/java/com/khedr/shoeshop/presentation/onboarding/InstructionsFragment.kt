package com.khedr.shoeshop.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.khedr.shoeshop.R
import com.khedr.shoeshop.databinding.FragmentInstructionsBinding
import com.khedr.shoeshop.utils.navigateTo
import com.khedr.shoeshop.utils.showBackButton


class InstructionsFragment : Fragment() {
    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBackButton()
        binding.finishBtn.setOnClickListener {
            navigateTo(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListingFragment())
        }
    }


}