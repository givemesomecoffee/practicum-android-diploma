package ru.practicum.android.diploma.features.filter.ui.screen.workplace

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.getParcelableCompat
import ru.practicum.android.diploma.databinding.FragmentWorkplaceBinding
import ru.practicum.android.diploma.features.filter.ui.screen.country.CountryFilterResult
import ru.practicum.android.diploma.features.filter.ui.screen.region.RegionFilterResult
import ru.practicum.android.diploma.core.ui.toolbar.enablePopUp

class WorkPlaceFragment : Fragment(R.layout.fragment_workplace) {

    private val viewModel by viewModel<WorkPlaceViewModel>()
    private val binding by viewBinding(FragmentWorkplaceBinding::bind)
    private val args: WorkPlaceFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initState(args.country, args.region)
        setFragmentResultListener(CountryFilterResult.requestKey) { requestKey, bundle ->
            viewModel.updateCountry(bundle.getParcelableCompat(CountryFilterResult.country))
            clearFragmentResult(requestKey)
        }
        setFragmentResultListener(RegionFilterResult.requestKey) { requestKey, bundle ->
            viewModel.updateRegion(
                region = bundle.getParcelableCompat(RegionFilterResult.region),
                country = bundle.getParcelableCompat(RegionFilterResult.country)
            )
            clearFragmentResult(requestKey)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::updateView)
        enablePopUp(binding.toolbar)
        binding.country.setOnClickListener {
            findNavController().navigate(WorkPlaceFragmentDirections.actionWorkPlaceFragmentToCountryFilterFragment())
        }
    }

    private fun updateView(uiState: WorkLocationUiState?) {
        uiState?.let { state ->
            binding.apply {
                country.setTitle(state.country?.name) {
                    viewModel.resetCountry()
                }
                region.setTitle(state.region?.name) {
                    viewModel.resetRegion()
                }
                confirm.isVisible = state.hasChanges
                binding.confirm.setOnClickListener {
                    setFragmentResult(
                        WorkPlaceResult.requestKey,
                        Bundle().apply {
                            putParcelable(WorkPlaceResult.country, state.country)
                            putParcelable(WorkPlaceResult.region, state.region)
                        }
                    )
                    findNavController().popBackStack()
                }
                binding.region.setOnClickListener {
                    findNavController().navigate(
                        WorkPlaceFragmentDirections.actionWorkPlaceFragmentToRegionFilterFragment(
                            state.country?.id,
                            state.region
                        )
                    )
                }
            }
        }
    }
}