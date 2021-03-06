package br.com.cwi.nespresso_app.presentation.feature.products.machine

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.data.mapper.CategoryAccessoryMapper
import br.com.cwi.nespresso_app.data.mapper.CategoryCoffeeMapper
import br.com.cwi.nespresso_app.data.mapper.MachineMapper
import br.com.cwi.nespresso_app.data.repository.CoffeeRepositoryImpl
import br.com.cwi.nespresso_app.databinding.ActivityMachineBinding
import br.com.cwi.nespresso_app.domain.entity.Machine
import br.com.cwi.nespresso_app.domain.repository.CoffeeRepository
import br.com.cwi.nespresso_app.presentation.base.BaseBottomNavigation
import br.com.cwi.nespresso_app.presentation.extension.visibleOrGone
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MachineActivity : BaseBottomNavigation() {

    private lateinit var binding: ActivityMachineBinding

    private val viewModel = MachineViewModel()

    override val currentTab: Int = R.id.products_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMachineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpViewModel()
    }

    override fun getBottomNavigation(): BottomNavigationView = binding.contentBottomNavigation.root

    private fun setUpViewModel() {
        viewModel.machines.observe(this@MachineActivity) { list ->
            setUpMachineRecyclerView(list)
        }

        viewModel.loading.observe(this@MachineActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@MachineActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }

        viewModel.fetchMachines()
    }

    private fun setUpMachineRecyclerView(list: List<Machine>) {
        list.let {
            val recyclerView = binding.rvMachines
            recyclerView.adapter = MachineAdapter(context = this@MachineActivity, it)
            recyclerView.layoutManager = GridLayoutManager(this@MachineActivity, 2)
        }
    }
}