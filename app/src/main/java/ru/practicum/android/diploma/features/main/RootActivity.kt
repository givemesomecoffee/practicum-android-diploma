package ru.practicum.android.diploma.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.navigation.NavigationUiController
import ru.practicum.android.diploma.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity(R.layout.activity_root), NavigationUiController {

    private val binding: ActivityRootBinding by viewBinding(ActivityRootBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, _, _ ->
            hideBottomNavigation(false)
        }
    }

    override fun hideBottomNavigation(hide: Boolean) {
        binding.bottomNavigationView.isVisible = !hide
    }
}