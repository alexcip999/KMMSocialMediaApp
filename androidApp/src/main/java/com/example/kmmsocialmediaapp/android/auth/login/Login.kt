package com.example.kmmsocialmediaapp.android.auth.login

import androidx.compose.runtime.Composable
import com.example.kmmsocialmediaapp.android.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
@Composable
fun Login(
    navigator: DestinationsNavigator
){
    val viewModel: LoginViewModel = koinViewModel()
    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = viewModel::signIn,
        onNavigateToHome = {
            navigator.navigate(HomeScreenDestination)
        }
    )
}