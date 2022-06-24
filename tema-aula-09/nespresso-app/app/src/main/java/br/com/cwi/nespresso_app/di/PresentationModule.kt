package br.com.cwi.nespresso_app.di

import br.com.cwi.nespresso_app.presentation.feature.products.accessories.AccessoryViewModel
import br.com.cwi.nespresso_app.presentation.feature.products.accessoryDetails.AccessoryDetailsViewModel
import br.com.cwi.nespresso_app.presentation.feature.products.coffee.CoffeeViewModel
import br.com.cwi.nespresso_app.presentation.feature.products.machine.MachineViewModel
import br.com.cwi.nespresso_app.presentation.feature.products.machineDetails.MachineDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CoffeeViewModel(get()) }
    viewModel { MachineViewModel(get()) }
    viewModel { AccessoryViewModel(get()) }
    viewModel { MachineDetailsViewModel(get()) }
    viewModel { AccessoryDetailsViewModel(get()) }
}