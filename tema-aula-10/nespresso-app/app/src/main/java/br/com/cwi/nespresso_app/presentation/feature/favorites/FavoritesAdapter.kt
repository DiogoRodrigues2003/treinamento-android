package br.com.cwi.nespresso_app.presentation.feature.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.data.database.entity.AccessoryEntity
import br.com.cwi.nespresso_app.data.database.entity.CoffeeEntity
import br.com.cwi.nespresso_app.data.database.entity.EntityType
import br.com.cwi.nespresso_app.data.database.entity.ProductType

class FavoritesAdapter (
    private val list: List<EntityType>
) : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflateView(R.layout.item_favorite, parent)
        return FavoritesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = list[position]
        viewHolder as FavoritesViewHolder

        if (item.type == ProductType.COFFEE) viewHolder.bind(item as CoffeeEntity)
        else if (item.type == ProductType.ACCESSORY) viewHolder.bind(item as AccessoryEntity)
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = list[position].type.value

    private fun inflateView(layout: Int, parent: ViewGroup) = LayoutInflater.from(parent.context)
        .inflate(layout, parent, false)
}
