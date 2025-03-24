package com.example.kmmsocialmediaapp.android.home.onboarding

import com.example.kmmsocialmediaapp.android.common.dummy_data.SampleFollowsUser

data class OnBoardingUiState(
    val isLoading: Boolean = false,
    val users: List<SampleFollowsUser> = listOf(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)
