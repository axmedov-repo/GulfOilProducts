package com.gulfoil.pdsapp.screens.trial_end

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.databinding.ScreenTrialEndBinding
import com.gulfoil.pdsapp.utils.scope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrialEndScreen : Fragment(R.layout.screen_trial_end) {
    private val binding by viewBinding(ScreenTrialEndBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }
}