package ru.practicum.android.diploma.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ru.practicum.android.diploma.core.utils.debounce
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding
import ru.practicum.android.diploma.features.search.presentation.SearchViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private companion object{
        const val SEARCH_DELAY = 2000L
    }
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SearchViewModel>()
    private lateinit var searchDebounce: (String)->Unit
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchDebounce = debounce(SEARCH_DELAY, lifecycleScope, true){
            if (it.isNotEmpty()) viewModel.getJobs(it)
        }
        binding.crossSearchInput.setOnClickListener {
            binding.searchInput.text.clear()
        }
        binding.searchInput.doOnTextChanged { text, _, _, _ ->

            searchDebounce(binding.searchInput.text.toString())

            when {
                text.isNullOrEmpty() -> {
                    changeUISearch(true)
                }

                else -> {
                    changeUISearch(false)
                }
            }
        }
    }

    private fun changeUISearch(textEmpty: Boolean) {
        if (textEmpty) {
            binding.searchRecycler.visibility = View.GONE
            binding.searchPlaceholder.visibility = View.VISIBLE
            binding.crossSearchInput.visibility = View.GONE
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
            binding.searchInput.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        }
    }
}
