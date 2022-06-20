package br.com.cwi.nespresso_app.presentation.products.accessory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.data.entity.*
import br.com.cwi.nespresso_app.databinding.ItemAccessoryBinding
import br.com.cwi.nespresso_app.databinding.ItemAccessoryCategoryBinding
import br.com.cwi.nespresso_app.presentation.extension.toMoneyFormat
import com.bumptech.glide.Glide

const val VIEW_TYPE_CATEGORY = 0

class AccessoryAdapter(val context: Context, private val list: List<ProductType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_CATEGORY) {

            val view = inflateView(R.layout.item_accessory_category, parent)
            AccessoryCategoryViewHolder(view)

        } else {

            val view = inflateView(R.layout.item_accessory, parent)
            AccessoryViewHolder(view)

        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]

        if (item.type == VIEW_TYPE_CATEGORY) {

            item as AccessoryCategoryResponse
            (viewHolder as AccessoryCategoryViewHolder).bind(item)

        } else {

            item as AccessoryResponse
            (viewHolder as AccessoryViewHolder).bind(context, item)

        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = list[position].type

    private fun inflateView(layout: Int, parent: ViewGroup) = LayoutInflater.from(parent.context)
        .inflate(layout, parent, false)
}

class AccessoryCategoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val tvCategory = ItemAccessoryCategoryBinding.bind(item).tvAccessoryTitleCategory

    fun bind(item: AccessoryCategoryResponse) {
        tvCategory.text = item.category
    }
}

class AccessoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val tvTitle = ItemAccessoryBinding.bind(item).tvAccessoryTitle
    private val ivImage = ItemAccessoryBinding.bind(item).ivAccessoryImage
    private val tvPrice = ItemAccessoryBinding.bind(item).tvAccessoryPrice

    fun bind(context: Context, item: AccessoryResponse) {
        tvTitle.text = item.name
        tvPrice.text = item.price.toMoneyFormat()

        Glide.with(context)
            .load(item.imageUrl)
            .into(ivImage)
    }
}