package com.gulfoil.pdsapp.screens.trial_end

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.databinding.ScreenTrialEndBinding
import com.gulfoil.pdsapp.utils.scope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrialEndScreen : Fragment(R.layout.screen_trial_end) {
    private val binding by viewBinding(ScreenTrialEndBinding::bind)
    private val viewModel: TrialEndViewModel by viewModels<TrialEndViewModelImpl>()
    private var language: Languages = Languages.ENGLISH

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAppLanguage()
        viewModel.appLanguage.observe(viewLifecycleOwner) {
            language = it
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })

        txtMessage.text =
            SpannableStringBuilder().color(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            ) {
                append(
                    if (language == Languages.RUSSIAN) {
                        getString(R.string.error_trial_period_ru)
                    } else {
                        getString(R.string.error_trial_period_en)
                    }
                )
            }.color(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.orange
                )
            ) {
                append(
                    "\n${
                        if (language == Languages.RUSSIAN) {
                            getString(R.string.error_ended_ru)
                        } else {
                            getString(R.string.error_ended_en)
                        }
                    }"
                )
            }
    }
}
