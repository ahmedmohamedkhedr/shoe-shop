package com.khedr.shoeshop.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.khedr.shoeshop.R
import com.khedr.shoeshop.databinding.FragmentOnBoardBinding
import com.khedr.shoeshop.utils.navigateTo
import com.khedr.shoeshop.utils.showBackButton

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_board, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextBtn.setOnClickListener {
            navigateTo(OnBoardFragmentDirections.actionOnBoardFragmentToInstructionsFragment())
        }
    }

}