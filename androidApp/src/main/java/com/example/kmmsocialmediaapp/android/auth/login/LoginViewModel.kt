package com.example.kmmsocialmediaapp.android.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmmsocialmediaapp.android.common.datastore.UserSettings
import com.example.kmmsocialmediaapp.android.common.datastore.toUserSettings
import com.example.kmmsocialmediaapp.auth.domain.usecase.SignInUseCase
import com.example.kmmsocialmediaapp.common.util.Result
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInUseCase: SignInUseCase,
    private val dataStore: DataStore<UserSettings>
): ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun signIn(){
        viewModelScope.launch {
            uiState = uiState.copy(
                isAuthenticating = true
            )

            val authResultData = signInUseCase(uiState.email, uiState.password)

            uiState = when(authResultData){
                is Result.Error -> {
                    uiState.copy(
                        isAuthenticating =  false,
                        authErrorMessage = authResultData.message
                    )
                }
                is Result.Success -> {
                    dataStore.updateData {
                        authResultData.data!!.toUserSettings()
                    }
                    uiState.copy(
                        isAuthenticating = false,
                        authenticationSucceed = true
                    )
                }
            }
        }
    }

    fun updateEmail(input: String){
        uiState = uiState.copy(email = input)
    }

    fun updatePassword(input: String){
        uiState = uiState.copy(password = input)
    }
}

data class LoginUiState(
    var email: String = "",
    var password: String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)