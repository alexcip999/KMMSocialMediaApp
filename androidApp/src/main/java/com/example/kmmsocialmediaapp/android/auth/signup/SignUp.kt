package com.example.kmmsocialmediaapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.example.kmmsocialmediaapp.android.auth.login.LoginScreen
import com.example.kmmsocialmediaapp.android.auth.login.LoginViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination(start = true)
@Composable
fun SignUp(
    navigator: DestinationsNavigator
){
    val viewModel: SignUpViewModel = koinViewModel()
    SignUpScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onUsernameChange = viewModel::updateUsername,
        onPasswordChange = viewModel::updatePassword
    )
}