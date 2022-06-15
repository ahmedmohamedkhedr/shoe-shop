package com.khedr.shoeshop.presentation.shoe_listing

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.khedr.shoeshop.R
import com.khedr.shoeshop.databinding.FragmentShoeListingBinding
import com.khedr.shoeshop.domain.models.ShoeModel
import com.khedr.shoeshop.presentation.MainActivity
import com.khedr.shoeshop.utils.navigateTo

class ShoeListingFragment : Fragment() {
    private lateinit var binding: FragmentShoeListingBinding
    private val viewModel by lazy {
        (requireActivity() as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.onLogoutLiveData.observe(viewLifecycleOwner) {
            navigateTo(ShoeListingFragmentDirections.actionShoeListingFragmentToLoginFragment())
        }
        viewModel.newShoeLiveData.observe(viewLifecycleOwner) {
            addNewShoe(it)
        }

        binding.floatingActionButton.setOnClickListener {
            navigateTo(ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailsFragment())
        }
    }

    private fun addNewShoe(shoe: MutableList<ShoeModel>) {
        shoe.forEach {
            val myLayout: LinearLayout = binding.linerLayout

            val layout = LinearLayout(requireContext())
            layout.apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                orientation = LinearLayout.VERTICAL
            }

            val name = TextView(requireContext())

            name.apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = it.name ?: ""
            }

            val size = TextView(requireContext())
            size.apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = it.size ?: ""
            }

            val company = TextView(requireContext())
            company.apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = it.company ?: ""
            }

            val desc = TextView(requireContext())
            desc.apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                text = it.description ?: ""
            }

            val divider = View(requireContext())
            divider.apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1
                )

                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.line)
            }

            layout.apply {
                addView(name)
                addView(company)
                addView(size)
                addView(desc)
                addView(divider)
            }

            myLayout.addView(layout)
        }
    }
}
