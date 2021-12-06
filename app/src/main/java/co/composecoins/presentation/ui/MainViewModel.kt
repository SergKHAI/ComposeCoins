package co.composecoins.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.composecoins.domain.repos.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val coinRepository: CoinRepository) : ViewModel() {
    fun updateCoins() {
        viewModelScope.launch {
            coinRepository.updateCoinsList()
        }
    }
}