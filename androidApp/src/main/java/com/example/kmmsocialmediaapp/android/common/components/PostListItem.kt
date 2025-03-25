package com.example.kmmsocialmediaapp.android.common.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kmmsocialmediaapp.android.R
import com.example.kmmsocialmediaapp.android.common.dummy_data.SamplePost
import com.example.kmmsocialmediaapp.android.common.dummy_data.samplePosts
import com.example.kmmsocialmediaapp.android.common.theming.DarkGray
import com.example.kmmsocialmediaapp.android.common.theming.ExtraLargeSpacing
import com.example.kmmsocialmediaapp.android.common.theming.LargeSpacing
import com.example.kmmsocialmediaapp.android.common.theming.LightGray
import com.example.kmmsocialmediaapp.android.common.theming.MediumSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.common.theming.appColors
import com.example.kmmsocialmediaapp.android.common.util.toCurrentUrl
import com.example.kmmsocialmediaapp.common.domain.model.Post

@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostClick: (Post) -> Unit,
    onProfileClick: (userId: Long) -> Unit,
    onLikeClick: (Post) -> Unit,
    onCommnetClick: (Post) -> Unit,
    isDetailScreen: Boolean = false
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.appColors.surface)
            .clickable { onPostClick(post) }
            .padding(bottom = ExtraLargeSpacing)
    ) {
        PostItemHeader(
            name = post.userName,
            profileUrl = post.userImageUrl,
            date = post.createdAt,
            onProfileClick = { onProfileClick(post.userId)}
        )
        Log.d("image", post.imageUrl.toCurrentUrl())
        AsyncImage(
            model = post.imageUrl.toCurrentUrl(),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 1.0f),
            contentScale = ContentScale.Crop,
            placeholder =  if (!isSystemInDarkTheme()){
                painterResource(id = R.drawable.white_background)
            } else {
                painterResource(id = R.drawable._50x350_gray_solid_color_background)
            },
        )

        PostLikesRow(
            likesCount = post.likesCount,
            commentsCount = post.commentsCount,
            onLikeClick = { onLikeClick(post)},
            onCommnetClick = {onCommnetClick(post)}
        )

        Text(
            text = post.caption,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(horizontal = LargeSpacing),
            maxLines = if(isDetailScreen){
                20
            }else{
                2
            },
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.appColors.onSurface
        )
    }
}



@Composable
fun PostItemHeader(
    modifier: Modifier = Modifier,
    name: String,
    profileUrl: String?,
    date: String,
    onProfileClick: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = LargeSpacing,
                vertical = MediumSpacing
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ){
        CircleImage(
            imageUrl = profileUrl?.toCurrentUrl(),
            modifier = modifier.size(30.dp)
        ){
            onProfileClick()
        }

        Text(
            text = name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.appColors.onSurface
        )

        Box(
            modifier = modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(
                    color = if (!isSystemInDarkTheme()){
                        LightGray
                    }else{
                        DarkGray
                    }
                )
        )

        Text(
            text = date,
            style = MaterialTheme.typography.labelMedium.copy(
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = if (!isSystemInDarkTheme()){
                    LightGray
                }else{
                    DarkGray
                }
            ),
            modifier = modifier.weight(1f),
            color = MaterialTheme.appColors.onSurface
        )

        Icon(
            painter = painterResource(id = R.drawable.round_more_horiz),
            contentDescription = null,
            tint = if (!isSystemInDarkTheme()){
                LightGray
            }else{
                DarkGray
            }
        )
    }
}

@Composable
fun PostLikesRow(
    modifier: Modifier = Modifier,
    likesCount: Int,
    commentsCount: Int,
    onCommnetClick: () -> Unit,
    onLikeClick: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 0.dp,
                horizontal = MediumSpacing
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = onLikeClick){
            Icon(
                painter = painterResource(id = R.drawable.like_icon_outlined),
                contentDescription = null,
                tint = if(!isSystemInDarkTheme()){
                    LightGray
                }else{
                    DarkGray
                }
            )
        }

        Text(
            text = "$likesCount",
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 18.sp
            ),
            color = MaterialTheme.appColors.onSurface
        )

        Spacer(modifier = modifier.width(MediumSpacing))

        IconButton(onClick = onCommnetClick){
            Icon(
                painter = painterResource(id = R.drawable.chat_icon_outlined),
                contentDescription = null,
                tint = if(!isSystemInDarkTheme()){
                    LightGray
                }else{
                    DarkGray
                }
            )
        }

        Text(
            text = "$commentsCount",
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 18.sp
            ),
            color = MaterialTheme.appColors.onSurface
        )


    }
}



@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PostListItemPreview(){
    SocialAppTheme {
        Surface(
            color = MaterialTheme.appColors.surface
        ) {
            PostListItem(
                post = samplePosts.first().toDomainPost(),
                onPostClick = {},
                onProfileClick = {},
                onCommnetClick = {},
                onLikeClick = {}
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PostHeaderPreview(){
    SocialAppTheme {
        Surface(
            color = MaterialTheme.appColors.surface
        ) {
            PostItemHeader(
                name = "Mr Dip",
                profileUrl = "",
                date = "20 min",
                onProfileClick = {}
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PostLikesRowPreview(){
    SocialAppTheme {
        Surface(
            color = MaterialTheme.appColors.surface
        ) {
            PostLikesRow(
                likesCount = 12,
                commentsCount = 2,
                onLikeClick = {},
                onCommnetClick = {}
            )
        }
    }
}













