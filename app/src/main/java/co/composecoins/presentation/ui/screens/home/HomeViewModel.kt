package co.composecoins.presentation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.composecoins.domain.models.responces.gecko.CoinEntity
import co.composecoins.domain.repos.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val coinRepository: CoinRepository) : ViewModel() {
    private val _coinsFlow = MutableSharedFlow<List<CoinEntity>>()
    val coinsFlow = _coinsFlow.asSharedFlow()
    fun getCoins() {
        viewModelScope.launch {
            _coinsFlow.emit(coinRepository.getLocalCoins())
        }
    }
}