package com.example.kmmsocialmediaapp.android.home.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmmsocialmediaapp.android.R
import com.example.kmmsocialmediaapp.android.common.components.CircleImage
import com.example.kmmsocialmediaapp.android.common.components.FollowsButton
import com.example.kmmsocialmediaapp.android.common.fake_data.FollowsUser
import com.example.kmmsocialmediaapp.android.common.fake_data.sampleUsers
import com.example.kmmsocialmediaapp.android.common.theming.MediumSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SmallSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.common.theming.appColors

@Composable
fun OnBoardingUserItem(
    modifier: Modifier = Modifier,
    followsUser: FollowsUser,
    onUserClick: (FollowsUser) -> Unit,
    isFollowing: Boolean = false,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit
){
    Card (
        modifier = modifier
            .height(140.dp)
            .width(130.dp)
            .clickable { onUserClick(followsUser) },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.appColors.surface
        )
    ){
        Column(
            modifier = modifier.fillMaxSize().padding(MediumSpacing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleImage(
                modifier = modifier.size(50.dp),
                imageUrl = followsUser.profileUrl
            ){
                onUserClick(followsUser)
            }

            Spacer(modifier = modifier.height(SmallSpacing))

            Text(
                text = followsUser.name,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.appColors.onBackground
                )

            Spacer(modifier = modifier.height(MediumSpacing))

            FollowsButton(
                modifier = modifier.fillMaxWidth().height(30.dp),
                text = R.string.follow_text_label,
                onClick = { onFollowButtonClick(!isFollowing, followsUser)},
                isOutLine = true
            )
        }
    }

}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingUserPreview1 (){
    SocialAppTheme {
        OnBoardingUserItem(
            followsUser = sampleUsers.first(),
            onUserClick = {},
            onFollowButtonClick = { _, _ ->}
        )
    }
}

@Preview
@Composable
private fun OnBoardingUserPreview2(){
    SocialAppTheme {
        OnBoardingUserItem(
            followsUser = sampleUsers.first(),
            onUserClick = {},
            onFollowButtonClick = { _, _ ->}
        )
    }
}