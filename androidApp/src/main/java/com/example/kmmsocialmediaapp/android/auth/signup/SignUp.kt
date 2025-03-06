package com.example.kmmsocialmediaapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.example.kmmsocialmediaapp.android.destinations.HomeDestination
import com.example.kmmsocialmediaapp.android.destinations.LoginDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination
@Composable
fun SignUp(
    navigator: DestinationsNavigator
){
    val viewModel: SignUpViewModel = koinViewModel()
    SignUpScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onUsernameChange = viewModel::updateUsername,
        onPasswordChange = viewModel::updatePassword,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination)
        },
        onNavigateToHome = {
            navigator.navigate(HomeDestination)
        },
        onSignUpClick = viewModel::signUp
    )
}