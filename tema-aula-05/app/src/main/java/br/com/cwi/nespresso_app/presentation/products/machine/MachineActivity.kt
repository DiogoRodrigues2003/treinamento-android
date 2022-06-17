package br.com.cwi.nespresso_app.presentation.products.machine

import android.os.Bundle
import br.com.cwi.nespresso_app.R
import br.com.cwi.nespresso_app.data.respository.CoffeeRepository
import br.com.cwi.nespresso_app.databinding.ActivityMachineBinding
import br.com.cwi.nespresso_app.presentation.base.BaseBottomNavigationActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MachineActivity : BaseBottomNavigationActivity() {

    private lateinit var binding: ActivityMachineBinding

    private val repository = CoffeeRepository(this)

    override val currentTab: Int = R.id.products_menu

    override fun getBottomNavigation(): BottomNavigationView =
        binding.contentBottomNavigation.bottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMachineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)

        setUpMachineList()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUpMachineList() {
        binding.rvMachines.adapter = repository.getMachines()?.machines?.let {
            MachineListAdapter(it)
        }
    }
}