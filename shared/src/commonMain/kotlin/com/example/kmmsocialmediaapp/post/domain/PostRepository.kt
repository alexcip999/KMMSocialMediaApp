package com.example.kmmsocialmediaapp.post.domain

import com.example.kmmsocialmediaapp.common.domain.model.Post
import com.example.kmmsocialmediaapp.common.util.Result

interface PostRepository {
    suspend fun getFeedPosts(page: Int, pageSize: Int): Result<List<Post>>

    suspend fun likeOrUnlikePost(postId: Long, shouldLike: Boolean): Result<Boolean>

    suspend fun getUserPosts(userId: Long, page: Int, pageSize: Int): Result<List<Post>>
}