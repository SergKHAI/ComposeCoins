package co.composecoins.presentation.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.composecoins.domain.models.responces.gecko.CoinMarketEntity
import co.composecoins.domain.repos.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val coinRepository: CoinRepository) :
    ViewModel() {

    fun getFavorites() = coinRepository.getFavorites()

    fun addFavorite(id: String) {
        viewModelScope.launch {
            coinRepository.addFavorite(id)
        }
    }

    fun removeFavorite(id: String) {
        viewModelScope.launch {
            coinRepository.deleteFavorite(id)
        }
    }
}