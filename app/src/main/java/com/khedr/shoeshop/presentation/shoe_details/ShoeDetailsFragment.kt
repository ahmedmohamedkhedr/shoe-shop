package com.khedr.shoeshop.presentation.shoe_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.khedr.shoeshop.R
import com.khedr.shoeshop.databinding.FragmentShoeDetailsBinding
import com.khedr.shoeshop.presentation.MainActivity
import com.khedr.shoeshop.presentation.shoe_listing.ShoeListingFragmentDirections
import com.khedr.shoeshop.utils.navigateTo
import com.khedr.shoeshop.utils.navigateUp
import com.khedr.shoeshop.utils.showBackButton

class ShoeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailsBinding
    private val viewModel by lazy {
        (requireActivity() as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        showBackButton()
        viewModel.onLogoutLiveData.observe(viewLifecycleOwner) {
            navigateTo(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToLoginFragment())
        }
        binding.addBtn.setOnClickListener {
            viewModel.addNewShoe()
            navigateUp()
        }
    }

}