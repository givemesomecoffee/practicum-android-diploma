package ru.practicum.android.diploma.features.similar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.core.ui.vacancies.VacanciesAdapter
import ru.practicum.android.diploma.databinding.FragmentSimilarBinding
import ru.practicum.android.diploma.features.similar.presentation.SimilarVacanciesScreenState
import ru.practicum.android.diploma.features.similar.presentation.SimilarVacanciesViewModel

class SimilarVacanciesFragment : Fragment() {

    companion object {
        const val SIMILAR_SCREEN_ARG = "SIMILAR_SCREEN_ARG"
    }

    private var _binding: FragmentSimilarBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<SimilarVacanciesViewModel>()
    private lateinit var adapter: VacanciesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimilarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vacancyId = requireArguments().getString(SIMILAR_SCREEN_ARG) ?: ""

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        adapter = VacanciesAdapter { }

        binding.similarList.layoutManager = LinearLayoutManager(requireContext())
        binding.similarList.adapter = adapter

        initObservers()
        viewModel.getSimilarVacancies(vacancyId)
    }

    private fun initObservers() {
        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is SimilarVacanciesScreenState.Empty -> {
                    initEmpty()
                }

                is SimilarVacanciesScreenState.Content -> {
                    initUi(loading = false, vacancies = screenState.vacancies)
                }

                SimilarVacanciesScreenState.Loading -> {
                    initUi(loading = true, vacancies = emptyList())
                }
            }
        }
    }

    private fun initEmpty() {
        binding.similarNotFoundContainer.visibility = View.VISIBLE
        binding.similarLoading.visibility = View.GONE
        binding.similarList.visibility = View.GONE
    }

    private fun initUi(loading: Boolean, vacancies: List<Vacancy>) {
        binding.similarLoading.visibility = if (loading) View.VISIBLE else View.GONE
        binding.similarList.visibility = if (vacancies.isEmpty()) View.GONE else View.VISIBLE


        adapter.vacancies.clear()
        adapter.vacancies.addAll(vacancies)
        adapter.notifyDataSetChanged()

    }
}