package ru.practicum.android.diploma.features.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.practicum.android.diploma.databinding.FragmentDetailsBinding
import ru.practicum.android.diploma.features.details.presentation.DetailsScreenState
import ru.practicum.android.diploma.features.details.presentation.DetailsViewModel

class DetailsFragment : Fragment() {

    companion object {
        const val VACANCY_ID_ARG = "VACANCY_ID_ARG"
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vacancyId = requireArguments().getString(VACANCY_ID_ARG) ?: ""
        viewModel.getVacancy(vacancyId)


        viewModel.screenState.observe(viewLifecycleOwner) { screeState ->
            when (screeState) {
                is DetailsScreenState.Filled -> {

                }

                is DetailsScreenState.Error -> {

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}