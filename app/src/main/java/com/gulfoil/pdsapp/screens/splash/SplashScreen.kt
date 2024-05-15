package com.gulfoil.pdsapp.screens.splash

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.app.App
import com.gulfoil.pdsapp.databinding.ScreenSplashBinding
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.showMessageOnTopOfScreen
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        if (App.IS_RELEASE) {
            goToMain()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Permissions.check(requireContext(), arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS
                ), null, null,
                    object : PermissionHandler() {
                        override fun onGranted() {
                            goToMain()
                        }
                    }
                )
            } else {
                goToMain()
            }
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun goToMain() {
        if (App.IS_DIFFIE_HELLMAN_ALGO_ENABLED) {
            viewModel.apply {
                exchangeEncryptionDetails()
                progressLiveData.observe(viewLifecycleOwner) {

                }
                errorLiveData.observe(viewLifecycleOwner) {
                    showMessageOnTopOfScreen(it)
                }
                successLiveData.observe(this@SplashScreen, successObserver)
            }
        } else {
            openNextScreen()
        }
    }

    private val successObserver = Observer<Unit> {
        openNextScreen()
    }

    private fun openNextScreen() {
        lifecycleScope.launch {
            // 1st September 2024 01:00:00
            if (Calendar.getInstance().timeInMillis >= 1725134400000) {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToTrialEndScreen())
            } else {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToProductsScreen())
            }
        }
    }
}
