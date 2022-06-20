package br.com.cwi.nespresso_app.presentation.products.accessory.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.nespresso_app.databinding.ItemAccessoryCategoryBinding
import br.com.cwi.nespresso_app.domain.entity.AccessoryCategory

class AccessoryCategoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val tvCategory = ItemAccessoryCategoryBinding.bind(item).tvAccessoryTitleCategory

    fun bind(item: AccessoryCategory) {
        tvCategory.text = item.category
    }
}