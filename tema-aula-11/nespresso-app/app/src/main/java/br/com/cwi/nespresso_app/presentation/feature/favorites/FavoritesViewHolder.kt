package br.com.cwi.nespresso_app.presentation.feature.favorites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.nespresso_app.data.database.entity.AccessoryEntity
import br.com.cwi.nespresso_app.data.database.entity.CoffeeEntity
import br.com.cwi.nespresso_app.databinding.ItemFavoriteBinding
import br.com.cwi.nespresso_app.presentation.extension.toMoneyFormat
import com.bumptech.glide.Glide

class FavoritesViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = ItemFavoriteBinding.bind(itemView).tvTitle
    private val tvSubtitle = ItemFavoriteBinding.bind(itemView).tvSubtitle
    private val ivImage = ItemFavoriteBinding.bind(itemView).ivImage
    private val tvPrice = ItemFavoriteBinding.bind(itemView).tvPrice

    fun bind(item: CoffeeEntity) {
        tvTitle.text = item.name
        tvSubtitle.text = "CAFÉS"
        tvPrice.text = item.unitPrice.toMoneyFormat()

        Glide.with(itemView.context)
            .load(item.urlImage)
            .into(ivImage)
    }

    fun bind(item: AccessoryEntity) {
        tvTitle.text = item.name
        tvSubtitle.text = "ACESSÓRIOS"
        tvPrice.text = item.unitPrice.toMoneyFormat()

        Glide.with(itemView.context)
            .load(item.urlImage)
            .into(ivImage)
    }
}