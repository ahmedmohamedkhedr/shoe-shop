package com.khedr.shoeshop.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.khedr.shoeshop.R
import com.khedr.shoeshop.domain.models.UserModel
import com.khedr.shoeshop.presentation.shoe_listing.ShoeListingFragmentDirections
import com.khedr.shoeshop.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getCurrentUser()
        viewModel.currentUserLiveData.observe(this) {
            handleNavigation(it)
        }
    }

    private fun handleNavigation(userModel: UserModel?) {
        val graph = findNavController(R.id.navHostFragment).graph
        graph.startDestination =
            if (userModel != null) R.id.shoeListingFragment else R.id.loginFragment
        findNavController(R.id.navHostFragment).graph = graph
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            viewModel.logout()
        }
        val navController = findNavController(R.id.navHostFragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}