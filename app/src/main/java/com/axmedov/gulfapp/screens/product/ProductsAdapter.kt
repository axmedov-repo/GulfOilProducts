package com.axmedov.gulfapp.screens.product

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.app.App
import com.axmedov.gulfapp.data.entities.ProductData
import com.axmedov.gulfapp.data.enums.ProductTypes
import com.axmedov.gulfapp.databinding.ItemProductBinding
import com.axmedov.gulfapp.utils.scope

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.VH>() {
    private val list = ArrayList<ProductData>()

    fun setData(newList: List<ProductData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((ProductData) -> Unit)? = null
    fun setItemClickedListener(f: (ProductData) -> Unit) {
        itemClickedListener = f
    }

    inner class VH(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickedListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() = binding.scope {
            val data = list[absoluteAdapterPosition]
            if (data.productType == ProductTypes.CONTACT) {
                itemView.setBackgroundResource(R.drawable.bg_item_products_contact)
                txtNameProduct.setTextColor(ContextCompat.getColor(App.instance, R.color.white))
                imgProduct.setColorFilter(
                    ContextCompat.getColor(App.instance, R.color.white),
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                itemView.setBackgroundResource(R.drawable.bg_item_products)
                txtNameProduct.setTextColor(ContextCompat.getColorStateList(App.instance, R.color.product_txt_color))
                imgProduct.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColorStateList(App.instance, R.color.product_img_color)!!.defaultColor,
                    PorterDuff.Mode.SRC_IN
                )
            }
            imgProduct.setImageResource(data.image)
            txtNameProduct.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
