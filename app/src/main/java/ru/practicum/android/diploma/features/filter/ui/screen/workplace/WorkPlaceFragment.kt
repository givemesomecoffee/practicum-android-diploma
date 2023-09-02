package ru.practicum.android.diploma.features.filter.ui.screen.workplace

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.navigation.koinNavGraphViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentWorkplaceBinding
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.filter.ui.SettingsFilterEvent
import ru.practicum.android.diploma.features.filter.ui.screen.settings.SettingsFilterViewModel
import ru.practicum.android.diploma.features.filter.ui.util.enablePopUp

class WorkPlaceFragment : Fragment(R.layout.fragment_workplace) {

    private val viewModel: SettingsFilterViewModel by koinNavGraphViewModel(R.id.filter_nav_graph)
    private val binding by viewBinding(FragmentWorkplaceBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::updateView)
        enablePopUp(binding.toolbar)
        binding.country.setOnClickListener {
            // TODO:
        }

        binding.region.setOnClickListener {
            // TODO:
        }
    }

    private fun updateView(state: Pair<Filter, Boolean>?) {
        state?.first?.let { filter ->
            binding.apply {
                country.setTitle(filter.location?.country?.name) {
                    viewModel.onEvent(SettingsFilterEvent.ResetCountry)
                }
                region.setTitle(filter.location?.city?.name) {
                    viewModel.onEvent(SettingsFilterEvent.ResetRegion)
                }
            }
        }
    }
}