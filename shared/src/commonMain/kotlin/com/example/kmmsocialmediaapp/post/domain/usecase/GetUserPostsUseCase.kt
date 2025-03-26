package com.example.kmmsocialmediaapp.post.domain.usecase

import com.example.kmmsocialmediaapp.common.domain.model.Post
import com.example.kmmsocialmediaapp.common.util.Result
import com.example.kmmsocialmediaapp.post.domain.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetUserPostsUseCase : KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(page: Int, pageSize: Int, userId: Long): Result<List<Post>> {
        return repository.getUserPosts(userId = userId, page = page, pageSize = pageSize)
    }
}