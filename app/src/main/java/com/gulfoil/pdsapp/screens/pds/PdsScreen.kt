package com.gulfoil.pdsapp.screens.pds

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.activity.setInternetReconnectedListener
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.databinding.ScreenPdsBinding
import com.gulfoil.pdsapp.screens.ads.AdsAdapter
import com.gulfoil.pdsapp.screens.pds.viewmodel.PdsViewModel
import com.gulfoil.pdsapp.screens.pds.viewmodel.PdsViewModelImpl
import com.gulfoil.pdsapp.utils.gone
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.showMessageOnTopOfScreen
import com.gulfoil.pdsapp.utils.visible
import com.hadar.danny.horinzontaltransformers.DepthTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.InputStream

@AndroidEntryPoint
class PdsScreen : Fragment(R.layout.screen_pds) {
    private val binding by viewBinding(ScreenPdsBinding::bind)
    private val viewModel: PdsViewModel by viewModels<PdsViewModelImpl>()
    private val args by navArgs<PdsScreenArgs>()
    private var language: Languages = Languages.ENGLISH

    private lateinit var adsAdapter: AdsAdapter
    private var currentPage = 0
    private val delayMillis: Long = 3000
    private var job: Job? = null
    private var isUIVisible: Boolean = false

    init {
        setInternetReconnectedListener {
            loadData()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
        loadData()
    }

    override fun onResume() {
        super.onResume()
        isUIVisible = true
        viewModel.getLanguage()
    }

    private fun loadData() {
        viewModel.getLanguage()
        viewModel.getPds(args.oilId)
        viewModel.getAds()
    }

    private fun setViews() = binding.scope {
        adsVP.setPageTransformer(DepthTransformer())
        adsVP.isUserInputEnabled = false

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
        job = lifecycleScope.launch {
            delay(delayMillis)

            while (isUIVisible) {
                val itemCount = adsAdapter.itemCount

                if (adsAdapter.itemCount != 0) {
                    if (currentPage == itemCount - 1) {
                        currentPage = 0
                        binding.adsVP.setCurrentItem(currentPage, false)
                        delay(delayMillis)
                    } else {
                        currentPage++
                        binding.adsVP.setCurrentItem(currentPage, true)
                        delay(delayMillis)
                    }
                }
            }
        }
    }

    private fun setModels() = binding.scope {
        viewModel.apply {
            pdsLiveData.observe(viewLifecycleOwner) {
                setPdf(it)
            }
            progressLiveData.observe(viewLifecycleOwner) {
                progressBar.visible(it)
            }
            errorLiveData.observe(viewLifecycleOwner) {
                showMessageOnTopOfScreen(it)
            }
            emptyLiveData.observe(viewLifecycleOwner) {
                txtEmpty.visible(it)
            }
            lastLanguageLiveData.observe(viewLifecycleOwner) {
                language = it
                setData()
            }
            adResponseLiveData.observe(viewLifecycleOwner) { adsList ->
                adsAdapter = AdsAdapter(childFragmentManager, lifecycle, adsList)
                adsVP.adapter = adsAdapter
                job?.cancel()
                if (adsList.isNotEmpty()) {
                    adsVP.setCurrentItem(0, false)
                    startAutoScroll()
                }
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

    private fun setPdf(inputStream: InputStream) = binding.scope {
        pdfView.fromStream(inputStream).onLoad {
            progressBar.gone()
        }.load()
    }

    override fun onPause() {
        super.onPause()
        isUIVisible = false
        job?.cancel()
        currentPage = 0
    }
}
