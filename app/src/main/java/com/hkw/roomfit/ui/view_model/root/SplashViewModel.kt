package com.hkw.roomfit.ui.view_model.root

import android.util.Log
import androidx.lifecycle.ViewModel
import com.hkw.roomfit.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class SplashViewState(
    val deviceState: String = ""
)

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val pref: PreferenceManager
): ViewModel() {

    private val _splashState = MutableStateFlow(SplashViewState())
    val splashState = _splashState.asStateFlow()

    init{
        Log.d("SplashViewModel", "SplashViewModel init")
    }
}