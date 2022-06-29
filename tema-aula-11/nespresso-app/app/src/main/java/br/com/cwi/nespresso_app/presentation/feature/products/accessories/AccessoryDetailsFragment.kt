package br.com.cwi.nespresso_app.presentation.feature.products.accessories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.cwi.nespresso_app.databinding.FragmentAccessoryDetailsBinding
import br.com.cwi.nespresso_app.presentation.feature.products.viewholder.AccessoryDetailsViewHolder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AccessoryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAccessoryDetailsBinding

    private val accessoryId by lazy {
        arguments?.getInt("ACCESSORY_ID")
    }

    private val viewModel: AccessoryViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccessoryDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModel(view)
    }

    private fun setUpViewModel(view: View) {
        viewModel.accessory.observe(viewLifecycleOwner) {
            AccessoryDetailsViewHolder(view).bind(this, it)
        }
        viewModel.fetchAccessoryDetails(accessoryId)
    }
}