package br.com.cwi.nespresso_app.presentation.feature.products.viewholder

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.databinding.FragmentAccessoryDetailsBinding
import br.com.cwi.nespresso_app.databinding.ItemAccessoryBinding
import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.presentation.extension.toMoneyFormat
import com.bumptech.glide.Glide

class AccessoryViewHolder(
    itemView: View,
    private val onFavoriteClick: (Accessory) -> Unit,
    private val onAccessoryClick: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle = ItemAccessoryBinding.bind(itemView).tvTitle
    private val ivImage = ItemAccessoryBinding.bind(itemView).ivImage
    private val tvPrice = ItemAccessoryBinding.bind(itemView).tvPrice
    private val ivFavorite = ItemAccessoryBinding.bind(itemView).ivFavorite

    fun bind(context: Context, item: Accessory) {
        tvTitle.text = item.name
        tvPrice.text = item.unitPrice.toMoneyFormat()

        with(ivFavorite) {
            setImageDrawable(getFavoriteIcon(item))
            setOnClickListener {
                item.isFavorite = !item.isFavorite
                setImageDrawable(getFavoriteIcon(item))
                onFavoriteClick(item)
            }
        }

        Glide.with(context)
            .load(item.urlImage)
            .into(ivImage)

        itemView.setOnClickListener {
            onAccessoryClick(item.id)
        }
    }

    private fun getFavoriteIcon(accessory: Accessory) = ContextCompat.getDrawable(
        itemView.context,
        if (accessory.isFavorite) R.drawable.ic_favorite_filled
        else R.drawable.ic_favorite_rounded
    )
}

class AccessoryDetailsViewHolder(item: View) {
    private val tvAccessoryName = FragmentAccessoryDetailsBinding.bind(item).tvAccessoryName
    private val tvAccessoryPrice = FragmentAccessoryDetailsBinding.bind(item).tvAccessoryPrice
    private val tvAccessoryDescription = FragmentAccessoryDetailsBinding.bind(item).tvAccessoryDescription
    private val ivAccessoryPhoto = FragmentAccessoryDetailsBinding.bind(item).ivAccessoryPhoto

    fun bind (context: Fragment, item: Accessory) {
        tvAccessoryName.text = item.name
        tvAccessoryPrice.text = item.unitPrice?.toMoneyFormat(0)
        tvAccessoryDescription.text = item.description

        Glide.with(context)
            .load(item.urlImage)
            .into(ivAccessoryPhoto)
    }
}