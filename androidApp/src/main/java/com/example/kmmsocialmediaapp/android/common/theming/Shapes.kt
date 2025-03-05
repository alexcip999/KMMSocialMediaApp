package com.example.kmmsocialmediaapp.android.common.theming

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(SmallSpacing),   // Ex.: 4.dp colțuri rotunjite mici
    medium = RoundedCornerShape(MediumSpacing),  // Ex.: 8.dp pentru mediu
    large = RoundedCornerShape(LargeSpacing)   // Ex.: 12.dp pentru butoane mari sau carduri
)
