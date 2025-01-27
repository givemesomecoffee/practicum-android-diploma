package ru.practicum.android.diploma.features.details.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.core.navigation.ActionScreen
import ru.practicum.android.diploma.core.navigation.util.goToScreen
import ru.practicum.android.diploma.databinding.FragmentDetailsBinding
import ru.practicum.android.diploma.features.details.presentation.DetailsScreenState
import ru.practicum.android.diploma.features.details.presentation.DetailsViewModel
import ru.practicum.android.diploma.features.similar.ui.SimilarVacanciesFragment.Companion.SIMILAR_SCREEN_ARG

class DetailsFragment : Fragment() {

    companion object {
        const val VACANCY_ID_ARG = "VACANCY_ID_ARG"
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModel()


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

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        val vacancyId = requireArguments().getString(VACANCY_ID_ARG) ?: ""
        viewModel.getVacancy(vacancyId)

        initListeners(vacancyId)
        initObservers()

    }

    private fun initObservers() {
        viewModel.screenState.observe(viewLifecycleOwner) { screeState ->
            when (screeState) {
                is DetailsScreenState.Filled -> {
                    initUi(screeState.vacancy)
                }

                is DetailsScreenState.Error -> {

                }
            }
        }
    }

    private fun initListeners(vacancyId: String) {
        binding.detailsArrowBack.setOnClickListener {
            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
                View.VISIBLE
            findNavController().navigateUp()
        }

        binding.detailsShare.setOnClickListener {
            viewModel.shareVacancy()
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
                    View.VISIBLE

                findNavController().navigateUp()
            }

        })

        binding.detailsSimilarVacancies.setOnClickListener {
            goToScreen(
                ActionScreen(
                    R.id.action_detailsFragment_to_similarVacanciesFragment,
                    bundleOf(SIMILAR_SCREEN_ARG to vacancyId)
                )
            )
        }
    }

    private fun initUi(vacancy: Vacancy) {
        val scheduleAndEmployment = "${vacancy.employment.name}, ${vacancy.schedule?.name}"

        binding.detailsVacancyName.text = vacancy.name
        binding.detailsVacancySalary.text =
            (vacancy.salary?.pretty(requireContext())
                ?: resources.getString(R.string.no_salary)).toString()
        binding.detailsVacancyCompanyName.text = vacancy.employer.name

        if (vacancy.employer.logoUrls != null) {
            Glide.with(binding.root)
                .load(
                    vacancy.employer.logoUrls.twoHundredAndForty
                        ?: vacancy.employer.logoUrls.original
                )
                .into(binding.detailsVacancyCompanyLogo)
        }

        binding.detailsVacancyCompanyCity.text = vacancy.address?.city ?: vacancy.area.name
        binding.detailsRequiredExperienceValue.text = vacancy.experience?.name
        binding.detailsScheduleAndEmployment.text = scheduleAndEmployment
        binding.detailsVacancyDescriptionValue.text = Html.fromHtml(
            vacancy.description,
            Html.FROM_HTML_MODE_COMPACT
        )

        if (vacancy.keySkills.isNullOrEmpty()) {
            binding.detailsKeySkills.visibility = View.GONE
        } else {
            var keySkills = ""
            vacancy.keySkills.forEach { keySkill ->
                keySkills += "• ${keySkill.name}\n"
            }

            binding.detailsKeySkillsValue.text = keySkills
        }

        if (vacancy.contacts == null) {
            binding.detailsContactsContainer.visibility = View.GONE
        } else {
            binding.detailsContactPersonValue.text = vacancy.contacts.name
            binding.detailsEmailValue.text = vacancy.contacts.email

            binding.detailsEmailValue.setOnClickListener {
                requireActivity().startActivity(Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:" + binding.detailsEmailValue.text)
                })
            }

            if (vacancy.contacts.phones?.isNotEmpty() == true) {
                binding.detailsPhoneNumberValue.text =
                    vacancy.contacts.phones[0].toString()
                binding.detailsPhoneCommentValue.text =
                    vacancy.contacts.phones[0].comment ?: ""

                binding.detailsPhoneNumberValue.setOnClickListener {
                    requireActivity().startActivity(Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:" + binding.detailsPhoneNumberValue.text)
                    })
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}