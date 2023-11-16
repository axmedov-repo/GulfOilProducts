package com.gulfoil.pdsapp.screens.pds

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.data.entities.AdsData
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.databinding.ScreenPdsBinding
import com.gulfoil.pdsapp.screens.ads.AdsAdapter
import com.gulfoil.pdsapp.screens.pds.viewmodel.PdsViewModel
import com.gulfoil.pdsapp.screens.pds.viewmodel.PdsViewModelImpl
import com.gulfoil.pdsapp.utils.adsDataList
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.showToast
import com.gulfoil.pdsapp.utils.visible
import com.hadar.danny.horinzontaltransformers.DepthTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PdsScreen : Fragment(R.layout.screen_pds) {
    private val binding by viewBinding(ScreenPdsBinding::bind)
    private val viewModel: PdsViewModel by viewModels<PdsViewModelImpl>()
    private val args by navArgs<PdsScreenArgs>()
    private var language: Languages = Languages.ENGLISH

    private lateinit var adsAdapter: AdsAdapter
    private val adsList = ArrayList<AdsData>()
    private var handler: Handler? = null
    private var currentPage = 0
    private val delayMillis: Long = 3000
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLanguage()
        viewModel.getPds(args.oilId)
        startAutoScroll()
    }

    private fun setViews() = binding.scope {
        adsList.clear()
        adsList.addAll(adsDataList)
        adsAdapter = AdsAdapter(childFragmentManager, lifecycle, adsList)
        vp.adapter = adsAdapter
        vp.setPageTransformer(DepthTransformer())
        vp.isUserInputEnabled = false
        handler = Handler()

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        imgLanguage.setOnClickListener {
            if (language == Languages.ENGLISH) {
                language = Languages.RUSSIAN
                viewModel.setLanguage(language)
                setData()
                viewModel.getPds(args.oilId)
            } else {
                language = Languages.ENGLISH
                viewModel.setLanguage(language)
                setData()
                viewModel.getPds(args.oilId)
            }
        }
    }

    private fun startAutoScroll() {
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(delayMillis)

            while (true) {
                val itemCount = adsAdapter.itemCount
                if (currentPage == itemCount - 1) {
                    currentPage = 0
                    binding.vp.setCurrentItem(currentPage, false)
                    delay(delayMillis)
                } else {
                    currentPage++
                    binding.vp.setCurrentItem(currentPage, true)
                    delay(delayMillis)
                }
            }
        }
    }

    private fun setModels() = binding.scope {
        viewModel.apply {
            pdsLiveData.observe(viewLifecycleOwner) {
                if (it.pdf.isNullOrEmpty()) {
                    txtEmpty.visible()
                } else {
                    setPdf(it.pdf)
                }
            }
            progressLiveData.observe(viewLifecycleOwner) {
                progressBar.visible(it)
            }
            errorLiveData.observe(viewLifecycleOwner) {

            }
            lastLanguageLiveData.observe(viewLifecycleOwner) {
                language = it
                setData()
            }
        }
    }

    private fun setData() = binding.scope {
        when (language) {
            Languages.ENGLISH -> {
                txtTitle.text = getString(R.string.txt_specifications_en)
                txtEmpty.text = getString(R.string.txt_empty_en)
                imgLanguage.setImageResource(R.drawable.ic_flag_en)
            }

            Languages.RUSSIAN -> {
                txtTitle.text = getString(R.string.txt_specifications_ru)
                txtEmpty.text = getString(R.string.txt_empty_ru)
                imgLanguage.setImageResource(R.drawable.ic_flag_ru)
            }
        }
    }

    private fun setPdf(value: String) = binding.scope {
//        if (URLUtil.isValidUrl(value)) {
        showToast(value)
        pdfView.fromUri(Uri.parse(value)).nightMode(false) // toggle night mode
            .load()
//        }
    }

    override fun onPause() {
        super.onPause()
        job?.cancel()
    }
}
