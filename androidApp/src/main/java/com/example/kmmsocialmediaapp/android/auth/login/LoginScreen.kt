package com.example.kmmsocialmediaapp.android.auth.login

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.kmmsocialmediaapp.android.common.components.CustomTextField
import com.example.kmmsocialmediaapp.android.common.theming.ButtonHeight
import com.example.kmmsocialmediaapp.android.common.theming.ExtraLargeSpacing
import com.example.kmmsocialmediaapp.android.common.theming.LargeSpacing
import com.example.kmmsocialmediaapp.android.common.theming.MediumSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SmallSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.common.theming.appColors
import com.ramcosta.composedestinations.annotation.Destination

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNavigateToHome: () -> Unit,
    onSignInClick: () -> Unit,
    onNavigateToSignup: () -> Unit
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
                    onSignInClick()
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
                Text(text = stringResource(id = R.string.login_button_label), color = Color.White)
            }

            GoToSignUp {
                onNavigateToSignup()
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
                Toast.makeText(context, uiState.authErrorMessage, Toast.LENGTH_SHORT).show()
            }
        })

}

@Composable
fun GoToSignUp(
    modifier: Modifier = Modifier,
    onNavigateToSignup: () -> Unit
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            SmallSpacing
        )
    ){
        Text(
            text = "Don't have an account?",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.appColors.onSurface
            )
        Text(
            text = "SignUp",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.appColors.primary,
            modifier = modifier.clickable { onNavigateToSignup() }
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview(){
    SocialAppTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onNavigateToHome = {},
            onSignInClick = {},
            onNavigateToSignup = {}
        )
    }
}