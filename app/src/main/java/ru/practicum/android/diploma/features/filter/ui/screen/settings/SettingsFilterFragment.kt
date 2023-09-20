package ru.practicum.android.diploma.features.filter.ui.screen.settings

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.getParcelableCompat
import ru.practicum.android.diploma.databinding.FragmentSettingsFilterBinding
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.filter.ui.SettingsFilterEvent
import ru.practicum.android.diploma.features.filter.ui.model.AreaResult
import ru.practicum.android.diploma.features.filter.ui.model.toAreaResult
import ru.practicum.android.diploma.features.filter.ui.screen.workplace.WorkPlaceResult
import ru.practicum.android.diploma.core.ui.toolbar.enablePopUp
import ru.practicum.android.diploma.features.filter.domain.model.Industry
import ru.practicum.android.diploma.features.filter.ui.model.IndustryResult
import ru.practicum.android.diploma.features.filter.ui.model.toIndustryResult
import ru.practicum.android.diploma.features.filter.ui.screen.industry.IndustryFilterResult

class SettingsFilterFragment : Fragment(R.layout.fragment_settings_filter) {

    private val viewModel by viewModel<SettingsFilterViewModel>()
    private val binding by viewBinding(FragmentSettingsFilterBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(WorkPlaceResult.requestKey) { requestKey, bundle ->
            val country = bundle.getParcelableCompat<AreaResult>(WorkPlaceResult.country)
            val region = bundle.getParcelableCompat<AreaResult>(WorkPlaceResult.region)
            viewModel.onEvent(SettingsFilterEvent.WorkPlaceFilter(country, region))
            clearFragmentResult(requestKey)
        }
        setFragmentResultListener(IndustryFilterResult.requestKey) { requestKey, bundle ->
            val industry = bundle.getParcelableCompat<IndustryResult>(IndustryFilterResult.industry)
            viewModel.onEvent(SettingsFilterEvent.IndustryFilter(industry))
            clearFragmentResult(requestKey)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.state.observe(viewLifecycleOwner, ::updateView)
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            enablePopUp(toolbar)
            etSalary.setEndIconOnClickListener {
                etSalary.editText?.text = null
            }
            etSalary.editText?.doOnTextChanged { text, _, _, _ ->
                if (text?.toString().isNullOrEmpty()) {
                    etSalary.endIconDrawable = null
                } else {
                    etSalary.endIconDrawable =
                        ResourcesCompat.getDrawable(resources, R.drawable.cross_icon, null)
                }
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
        }
    }

    private fun updateView(state: Pair<Filter, Boolean>?) {
        state?.let {
            val filter = it.first
            val hasChanges = it.second
            binding.run {
                settingsFilterWorkplace.setTitle(filter.location?.toString()) {
                    viewModel.onEvent(SettingsFilterEvent.ResetWorkplace)
                }
                settingsFilterIndustry.setTitle(filter.industry?.name) {
                    viewModel.onEvent(SettingsFilterEvent.ResetIndustry)
                }
                if (etSalary.editText?.text.toString() != filter.salary.toString()) {
                    etSalary.editText?.setText(filter.salary?.toString().orEmpty())
                }
                if (filterExcludeNoSalary.isChecked == filter.showNoSalaryItems) {
                    filterExcludeNoSalary.isChecked = !filter.showNoSalaryItems
                }
                btnReset.isVisible = hasChanges
                btnConfirm.isVisible = hasChanges
                settingsFilterWorkplace.setOnClickListener {
                    goToWorkplaceFilter(filter.location)
                }
                settingsFilterIndustry.setOnClickListener {
                    goToIndustryFilter(filter.industry)
                }
            }
        }
    }

    private fun goToWorkplaceFilter(location: Filter.WorkLocation?) {
        findNavController().navigate(
            SettingsFilterFragmentDirections.actionSettingsFilterFragmentToWorkPlaceFragment(
                location?.country?.toAreaResult(),
                location?.city?.toAreaResult()
            )
        )
    }

    private fun goToIndustryFilter(industry: Industry?) {
        findNavController().navigate(
            SettingsFilterFragmentDirections.actionSettingsFilterFragmentToIndustryFilterFragment(
                industry?.toIndustryResult()
            )
        )
    }
}