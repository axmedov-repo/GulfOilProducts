package com.axmedov.gulfapp.screens.pdf

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.data.enums.Languages
import com.axmedov.gulfapp.databinding.ScreenPdfBinding
import com.axmedov.gulfapp.screens.pdf.viewmodel.PdfViewModel
import com.axmedov.gulfapp.screens.pdf.viewmodel.PdfViewModelImpl
import com.axmedov.gulfapp.utils.scope
import com.axmedov.gulfapp.utils.timber
import com.axmedov.gulfapp.utils.visible

//@AndroidEntryPoint
class PdfScreen : Fragment(R.layout.screen_pdf) {
    private val binding by viewBinding(ScreenPdfBinding::bind)
    private val viewModel: PdfViewModel by viewModels<PdfViewModelImpl>()
    private val args by navArgs<PdfScreenArgs>()
    private var language: Languages = Languages.ENGLISH

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLanguage()
    }

    private fun setViews() = binding.scope {
        /*         OnRenderListener {
             fun onInitiallyRendered(pages: Int, pageWidth: Float, pageHeight: Float) {
                 pdfView.fitToWidth(pageIndex)
             }
         }*/

        setPdf(args.pdfName)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        imgLanguage.setOnClickListener {
            if (language == Languages.ENGLISH) {
                language = Languages.RUSSIAN
                setData()
                viewModel.setLanguage(language)
            } else {
                language = Languages.ENGLISH
                setData()
                viewModel.setLanguage(language)
            }
        }
    }

    private fun setModels() = binding.scope {
        viewModel.lastLanguageLiveData.observe(viewLifecycleOwner) {
            language = it
            setData()
        }
    }

    private fun setData() = binding.scope {
        when (language) {
            Languages.ENGLISH -> {
                txtTitle.text = getString(R.string.txt_specifications_en)
                txtEmpty.text = getString(R.string.txt_empty_en)
                imgLanguage.setImageResource(R.drawable.ic_flag_en)
                if (args.pdfName.isEmpty()) {
                    txtEmpty.visible()
                } else {
                    setPdf(args.pdfName.replace(Languages.RUSSIAN.brief, Languages.ENGLISH.brief))
                }
            }

            Languages.RUSSIAN -> {
                txtTitle.text = getString(R.string.txt_specifications_ru)
                txtEmpty.text = getString(R.string.txt_empty_ru)
                imgLanguage.setImageResource(R.drawable.ic_flag_ru)
                if (args.pdfName.isEmpty()) {
                    txtEmpty.visible()
                } else {
                    setPdf(args.pdfName.replace(Languages.ENGLISH.brief, Languages.RUSSIAN.brief))
                }
            }
        }
    }

    private fun setPdf(value: String) = binding.scope {
        timber(args.pdfName, "skdskdlskdlskld")

        pdfView.fromAsset(value)
            .nightMode(false) // toggle night mode
            .load()
    }
}
