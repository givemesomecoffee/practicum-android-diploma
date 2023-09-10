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

    fun removeVacancyFromFavorites(vacancy: Vacancy) {
        viewModelScope.launch {
            favoriteVacanciesInteractor.deleteFavoriteVacancies(listOf(vacancy))
        }
        favoritesFragmentControl()
    }

}