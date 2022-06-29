package br.com.cwi.nespresso_app.presentation.feature.products.accessories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.databinding.ActivityAccessoryHostBinding
import br.com.cwi.nespresso_app.presentation.base.BaseBottomNavigation
import br.com.cwi.nespresso_app.presentation.extension.visibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccessoryHostActivity : BaseBottomNavigation() {

    private val viewModel: AccessoryViewModel by viewModel()

    private lateinit var binding: ActivityAccessoryHostBinding

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment)
            .findNavController()
    }

    override val currentTab: Int = R.id.products_menu

    override fun getBottomNavigation(): BottomNavigationView = binding.contentBottomNavigation.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessoryHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpViewModel()
        setUpNavController()
    }

    private fun setUpNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.accessoryFragment) {
                binding.contentBottomNavigation.bottomNavigation.visibility = View.VISIBLE
                supportActionBar?.title = getString(R.string.txt_accessories_title)

            } else {
                binding.contentBottomNavigation.bottomNavigation.visibility = View.GONE
                supportActionBar?.title = getString(R.string.txt_details)
            }
        }
    }

    private fun setUpViewModel() {
        viewModel.loading.observe(this@AccessoryHostActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@AccessoryHostActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}