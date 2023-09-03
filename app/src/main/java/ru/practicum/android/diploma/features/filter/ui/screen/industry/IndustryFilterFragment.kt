package ru.practicum.android.diploma.features.filter.ui.screen.industry

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.lce.ContentState
import ru.practicum.android.diploma.core.ui.toolbar.enablePopUp
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.databinding.FragmentIndustryFilterBinding
import ru.practicum.android.diploma.features.filter.ui.screen.industry.widget.IndustryAdapter
import ru.practicum.android.diploma.features.filter.ui.screen.industry.widget.IndustryClickListener

class IndustryFilterFragment : Fragment(R.layout.fragment_industry_filter), IndustryClickListener {
    private val countryAdapter = IndustryAdapter(this)
    private val binding by viewBinding(FragmentIndustryFilterBinding::bind)
    private val viewModel by viewModel<IndustryFilterViewModel>()
    private val args: IndustryFilterFragmentArgs by navArgs()
    private lateinit var searchDebounce: (String) -> Unit

    override fun onItemClick(industry: IndustryUi) {
        viewModel.toggleSelection(industry)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchDebounce = debounce(SEARCH_DELAY, lifecycleScope, true) {
            viewModel.filter(it)
        }
        viewModel.sync(args.selectedIndustry)
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            enablePopUp(toolbar)
            refreshButton.setOnClickListener {
                viewModel.sync(args.selectedIndustry)
            }
            filterIndustryList.adapter = countryAdapter
            search.doOnTextChanged { text, start, before, count ->
                searchDebounce(text.toString())
            }
        }
        viewModel.state.observe(viewLifecycleOwner, ::updateState)

    }

    private fun updateState(state: ContentState<IndustryFilterUiState>?) {
        Log.d("custom", state?.error?.stackTraceToString().toString())
        state?.let { uiState ->
            binding.run {
                pb.isVisible = uiState.isLoading
                search.isVisible = uiState.content != null
                errorScreen.isVisible = uiState.error != null
                confirm.isVisible = state.content?.hasChanges == true
                emptyPlaceholder.isVisible = uiState.content?.industryList?.isEmpty() == true
                confirm.setOnClickListener {
                    setFragmentResult(IndustryFilterResult.requestKey, Bundle().apply {
                        putParcelable(
                            IndustryFilterResult.industry,
                            state.content?.selectedIndustry
                        )
                    })
                    findNavController().popBackStack()
                }
            }
            countryAdapter.countryList = uiState.content?.industryList.orEmpty()
            countryAdapter.notifyDataSetChanged()

        }
    }

    private companion object {
        private const val SEARCH_DELAY = 500L
    }
}