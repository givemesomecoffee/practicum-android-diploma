package ru.practicum.android.diploma.features.filter.ui.screen.country

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.lce.ContentState
import ru.practicum.android.diploma.databinding.FragmentCountryFilterBinding
import ru.practicum.android.diploma.features.filter.ui.model.AreaResult
import ru.practicum.android.diploma.features.filter.ui.screen.country.widget.CountryAdapter
import ru.practicum.android.diploma.features.filter.ui.screen.country.widget.ItemClickListener
import ru.practicum.android.diploma.core.ui.toolbar.enablePopUp

class CountryFilterFragment : Fragment(R.layout.fragment_country_filter), ItemClickListener {

    private val countryAdapter = CountryAdapter(this)
    private val binding by viewBinding(FragmentCountryFilterBinding::bind)
    private val viewModel by viewModel<CountryFilterViewModel>()
    override fun onItemClick(area: AreaResult) {
        setFragmentResult(
            CountryFilterResult.requestKey,
            Bundle().apply {
                putParcelable(CountryFilterResult.country, area)
            }
        )
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.sync()
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            enablePopUp(toolbar)
            refreshButton.setOnClickListener {
                viewModel.sync()
            }
            filterCountryList.adapter = countryAdapter
        }
        viewModel.state.observe(viewLifecycleOwner, ::updateState)
    }

    private fun updateState(state: ContentState<List<AreaResult>>?) {
        state?.let { uiState ->
            binding.run {
                pb.isVisible = uiState.isLoading
                errorScreen.isVisible = uiState.error != null
            }
            countryAdapter.countryList = uiState.content.orEmpty()
            countryAdapter.notifyDataSetChanged()
        }
    }
}