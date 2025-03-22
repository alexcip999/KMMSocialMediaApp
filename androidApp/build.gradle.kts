plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp") version "2.1.10-1.0.31"
    id("kotlinx-serialization")
}
android {
    namespace = "com.example.kmmsocialmediaapp.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.kmmsocialmediaapp.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }

}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.androidx.material3.v120)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3.android)
    debugImplementation(libs.compose.ui.tooling)

    implementation("io.github.raamcosta.compose-destinations:core:1.9.52")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.52")

    implementation("androidx.compose.material3:material3:1.3.0-beta04")
// V2 only: for bottom sheet destination support, also add
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0") // Replace with the desired version

    implementation("androidx.core:core-splashscreen:1.0.0")


    implementation("io.insert-koin:koin-androidx-compose:3.4.1")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    implementation("io.coil-kt:coil-compose:2.7.0")

    implementation("com.google.accompanist:accompanist-swiperefresh:0.31.1-alpha")

}