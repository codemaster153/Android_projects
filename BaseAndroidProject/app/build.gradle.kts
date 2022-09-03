plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 32
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.lkb.baseandroidproject"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
//    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
//    implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
//    implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
//    implementation("androidx.compose.foundation:foundation:${rootProject.extra["compose_version"]}")
    // Integration with activities
    implementation ("androidx.activity:activity-compose:1.5.1")
    // Compose Material Design
    implementation ("androidx.compose.material:material:1.2.1")
    // Animations
    implementation ("androidx.compose.animation:animation:1.2.1")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui-tooling:1.2.1")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.0-rc01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    // UI Tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.2.1")
    //androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
}