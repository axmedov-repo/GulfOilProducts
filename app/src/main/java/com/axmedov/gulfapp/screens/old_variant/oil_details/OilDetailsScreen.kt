package com.axmedov.gulfapp.screens.old_variant.oil_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ScreenOilDetailsBinding
import com.axmedov.gulfapp.utils.scope

//@AndroidEntryPoint
class OilDetailsScreen : Fragment(R.layout.screen_oil_details) {
    private val binding by viewBinding(ScreenOilDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    private fun setViews() = binding.scope {

    }

    private fun setModels() = binding.scope {

    }
}
