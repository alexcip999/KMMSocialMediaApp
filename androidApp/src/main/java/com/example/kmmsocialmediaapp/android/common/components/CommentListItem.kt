package com.example.kmmsocialmediaapp.android.common.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmmsocialmediaapp.android.R
import com.example.kmmsocialmediaapp.android.common.dummy_data.Comment
import com.example.kmmsocialmediaapp.android.common.dummy_data.sampleComments
import com.example.kmmsocialmediaapp.android.common.theming.DarkGray
import com.example.kmmsocialmediaapp.android.common.theming.LargeSpacing
import com.example.kmmsocialmediaapp.android.common.theming.LightGray
import com.example.kmmsocialmediaapp.android.common.theming.MediumSpacing
import com.example.kmmsocialmediaapp.android.common.theming.SocialAppTheme
import com.example.kmmsocialmediaapp.android.common.theming.appColors

@Composable
fun CommentListItem(
    modifier: Modifier = Modifier,
    comment: Comment,
    onProfileClick: (Int) -> Unit,
    onMoreIconClick: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircleImage(
            imageUrl = comment.authorImageUrl,
            modifier = modifier.size(30.dp)
        ){
            onProfileClick(comment.authorId)
        }

        Column(
            modifier = modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MediumSpacing),
            ) {
                Text(
                    text = comment.authorName,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = modifier.alignByBaseline(),
                    color = MaterialTheme.appColors.onSurface
                )

                Text(
                    text = comment.date,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (!isSystemInDarkTheme()){
                        LightGray
                    }else{
                        DarkGray
                    },
                    modifier = modifier.alignByBaseline().weight(1f),

                )

                Icon(
                    painter = painterResource(id = R.drawable.round_more_horiz),
                    contentDescription = null,
                    tint = if (!isSystemInDarkTheme()){
                        LightGray
                    }else{
                        DarkGray
                    },
                    modifier = modifier.clickable { onMoreIconClick() }
                )
            }

            Text(
                text = comment.comment,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.appColors.onSurface
            )
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CommentListItemPreview(){
    SocialAppTheme {
        Surface(color = MaterialTheme.appColors.surface){
            CommentListItem(
                comment = sampleComments.first(),
                onProfileClick = {},
                onMoreIconClick = {}
            )
        }
    }
}