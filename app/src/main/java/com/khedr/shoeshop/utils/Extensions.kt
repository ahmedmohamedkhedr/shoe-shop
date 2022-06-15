package com.khedr.shoeshop.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI


fun Fragment.navigateTo(navDirections: NavDirections) {
    findNavController().navigate(navDirections)
}

fun Fragment.navigateUp() {
    findNavController().navigateUp()
}

fun Fragment.showBackButton(){
    NavigationUI.setupActionBarWithNavController(
        requireActivity() as AppCompatActivity,
        findNavController()
    )
}