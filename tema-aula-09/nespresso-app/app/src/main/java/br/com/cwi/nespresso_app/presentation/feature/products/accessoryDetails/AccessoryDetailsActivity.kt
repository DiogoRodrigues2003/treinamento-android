package br.com.cwi.nespresso_app.presentation.feature.products.accessoryDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.cwi.nespresso_app.databinding.ActivityAccessoryDetailsBinding
import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.presentation.extension.toMoneyFormat
import br.com.cwi.nespresso_app.presentation.extension.visibleOrGone
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccessoryDetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAccessoryDetailsBinding

    private val viewModel: AccessoryDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccessoryDetailsBinding.inflate(layoutInflater)
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
        viewModel.accessory.observe(this@AccessoryDetailsActivity) { accessory ->
            setUpAdapter(accessory)
        }

        viewModel.loading.observe(this@AccessoryDetailsActivity) { isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this@AccessoryDetailsActivity) { hasError ->
            binding.viewError.root.visibleOrGone(hasError)
        }

        intent.getStringExtra("category")?.let {
            viewModel.fetchAccessoryDetails(intent.getIntExtra("id",0), it)
        }
    }

    private fun setUpAdapter(accessory: Accessory) {
        binding.tvAccessoryName.text = accessory.name
        binding.tvAccessoryPrice.text = accessory.unitPrice?.toMoneyFormat(0)
        binding.tvAccessoryDescription.text = accessory.description

        Glide.with(this).load(accessory.urlImage).into(binding.ivAccessoryPhoto)
    }
}