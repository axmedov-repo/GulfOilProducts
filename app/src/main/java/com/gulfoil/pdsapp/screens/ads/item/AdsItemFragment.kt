package com.gulfoil.pdsapp.screens.ads.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.databinding.ItemAdsBinding
import com.gulfoil.pdsapp.utils.scope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdsItemFragment : Fragment(R.layout.item_ads) {
    private val binding by viewBinding(ItemAdsBinding::bind)
    private var width: Int? = 0
    private var height: Int? = 0

    private var imageSizesListener: ((Int, Int) -> Unit)? = null
    fun setImageSizesListener(f: (Int, Int) -> Unit) {
        imageSizesListener = f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            Glide.with(imgAd.context)
                .asBitmap()
                .load(it.getString("ADS_IMAGE"))
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)) // Disable caching for accurate dimensions
                .into(imgAd)

//            Picasso.get().load(it.getString("ADS_IMAGE")).into(object : Target {
//                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
//                    // Get image dimensions
//                    width = bitmap?.width
//                    height = bitmap?.height
//
//                    // Do something with the dimensions, for example, display them in a Toast
//                    // Set the bitmap to the ImageView
//                    imgAd.setImageBitmap(bitmap)
//                }
//
//                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
//                    // Handle the error
//                }
//
//                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
//                    // Do nothing here
//                }
//            })
        }
    }
}
