package br.com.cwi.nespresso_app.presentation.products.accessory

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.domain.entity.*
import br.com.cwi.nespresso_app.presentation.products.accessory.viewholder.AccessoryCategoryViewHolder
import br.com.cwi.nespresso_app.presentation.products.accessory.viewholder.AccessoryViewHolder

class AccessoryAdapter(val context: Context, private val list: List<Type>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ItemType.CATEGORY.value) {
            val view = inflateView(R.layout.item_accessory_category, parent)
            AccessoryCategoryViewHolder(view)

        } else {
            val view = inflateView(R.layout.item_accessory, parent)
            AccessoryViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]

        if (item.type == ItemType.CATEGORY) {
            item as AccessoryCategory
            (viewHolder as AccessoryCategoryViewHolder).bind(item)

        } else {
            item as Accessory
            (viewHolder as AccessoryViewHolder).bind(context, item)
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = list[position].type.value

    private fun inflateView(layout: Int, parent: ViewGroup) = LayoutInflater.from(parent.context)
        .inflate(layout, parent, false)
}