package ru.practicum.android.diploma.features.details.ui

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
import ru.practicum.android.diploma.core.navigation.ActionScreen
import ru.practicum.android.diploma.core.navigation.util.goToScreen
import ru.practicum.android.diploma.core.utils.openEmailApp
import ru.practicum.android.diploma.core.utils.openPhoneApp
import ru.practicum.android.diploma.core.utils.shareLink
import ru.practicum.android.diploma.databinding.FragmentDetailsBinding
import ru.practicum.android.diploma.features.details.presentation.DetailsScreenState
import ru.practicum.android.diploma.features.details.presentation.DetailsViewModel
import ru.practicum.android.diploma.features.details.presentation.models.VacancyUiModel
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
                    initError()
                }

                is DetailsScreenState.Loading -> {
                    initLoading()
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
            val link = viewModel.getVacancyLink()
            if (link.isNotEmpty()) {
                requireContext().shareLink(link)
            }
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

    private fun initUi(vacancy: VacancyUiModel) {
        binding.detailsMainContainer.visibility = View.VISIBLE
        binding.detailsLoading.visibility = View.GONE
        binding.detailsEmptyLogo.visibility = View.GONE


        val scheduleAndEmployment = "${vacancy.name}, ${vacancy.scheduleName}"

        binding.detailsVacancyName.text = vacancy.name
        binding.detailsVacancySalary.text = vacancy.salary
        binding.detailsVacancyCompanyName.text = vacancy.employerName


        Glide.with(binding.root)
            .load(
                vacancy.employerLogoUrlsTwoHundredAndForty.ifEmpty { vacancy.employerLogoUrlsOriginal }
            )
            .into(binding.detailsVacancyCompanyLogo)


        binding.detailsVacancyCompanyCity.text = vacancy.addressCity
        binding.detailsRequiredExperienceValue.text = vacancy.experienceName
        binding.detailsScheduleAndEmployment.text = scheduleAndEmployment
        binding.detailsVacancyDescriptionValue.text = Html.fromHtml(
            vacancy.description,
            Html.FROM_HTML_MODE_COMPACT
        )

        if (vacancy.keySkills.isEmpty()) {
            binding.detailsKeySkills.visibility = View.GONE
        } else {
            var keySkills = ""
            vacancy.keySkills.forEach { keySkill ->
                keySkills += "• ${keySkill}\n"
            }

            binding.detailsKeySkillsValue.text = keySkills
        }

        if (vacancy.contactsEmail.isEmpty() or vacancy.contactsName.isEmpty()) {
            binding.detailsContactsContainer.visibility = View.GONE
        } else {
            binding.detailsContactPersonValue.text = vacancy.contactsName
            binding.detailsEmailValue.text = vacancy.contactsEmail

            binding.detailsEmailValue.setOnClickListener {
                requireContext().openEmailApp(binding.detailsEmailValue.text.toString())
            }

            if (vacancy.contactPhone.isNotEmpty()) {
                binding.detailsPhoneNumberValue.text = vacancy.contactPhone
                binding.detailsPhoneCommentValue.text = vacancy.contactPhoneComment

                binding.detailsPhoneNumberValue.setOnClickListener {
                    requireContext().openPhoneApp(binding.detailsPhoneNumberValue.text.toString())
                }
            }
        }
    }

    private fun initLoading() {
        binding.detailsLoading.visibility = View.VISIBLE
        binding.detailsMainContainer.visibility = View.GONE
        binding.detailsEmptyLogo.visibility = View.GONE
    }

    private fun initError() {
        binding.detailsEmptyLogo.visibility = View.VISIBLE
        binding.detailsLoading.visibility = View.GONE
        binding.detailsMainContainer.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}