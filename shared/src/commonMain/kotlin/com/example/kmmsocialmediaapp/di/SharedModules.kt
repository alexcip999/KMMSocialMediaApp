package com.example.kmmsocialmediaapp.di

import com.example.kmmsocialmediaapp.auth.data.AuthRepositoryImpl
import com.example.kmmsocialmediaapp.auth.data.AuthService
import com.example.kmmsocialmediaapp.auth.domain.repository.AuthRepository
import com.example.kmmsocialmediaapp.auth.domain.usecase.SignInUseCase
import com.example.kmmsocialmediaapp.auth.domain.usecase.SignUpUseCase
import com.example.kmmsocialmediaapp.common.data.remote.FollowsApiService
import com.example.kmmsocialmediaapp.common.data.remote.PostApiService
import com.example.kmmsocialmediaapp.common.util.provideDispatcher
import com.example.kmmsocialmediaapp.follows.data.FollowsRepositoryImpl
import com.example.kmmsocialmediaapp.follows.domain.FollowsRepository
import com.example.kmmsocialmediaapp.follows.domain.usecase.FollowOrUnfollowUseCase
import com.example.kmmsocialmediaapp.follows.domain.usecase.GetFollowableUsersUseCase
import com.example.kmmsocialmediaapp.post.data.PostRepositoryImpl
import com.example.kmmsocialmediaapp.post.domain.PostRepository
import com.example.kmmsocialmediaapp.post.domain.usecase.GetPostsUseCase
import com.example.kmmsocialmediaapp.post.domain.usecase.LikeOrUnlikePostUseCase
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val postModule = module {
    factory { PostApiService() }
    factory { GetPostsUseCase() }
    factory { LikeOrUnlikePostUseCase() }

    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
}

private val followsModule = module {
    factory { FollowsApiService() }
    factory { GetFollowableUsersUseCase() }
    factory { FollowOrUnfollowUseCase() }

    single<FollowsRepository> { FollowsRepositoryImpl(get(), get(), get()) }
}

fun getSharedModules() = listOf(
    platformModule,
    authModule,
    utilityModule,
    postModule,
    followsModule
)
