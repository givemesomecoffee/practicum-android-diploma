package ru.practicum.android.diploma.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.FragmentSearchBinding

class SearchFragment: Fragment(R.layout.fragment_search) {
//    private val viewModel by viewModel<SearchViewModel>()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.crossSearchInput.setOnClickListener {
            binding.searchInput.text.clear()
        }
        binding.searchInput.doOnTextChanged { text, _, _, _ ->
            when{
                text.isNullOrEmpty()-> {
                    binding.searchRecycler.visibility = View.GONE
                    binding.searchPlaceholder.visibility = View.VISIBLE
                    binding.crossSearchInput.visibility = View.GONE
                    binding.searchInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.search_icon, 0)

                }
                else -> {
                    binding.searchRecycler.visibility = View.VISIBLE
                    binding.searchPlaceholder.visibility = View.GONE
                    binding.crossSearchInput.visibility = View.VISIBLE
                    binding.searchInput.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)

                }
            }
        }
    }
}