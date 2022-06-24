package br.com.cwi.nespresso_app.presentation.feature.products.machineDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.cwi.nespresso_app.databinding.ActivityMachineDetailsBinding
import br.com.cwi.nespresso_app.domain.entity.Machine
import br.com.cwi.nespresso_app.presentation.extension.toMoneyFormat
import br.com.cwi.nespresso_app.presentation.extension.visibleOrGone
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
class MachineDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMachineDetailsBinding

    private val viewModel: MachineDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMachineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setUpViewModel()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setUpViewModel() {
        viewModel.machine.observe(this@MachineDetailsActivity) { machine ->
            setUpAdapter(machine)
        }

        viewModel.loading.observe(this@MachineDetailsActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@MachineDetailsActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }

        viewModel.fetchMachineDetails(intent.getIntExtra("item",0))
    }

    private fun setUpAdapter(machine: Machine) {
        binding.tvMachineName.text = machine.name
        binding.tvMachinePrice.text = machine.unitPrice?.toMoneyFormat(0)
        binding.tvMachineDescription.text = machine.description

        Glide.with(this).load(machine.imageUrl).into(binding.ivMachinePhoto)
    }
}