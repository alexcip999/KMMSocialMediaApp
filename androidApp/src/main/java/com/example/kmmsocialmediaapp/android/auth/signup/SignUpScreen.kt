package com.example.kmmsocialmediaapp.android.auth.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmmsocialmediaapp.android.R
import com.example.kmmsocialmediaapp.android.components.CustomTextField
import com.example.kmmsocialmediaapp.android.theming.ButtonHeight
import com.example.kmmsocialmediaapp.android.theming.ExtraLargeSpacing
import com.example.kmmsocialmediaapp.android.theming.LargeSpacing
import com.example.kmmsocialmediaapp.android.theming.MediumSpacing
import com.example.kmmsocialmediaapp.android.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.theming.appColors

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit,
    onSignUpClick: () -> Unit
){
    val context = LocalContext.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = if (isSystemInDarkTheme()){
                        MaterialTheme.colorScheme.background
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
                .padding(
                    top = ExtraLargeSpacing + LargeSpacing,
                    start = LargeSpacing + MediumSpacing,
                    end = LargeSpacing + MediumSpacing,
                    bottom = LargeSpacing
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LargeSpacing)
        ){
            CustomTextField(
                value = uiState.username,
                onValueChange = onUsernameChange,
                hint = R.string.username_hint
            )

            CustomTextField(
                value = uiState.email,
                onValueChange = onEmailChange,
                hint = R.string.email_hint,
                keyboardType = KeyboardType.Email
            )

            CustomTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                hint = R.string.password_hint,
                keyboardType = KeyboardType.Password,
                isPasswordTextField = true
            )

            Button(
                onClick = {
                    onSignUpClick()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(ButtonHeight),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 0.dp
                ),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.appColors.primary // Custom background color
                )
            ){
                Text(text = stringResource(id = R.string.signup_button_hint), color = Color.White)
            }
        }

        if (uiState.isAuthenticating){
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(
        key1 = uiState.authenticationSucceed,
        key2 = uiState.authErrorMessage,
        block = {
            if (uiState.authenticationSucceed){
                onNavigateToHome()
            }

            if (uiState.authErrorMessage != null){
                Log.d("Here", uiState.authErrorMessage.toString())
                Toast.makeText(context, uiState.authErrorMessage, Toast.LENGTH_SHORT).show()
            }
    })

}

@Preview
@Composable
fun SignUpScreenPreview(){
    SocialAppTheme {
        SignUpScreen(
            uiState = SignUpUiState(),
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToLogin = {},
            onNavigateToHome = {},
            onSignUpClick = {}
        )
    }
}





















