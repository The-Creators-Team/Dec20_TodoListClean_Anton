package com.creators.dec20_todolistclean_anton.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creators.dec20_todolistclean_anton.data.firebase.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepositoryImpl
) : ViewModel() {
    private val _signUpSuccess = MutableStateFlow(false)
    val signUpSuccess = _signUpSuccess.asStateFlow()

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = authRepository.signUpWithEmail(email, password)
            _signUpSuccess.value = result
        }
    }
}
