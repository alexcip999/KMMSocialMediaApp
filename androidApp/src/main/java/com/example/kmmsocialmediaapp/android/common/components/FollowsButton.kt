package com.example.kmmsocialmediaapp.android.common.components

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmmsocialmediaapp.android.common.theming.Shapes
import com.example.kmmsocialmediaapp.android.common.theming.appColors

@Composable
fun FollowsButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    onClick: () -> Unit,
    isOutLine: Boolean = false
){
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = if (isOutLine){
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.appColors.primary
            )
        }else{
            ButtonDefaults.buttonColors()
        },
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp
        ),
        shape = Shapes.medium

    ){
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 12.sp
            ),
            color = MaterialTheme.appColors.surface
        )
    }
}