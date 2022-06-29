package br.com.cwi.nespresso_app.presentation.feature.products.accessories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.databinding.FragmentAccessoryBinding
import br.com.cwi.nespresso_app.domain.entity.Type
import br.com.cwi.nespresso_app.presentation.feature.products.coffee.CoffeeAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AccessoryFragment : Fragment() {

    private lateinit var binding: FragmentAccessoryBinding

    private val viewModel: AccessoryViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccessoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.accessories.observe(viewLifecycleOwner) { list ->
            setUpCoffeeRecyclerView(list)
        }
        viewModel.fetchAccessories()
    }

    private fun setUpCoffeeRecyclerView(list: List<Type>) {
        binding.rvAccessories.apply {
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
            adapter = AccessoryAdapter(context, list,
                onFavoriteClick = {
                    viewModel.setFavorite(it)
            },  onAccessoryClick = {
                    navigateToAccessoryDetails(it)
            })
        }
    }

    private fun navigateToAccessoryDetails(id: Int) {
        findNavController().navigate(
            R.id.accessoryDetailsFragment,
            bundleOf(
                Pair("ACCESSORY_ID", id)
            )
        )
    }
}