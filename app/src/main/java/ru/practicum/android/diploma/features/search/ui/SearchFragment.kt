package ru.practicum.android.diploma.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ru.practicum.android.diploma.core.utils.debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.features.search.data.dto.VacanciesState
import ru.practicum.android.diploma.features.search.presentation.SearchViewModel
import ru.practicum.android.diploma.features.search.presentation.VacanciesAdapter

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = VacanciesAdapter {  }
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

                when {
                    text.isNullOrEmpty() -> {
                        changeUISearch(true)
                    }

                    else -> {
                        changeUISearch(false)
                    }
                }
            }
            lastText = trimmedText

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
                    if (!state.items.isNullOrEmpty()) getString(
                        R.string.num_jobs_found,
                        state.items.size
                    ) else getString(R.string.no_jobs_found)
                )
                if (state.items != null){
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

            403 -> {
                changeVisibilitiesResult(false, getString(R.string.error_403))
            }

            else -> {
                changeVisibilitiesResult(false, getString(R.string.error_else, state.code))
            }
        }
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
}
