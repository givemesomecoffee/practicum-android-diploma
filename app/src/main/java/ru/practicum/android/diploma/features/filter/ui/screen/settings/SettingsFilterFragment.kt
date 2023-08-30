package ru.practicum.android.diploma.features.filter.ui.screen.settings

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.navigation.koinNavGraphViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.navigation.util.goToScreen
import ru.practicum.android.diploma.databinding.FragmentSettingsFilterBinding
import ru.practicum.android.diploma.features.filter.FilterScreens
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.filter.ui.SettingsFilterEvent
import ru.practicum.android.diploma.features.filter.ui.util.enablePopUp

class SettingsFilterFragment : Fragment(R.layout.fragment_settings_filter) {

    private val viewModel : SettingsFilterViewModel by koinNavGraphViewModel(R.id.filter_nav_graph)
    private val binding by viewBinding(FragmentSettingsFilterBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state.observe(viewLifecycleOwner, ::updateView)
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            enablePopUp(toolbar)
            etSalary.editText?.doOnTextChanged { text, _, _, _ ->
                viewModel.onEvent(SettingsFilterEvent.SalaryFilter(text.toString().toLongOrNull()))
            }
            filterExcludeNoSalary.setOnCheckedChangeListener { _, checked ->
                viewModel.onEvent(SettingsFilterEvent.IncludeNoSalary(!checked))
            }
            btnReset.setOnClickListener {
                viewModel.onEvent(SettingsFilterEvent.ResetFilter)
            }
            btnConfirm.setOnClickListener {
                viewModel.onEvent(SettingsFilterEvent.ApplyChanges)
                findNavController().popBackStack()
            }
            settingsFilterWorkplace.setOnClickListener {
                goToScreen(FilterScreens.Workplace.getScreen())
            }
        }
    }

    private fun updateView(state: Pair<Filter, Boolean>?) {
        state?.let {
            val filter = it.first
            val hasChanges = it.second
            binding.run {
                settingsFilterWorkplace.setTitle(filter.location?.toString()){
                    viewModel.onEvent(SettingsFilterEvent.ResetWorkplace)
                }
                if (etSalary.editText?.text.toString() != filter.salary.toString()) {
                    etSalary.editText?.setText(filter.salary?.toString().orEmpty())
                }
                if (filterExcludeNoSalary.isChecked == filter.showNoSalaryItems) {
                    filterExcludeNoSalary.isChecked = !filter.showNoSalaryItems
                }
                btnReset.isVisible = hasChanges
                btnConfirm.isVisible = hasChanges
            }
        }
    }
}