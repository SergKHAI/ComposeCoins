package co.composecoins.presentation.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.composecoins.domain.models.responces.gecko.CoinEntity
import co.composecoins.domain.repos.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import drewcarlson.coingecko.models.coins.CoinFullData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val coinRepository: CoinRepository) : ViewModel() {
    private val _coinInfo = MutableSharedFlow<CoinFullData?>()
    val coinInfo = _coinInfo.asSharedFlow()
    fun getCoinById(id : String) {
        viewModelScope.launch {
            _coinInfo.emit(coinRepository.getCoinById(id))
        }
    }
}