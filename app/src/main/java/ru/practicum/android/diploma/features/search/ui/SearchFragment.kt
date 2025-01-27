package ru.practicum.android.diploma.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.core.navigation.ActionScreen
import ru.practicum.android.diploma.core.navigation.util.goToScreen
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.features.details.ui.DetailsFragment
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.search.domain.models.VacanciesState
import ru.practicum.android.diploma.features.search.presentation.SearchViewModel
import ru.practicum.android.diploma.core.ui.vacancies.VacanciesAdapter

class SearchFragment : Fragment(R.layout.fragment_search) {
    companion object {
        private const val SEARCH_DELAY = 2000L
        const val CODE_LOADING = 0
        const val CODE_NO_INTERNET = -1
        const val CODE_EMPTY = -2
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var searchDebounce: (String) -> Unit
    private lateinit var adapter: VacanciesAdapter
    private var lastText: String? = ""
    private val vacancies = ArrayList<Vacancy>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.filtersLiveData.observe(viewLifecycleOwner) {
            renderFilter(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter =
            VacanciesAdapter { vacancyId ->
                goToScreen(
                    ActionScreen(
                        R.id.action_searchFragment_to_detailsFragment,
                        bundleOf(DetailsFragment.VACANCY_ID_ARG to vacancyId)
                    )
                )
            }
        adapter.vacancies = vacancies
        binding.searchRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecycler.adapter = adapter
        searchDebounce = debounce(SEARCH_DELAY, lifecycleScope, true) {
            if (it.isNotEmpty()) viewModel.getJobs(it)
        }
        binding.crossSearchInput.setOnClickListener {
            binding.searchInput.text.clear()
        }
        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            val trimmedText = text?.toString()?.trim()
            if (trimmedText != lastText) {
                searchDebounce(trimmedText.toString())

                changeUISearch(text.isNullOrEmpty())
            }
            lastText = trimmedText

        }
        binding.filterButton.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionFragmentToFilterNavGraph())
        }
    }

    private fun changeUISearch(textEmpty: Boolean) {
        if (textEmpty) {
            vacancies.clear()
            adapter.notifyDataSetChanged()
            binding.searchRecycler.visibility = View.GONE
            binding.searchPlaceholder.visibility = View.VISIBLE
            binding.crossSearchInput.visibility = View.GONE
            binding.searchLoading.visibility = View.GONE
            binding.jobsFoundLabel.visibility = View.GONE
            binding.searchInput.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.search_icon,
                0
            )
        } else {
            binding.searchRecycler.visibility = View.VISIBLE
            binding.searchPlaceholder.visibility = View.GONE
            binding.crossSearchInput.visibility = View.VISIBLE
            binding.searchLoading.visibility = View.GONE
            binding.searchInput.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        }
    }

    private fun render(state: VacanciesState) {
        when (state.code) {
            200 -> {
                changeVisibilitiesResult(
                    true,
                    if (!state.items.isNullOrEmpty())
                    getEnding(state.items.size)
                     else getString(R.string.no_jobs_found)
                )
                if (state.items != null) {
                    vacancies.clear()
                    vacancies.addAll(state.items)
                    adapter.notifyDataSetChanged()
                }
            }

            CODE_LOADING -> {
                binding.jobsFoundLabel.visibility = View.GONE
                binding.searchLoading.visibility = View.VISIBLE
                binding.searchRecycler.visibility = View.GONE
            }

            CODE_NO_INTERNET -> {
                changeVisibilitiesResult(false, getString(R.string.no_internet))
            }

            403 -> {
                changeVisibilitiesResult(false, getString(R.string.error_403))
            }

            else -> {
                changeVisibilitiesResult(false, getString(R.string.error_else, state.code))
            }
        }
    }

    private fun renderFilter(filter: Filter) {
        if (filter.salary != null
            || filter.industry != null
            || !filter.showNoSalaryItems
            || filter.location?.city != null
            || filter.location?.country != null
        ) binding.filterButton.setImageResource(
            R.drawable.filter_on
        )
    }

    private fun changeVisibilitiesResult(success: Boolean, message: String) {
        binding.searchLoading.visibility = View.GONE
        binding.jobsFoundLabel.visibility = View.VISIBLE
        binding.jobsFoundLabel.text = message
        if (success) {
            binding.searchRecycler.visibility = View.VISIBLE
        } else {
            binding.searchRecycler.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFilters()
        changeUISearch(binding.searchInput.text.isNullOrEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getEnding(count: Int): String{
        return when{
            count in 11..14 -> getString(R.string.num_jobs_found_5_9, count)
            count % 10 in 2..4 -> getString(R.string.num_jobs_found_2_4, count)
            count % 10 == 1 -> getString(R.string.num_jobs_found_1, count)
            else -> getString(R.string.num_jobs_found_5_9, count)
        }
    }
}
