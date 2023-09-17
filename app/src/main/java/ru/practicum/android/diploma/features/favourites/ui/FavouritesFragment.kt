package ru.practicum.android.diploma.features.favourites.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.core.navigation.ActionScreen
import ru.practicum.android.diploma.core.navigation.util.goToScreen
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.databinding.FragmentFavouritesBinding
import ru.practicum.android.diploma.features.details.ui.DetailsFragment
import ru.practicum.android.diploma.features.favourites.presentation.FavoriteFragmentState
import ru.practicum.android.diploma.features.favourites.presentation.FavoritesAdapter
import ru.practicum.android.diploma.features.favourites.presentation.FavoritesViewModel

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private val favoritesViewModel: FavoritesViewModel by viewModel()

    private lateinit var binding: FragmentFavouritesBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoriteVacanciesPlaceholderView: View
    private lateinit var favoriteVacanciesPlaceholderTextView: TextView

    private var favoritesAdapter: FavoritesAdapter = FavoritesAdapter()

    private lateinit var onVacancyClickDebounce: (Vacancy) -> Unit

    companion object {
        private const val CLICK_DEBOUNCE_DELAY_MILLIS = 1000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.content
        favoriteVacanciesPlaceholderView = binding.favsPlaceholder
        favoriteVacanciesPlaceholderTextView = binding.favsPlaceholderText

        favoritesViewModel.favoritesFragmentControl()

        onVacancyClickDebounce =
            debounce(CLICK_DEBOUNCE_DELAY_MILLIS, viewLifecycleOwner.lifecycleScope, false) { vacancy ->
                goToScreen(
                    ActionScreen(
                        R.id.action_favouritesFragment_to_detailsFragment,
                        bundleOf(DetailsFragment.VACANCY_ID_ARG to vacancy.id)
                    )
                )
            }

        favoritesAdapter.setItemClickListener {
            onVacancyClickDebounce(it)
        }

        favoritesViewModel.observeFavoriteFragmentState().observe(viewLifecycleOwner) {
            when (it) {
                is FavoriteFragmentState.Default -> showPlaceholder()
                is FavoriteFragmentState.Content -> showFavoriteVacancies(it.vacancies)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.favoritesFragmentControl()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        favoritesViewModel.favoritesFragmentControl()
    }

    private fun showPlaceholder() {
        favoriteVacanciesPlaceholderTextView.visibility = View.VISIBLE
        favoriteVacanciesPlaceholderView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun showFavoriteVacancies(vacancies: List<Vacancy>) {
        favoriteVacanciesPlaceholderTextView.visibility = View.GONE
        favoriteVacanciesPlaceholderView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        favoritesAdapter.setVacancies(vacancies)
        favoritesAdapter.notifyDataSetChanged()
        recyclerView.adapter = favoritesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}