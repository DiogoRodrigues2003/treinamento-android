package br.com.cwi.nespresso_app.presentation.products.machine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.data.entity.MachineResponse
import br.com.cwi.nespresso_app.databinding.ItemMachineBinding

class MachineListAdapter(private val machineList: List<MachineResponse>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MachineViewHolder(inflateView(R.layout.item_machine, parent))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = machineList.get(position)

        (holder as MachineViewHolder).bind(item)
    }

    override fun getItemCount(): Int = machineList.size

    private fun inflateView(layout: Int, parent: ViewGroup) = LayoutInflater
        .from(parent.context)
        .inflate(layout, parent, false)
}

class MachineViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    private val tvTitle = ItemMachineBinding.bind(item).tvMachineTitle
    private val ivImage = ItemMachineBinding.bind(item).ivMachineImage
    private val tvPrice = ItemMachineBinding.bind(item).tvMachinePrice
    private val ivFavorite = ItemMachineBinding.bind(item).ivFavorite

    fun bind(item: MachineResponse) {
        tvTitle.text = item.name
        tvPrice.text = "R$${item.price}"
    }
}