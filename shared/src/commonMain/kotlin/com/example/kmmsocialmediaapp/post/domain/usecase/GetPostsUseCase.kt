package com.example.kmmsocialmediaapp.post.domain.usecase

import com.example.kmmsocialmediaapp.common.domain.model.Post
import com.example.kmmsocialmediaapp.common.util.Result
import com.example.kmmsocialmediaapp.post.domain.PostRepository
import org.koin.core.component.KoinComponent

class GetPostsUseCase(
    private val repository: PostRepository
) : KoinComponent {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ) : Result<List<Post>>{
        return repository.getFeedPosts(page, pageSize)
    }
}