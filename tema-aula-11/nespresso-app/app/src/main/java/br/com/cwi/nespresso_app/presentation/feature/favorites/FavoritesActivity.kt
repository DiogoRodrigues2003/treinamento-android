package br.com.cwi.nespresso_app.presentation.feature.favorites

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.data.database.entity.EntityType
import br.com.cwi.nespresso_app.presentation.base.BaseBottomNavigation
import br.com.cwi.nespresso_app.databinding.ActivityFavoritesBinding
import br.com.cwi.nespresso_app.presentation.extension.visibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesActivity : BaseBottomNavigation() {

    private lateinit var binding: ActivityFavoritesBinding

    private val viewModel : FavoritesViewModel by viewModel()

    override val currentTab: Int = R.id.favorites_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
    }

    override fun getBottomNavigation(): BottomNavigationView = binding.contentBottomNavigation.root

    private fun setUpViewModel() {
        viewModel.favorites.observe(this@FavoritesActivity) { list ->
            setUpFavoriteRecyclerView(list)
        }

        viewModel.loading.observe(this@FavoritesActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@FavoritesActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }

        viewModel.fetchFavorites()
    }

    private fun setUpFavoriteRecyclerView(list: List<EntityType>) {
        binding.rvFavorites.apply {
            addItemDecoration(
                DividerItemDecoration(this@FavoritesActivity, DividerItemDecoration.VERTICAL)
            )
            adapter = FavoritesAdapter(list)
        }
    }

}