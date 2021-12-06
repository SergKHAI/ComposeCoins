package co.composecoins.other.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Fragment.observeFlow(stateFlow: Flow<T>, onResult: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateFlow.collect {
                onResult(it)
            }
        }
    }
}
fun <T> FragmentActivity.observeFlow(stateFlow: Flow<T>, onResult: (T) -> Unit) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateFlow.collect {
                onResult(it)
            }
        }
    }
}