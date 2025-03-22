package com.example.kmmsocialmediaapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.kmmsocialmediaapp.android.common.components.MyAppBar
import com.example.kmmsocialmediaapp.android.common.theming.appColors
import com.example.kmmsocialmediaapp.android.destinations.HomeDestination
import com.example.kmmsocialmediaapp.android.destinations.LoginDestination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun SocialApp(
    uiState: MainActivityUiState
){
    val navHostController = rememberNavController()
    val snackbarHostState = remember{ SnackbarHostState() }
    val systemUiController = rememberSystemUiController()

    val isSystemInDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemInDark){
        MaterialTheme.appColors.surface
    }else{
        MaterialTheme.appColors.surface.copy(alpha = 0.95f)
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isSystemInDark
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            MyAppBar(modifier = Modifier, navHostController = navHostController)
        }
    ) { innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }

    when(uiState){
        MainActivityUiState.Loading -> {}
        is MainActivityUiState.Success -> {
            LaunchedEffect(key1 = Unit) {
                if (uiState.currentUser.token.isEmpty()){
                    navHostController.navigate(LoginDestination.route){
                        popUpTo(HomeDestination.route){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}