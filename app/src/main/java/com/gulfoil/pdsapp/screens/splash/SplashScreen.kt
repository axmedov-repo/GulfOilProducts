package com.gulfoil.pdsapp.screens.splash

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.databinding.ScreenSplashBinding
import com.gulfoil.pdsapp.utils.scope
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val isRelease: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        if (isRelease) {
            openNextScreen()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Permissions.check(requireContext(), arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS
                ), null, null,
                    object : PermissionHandler() {
                        override fun onGranted() {
                            openNextScreen()
                        }
                    }
                )
            } else {
                openNextScreen()
            }
        }
    }

    private fun openNextScreen() {
        lifecycleScope.launch {
            delay(2000)

            // Mon 20 November 2023 00:00:00
            if (Calendar.getInstance().timeInMillis >= 1704049200000) {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToTrialEndScreen())
            } else {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToProductsScreen())
            }
        }
    }
}