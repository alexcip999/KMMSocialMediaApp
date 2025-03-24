package com.example.kmmsocialmediaapp.android.account.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmmsocialmediaapp.android.R
import com.example.kmmsocialmediaapp.android.common.components.CircleImage
import com.example.kmmsocialmediaapp.android.common.components.FollowsButton
import com.example.kmmsocialmediaapp.android.common.components.PostListItem
import com.example.kmmsocialmediaapp.android.common.dummy_data.SamplePost
import com.example.kmmsocialmediaapp.android.common.theming.LargeSpacing
import com.example.kmmsocialmediaapp.android.common.theming.MediumSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SmallSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.common.theming.appColors

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    userInfoUiState: UserInfoUiState,
    profilePostsUiState: ProfilePostsUiState,
    onButtonClick: () -> Unit,
    onFollowersClick: () -> Unit,
    onFollowingClick: () -> Unit,
    onPostClick: (SamplePost) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    fetchData: () -> Unit
){
    if (userInfoUiState.isLoading && profilePostsUiState.isLoading){
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            item(key = "header_section"){
                ProfileHeaderSection(
                    imageUrl = userInfoUiState.profile?.profileUrl ?: "",
                    name = userInfoUiState.profile?.name ?: "",
                    bio = userInfoUiState.profile?.bio ?: "",
                    followersCount = userInfoUiState.profile?.followersCount ?: 0,
                    followingCount = userInfoUiState.profile?.followingCount ?: 0,
                    onButtonClick = onButtonClick,
                    onFollowersClick = onFollowersClick,
                    onFollowingClick = onFollowingClick,
                )
            }

            items(
                items = profilePostsUiState.posts,
                key = {post -> post.id}
            ){
                PostListItem(
                    post = it,
                    onPostClick = onPostClick,
                    onProfileClick = {},
                    onLikeClick = {},
                    onCommnetClick = {},
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit){
        fetchData()
    }
}

@Composable
fun ProfileHeaderSection(
    modifier: Modifier = Modifier,
    imageUrl: String,
    name: String,
    bio: String,
    followersCount: Int,
    followingCount: Int,
    isCurrentUser: Boolean = false,
    isFollowing: Boolean = false,
    onButtonClick: () -> Unit,
    onFollowersClick: () -> Unit,
    onFollowingClick: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = MediumSpacing)
            .background(color = MaterialTheme.appColors.surface)
            .padding(all = LargeSpacing)
    ) {
        CircleImage(
            modifier = modifier.size(90.dp),
            imageUrl = imageUrl,
            onClick = {}
        )

        Spacer(modifier = modifier.height(SmallSpacing))

        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.appColors.onSurface
        )

        Text(
            text = bio,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.appColors.onSurface
        )

        Spacer(modifier = modifier.height(MediumSpacing))

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = modifier.weight(1f)
            ) {
                FollowsText(
                    count = followersCount,
                    text = R.string.followers_text,
                    onClick = onFollowersClick
                )

                Spacer(modifier = modifier.width(MediumSpacing))

                FollowsText(
                    count = followingCount,
                    text = R.string.following_text,
                    onClick = onFollowingClick
                )
            }

            FollowsButton(
                text = R.string.follow_text_label,
                onClick = onButtonClick,
                modifier = modifier
                    .height(30.dp)
                    .widthIn(100.dp),
                isOutLine = isCurrentUser || isFollowing,
            )
        }


    }
}

@Composable
fun FollowsText(
    modifier: Modifier = Modifier,
    count: Int,
    @StringRes text: Int,
    onClick: () -> Unit
){
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.appColors.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            ){
                append(text = "$count ")
            }

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.appColors.onSurface.copy(alpha = 0.54f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            ){
                append(text = stringResource(id = text))
            }
        },
        modifier = modifier.clickable { onClick() }
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileHeaderPreview(){
    SocialAppTheme {
        Surface(
            color = MaterialTheme.appColors.surface
        ) {
            ProfileHeaderSection(
                imageUrl = "",
                name = "Mr Dip",
                bio = "Hey there, welcome to Mr Dip Coding page",
                followersCount = 9,
                followingCount = 2,
                onFollowersClick = { /*TODO*/ },
                onFollowingClick = {/*TODO*/},
                onButtonClick = { /*TODO*/ },
            )
        }
    }
}









