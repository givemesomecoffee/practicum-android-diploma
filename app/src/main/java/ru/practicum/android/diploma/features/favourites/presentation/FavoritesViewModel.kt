package ru.practicum.android.diploma.features.favourites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.data.models.Address
import ru.practicum.android.diploma.core.data.models.Area
import ru.practicum.android.diploma.core.data.models.BrandedTemplate
import ru.practicum.android.diploma.core.data.models.Contacts
import ru.practicum.android.diploma.core.data.models.Employer
import ru.practicum.android.diploma.core.data.models.Employment
import ru.practicum.android.diploma.core.data.models.Experience
import ru.practicum.android.diploma.core.data.models.KeySkill
import ru.practicum.android.diploma.core.data.models.LogoUrls
import ru.practicum.android.diploma.core.data.models.Phone
import ru.practicum.android.diploma.core.data.models.ProfessionalRole
import ru.practicum.android.diploma.core.data.models.Salary
import ru.practicum.android.diploma.core.data.models.Schedule
import ru.practicum.android.diploma.core.data.models.Vacancy
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesInteractor

class FavoritesViewModel(
    private val favoriteVacanciesInteractor: FavoriteVacanciesInteractor,
) : ViewModel() {

    private val favoriteFragmentLiveData = MutableLiveData<FavoriteFragmentState>()
    fun observeFavoriteFragmentState(): LiveData<FavoriteFragmentState> = favoriteFragmentLiveData

    fun favoritesFragmentControl() {
        viewModelScope.launch {
            val favoriteVacancies: List<Vacancy> =
                favoriteVacanciesInteractor.getFavoriteVacancies().first()
            if (favoriteVacancies.isEmpty()) {
                favoriteFragmentLiveData.postValue(FavoriteFragmentState.Default)
            } else {
                favoriteFragmentLiveData.postValue(FavoriteFragmentState.Content(favoriteVacancies))
            }
        }
    }

    fun addVacancyToDB() {
        val vacancy: Vacancy = Vacancy(
            "Секретарь",
                Salary(
                    "RUR",
                    30000,
                    true,
                    null
                ),
            Address(
                "Москва"
            ),
            Employer(
                "1455",
                LogoUrls(
                    "https://hh.ru/employer-logo/289169.png",
                    "https://hh.ru/employer-logo/289027.png",
                    "https://hh.ru/file/2352807.png"
                    ),
                "HeadHunter",
                "https://api.hh.ru/employers/1455"
            ),
            Experience(
                "between1And3",
                "От 1 года до 3 лет"
            ),
            Area(
                "1",
                "Москва",
                "https://api.hh.ru/areas/1"
            ),
            Schedule(
                "fullDay",
                "Полный день"
            ),
            "<style>...</style><div>...</div><script></script>",
            BrandedTemplate(
                "1",
                "test"
            ),
            "HRR-3487",
            Contacts(
                "user@example.com",
                "Имя",
                listOf(Phone("985",null,"7", "000-00-00"), Phone("999",null,"7", "111-11-11"))
            ),
            "...",
            Employment(
                "full",
                "Полная занятость"
            ),
            "8331228",
            listOf(KeySkill("Прием посетителей"), KeySkill("Первичный документооборот")),
            listOf(ProfessionalRole("96", "Программист, разработчик"), ProfessionalRole("95", "Бетоноукладчик")),
            "https://hh.ru/vacancy/8331228"
            )
        viewModelScope.launch {
            favoriteVacanciesInteractor.insertIntoFavoriteVacancies(listOf(vacancy))
        }
    }

    fun deleteVacancyFromDB(vacancy: Vacancy) {

        viewModelScope.launch {
            favoriteVacanciesInteractor.deleteFavoriteVacancies(listOf(vacancy))
        }
    }

}