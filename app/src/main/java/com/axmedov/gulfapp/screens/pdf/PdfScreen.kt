package com.axmedov.gulfapp.screens.pdf

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ScreenPdfBinding
import com.axmedov.gulfapp.utils.scope
import com.axmedov.gulfapp.utils.timber
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PdfScreen : Fragment(R.layout.screen_pdf) {
    private val binding by viewBinding(ScreenPdfBinding::bind)
    private val args by navArgs<PdfScreenArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

//        OnRenderListener {
//            fun onInitiallyRendered(pages: Int, pageWidth: Float, pageHeight: Float) {
//                pdfView.fitToWidth(pageIndex)
//            }
//        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        timber(args.pdfName, "skdskdlskdlskld")
        pdfView.fromAsset(args.pdfName)
            .nightMode(false) // toggle night mode
            .load()
    }
}
