package br.com.cwi.nespresso_app.presentation.feature.products.accessoryDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.nespresso_app.domain.entity.Accessory
import br.com.cwi.nespresso_app.domain.repository.CoffeeRepository
import br.com.cwi.nespresso_app.presentation.base.BaseViewModel


class AccessoryDetailsViewModel(private val repository: CoffeeRepository): BaseViewModel() {

    private val _accessory = MutableLiveData<Accessory>()
    val accessory: LiveData<Accessory> = _accessory

    fun fetchAccessoryDetails(itemId: Int, category: String) {
        launch {
            val selectedAccessory = repository.getAccessoryDetails(itemId, category)
            _accessory.postValue(selectedAccessory)
        }
    }
}