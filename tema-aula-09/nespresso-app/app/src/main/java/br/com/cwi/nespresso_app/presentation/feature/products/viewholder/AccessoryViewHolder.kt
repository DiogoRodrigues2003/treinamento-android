package br.com.cwi.nespresso_app.presentation.feature.products.viewholder

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.nespresso_app.databinding.ItemAccessoryBinding
import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.presentation.extension.toMoneyFormat
import br.com.cwi.nespresso_app.presentation.feature.products.accessoryDetails.AccessoryDetailsActivity
import com.bumptech.glide.Glide

class AccessoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val contentAccessory = ItemAccessoryBinding.bind(item).contentAccessory
    private val tvTitle = ItemAccessoryBinding.bind(item).tvTitle
    private val ivImage = ItemAccessoryBinding.bind(item).ivImage
    private val tvPrice = ItemAccessoryBinding.bind(item).tvPrice

    fun bind(context: Context, accessory: Accessory) {
        tvTitle.text = accessory.name
        tvPrice.text = accessory.unitPrice?.toMoneyFormat()

        Glide.with(context).load(accessory.urlImage).into(ivImage)

        with(contentAccessory) {
            setOnClickListener {
                val intent = Intent(context, AccessoryDetailsActivity::class.java)
                    .putExtra("id", accessory.id)
                    .putExtra("category", accessory.category)
                startActivity(context, intent, null)
            }
        }
    }
}