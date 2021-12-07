package co.composecoins.presentation.ui.screens.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.composecoins.domain.models.responces.gecko.CoinEntity
import co.composecoins.domain.repos.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val coinRepository: CoinRepository) : ViewModel() {
    private val _coinsFlow = MutableSharedFlow<List<CoinEntity>>()
    val coinsFlow = _coinsFlow.asSharedFlow()
    val flist = mutableStateOf(listOf<CoinEntity>())
    val searchState = mutableStateOf(String())
    val checkState = mutableStateOf(false)
    private val _favoritesFlow = MutableSharedFlow<List<String>>()
    val favoritesFlow = _favoritesFlow.asSharedFlow()
    fun getCoins() {
        viewModelScope.launch {
            _coinsFlow.emit(coinRepository.getLocalCoins())
        }
    }

    fun getFavoritesIds() {
        viewModelScope.launch {
            coinRepository.getFavorites().collect { list ->
                _favoritesFlow.emit(list.map { it.id })
            }
        }
    }

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