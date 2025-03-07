package com.example.kmmsocialmediaapp.android.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.kmmsocialmediaapp.android.R
import com.example.kmmsocialmediaapp.android.destinations.LoginDestination
import com.example.kmmsocialmediaapp.android.destinations.SignUpDestination
import com.example.kmmsocialmediaapp.android.common.theming.SmallElevation
import com.example.kmmsocialmediaapp.android.common.theming.appColors
import com.example.kmmsocialmediaapp.android.destinations.HomeDestination
//import com.example.kmmsocialmediaapp.android.destinations.PostDetailDestination
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
){
    val currentDestination = navHostController.currentDestinationAsState().value

    Surface(
        modifier = modifier,
        tonalElevation = SmallElevation
    ) {
        TopAppBar(
            title = {
                if (currentDestination != null) {
                    Text(
                        text = stringResource(id = getAppBatTitle(currentDestination.route))
                    )
                }
            },
            modifier = modifier,
            colors =  TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.appColors.surface
            ),
            actions = {
                AnimatedVisibility(visible = currentDestination?.route == HomeDestination.route){
                    IconButton(
                        onClick = { TODO() }
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.account_circle_icon),
                            contentDescription = null
                        )
                    }
                }
            },
            navigationIcon = {
                if (shouldShowNavigationIcon(currentDestination?.route)){
                    run {
                        IconButton(onClick = { navHostController.navigateUp() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_arrow_back),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        )
    }
}

private fun getAppBatTitle(currentDestinationRoute: String?): Int{
    return when (currentDestinationRoute){
        LoginDestination.route -> R.string.login_destination_title
        SignUpDestination.route -> R.string.signup_destination_title
        HomeDestination.route -> R.string.home_destination_title
        //PostDetailDestination.route -> R.string.post_detail_destination_title
        else -> R.string.no_destination_title
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return !(currentDestinationRoute == LoginDestination.route
            || currentDestinationRoute == SignUpDestination.route
            || currentDestinationRoute == HomeDestination.route
            || currentDestinationRoute == null
            )
}