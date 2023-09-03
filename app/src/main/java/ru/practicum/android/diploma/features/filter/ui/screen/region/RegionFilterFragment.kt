package ru.practicum.android.diploma.features.filter.ui.screen.region

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.lce.ContentState
import ru.practicum.android.diploma.databinding.FragmentRegionFilterBinding
import ru.practicum.android.diploma.features.filter.ui.screen.region.model.RegionUi
import ru.practicum.android.diploma.features.filter.ui.screen.region.widget.RegionAdapter
import ru.practicum.android.diploma.features.filter.ui.screen.region.widget.RegionClickListener
import ru.practicum.android.diploma.core.ui.toolbar.enablePopUp

class RegionFilterFragment : Fragment(R.layout.fragment_region_filter), RegionClickListener {
    private val countryAdapter = RegionAdapter(this)
    private val binding by viewBinding(FragmentRegionFilterBinding::bind)
    private val viewModel by viewModel<RegionFilterViewModel>()
    private val args: RegionFilterFragmentArgs by navArgs()
    override fun onItemClick(region: RegionUi) {
        viewModel.toggleSelection(region)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.sync(args.countryId, args.selectedRegion)
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            enablePopUp(toolbar)
            refreshButton.setOnClickListener {
                viewModel.sync(args.countryId, args.selectedRegion)
            }
            filterRegionList.adapter = countryAdapter
        }
        viewModel.state.observe(viewLifecycleOwner, ::updateState)

    }

    private fun updateState(state: ContentState<RegionFilterUiState>?) {
        state?.let { uiState ->
            binding.run {
                pb.isVisible = uiState.isLoading
                errorScreen.isVisible = uiState.error != null
                confirm.isVisible = state.content?.hasChanges == true
                confirm.setOnClickListener {
                    setFragmentResult(RegionFilterResult.requestKey, Bundle().apply {
                        putParcelable(
                            RegionFilterResult.region,
                            state.content?.selectedRegion?.region
                        )
                        putParcelable(
                            RegionFilterResult.country,
                            state.content?.selectedRegion?.country
                        )
                    })
                    findNavController().popBackStack()
                }
            }
            countryAdapter.countryList = uiState.content?.regionList.orEmpty()
            countryAdapter.notifyDataSetChanged()

        }
    }
}