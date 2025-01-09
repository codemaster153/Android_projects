plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 35
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.lkb.baseandroidproject"
        minSdk = 21
        targetSdk = 34
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
    namespace = "com.lkb.baseandroidproject"
}

dependencies {

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    // Integration with activities
    implementation ("androidx.activity:activity-compose:1.9.3")
    // Compose Material Design
    implementation ("androidx.compose.material:material:1.7.5")
    // Animations
    implementation ("androidx.compose.animation:animation:1.7.5")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui-tooling:1.7.5")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    //koin
    implementation ("io.insert-koin:koin-android:3.5.3")
    implementation ("io.insert-koin:koin-androidx-compose:3.5.3")
    //implementation ("io.insert-koin:koin-core:3.5.3")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // UI Tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.7.5")

    implementation("androidx.navigation:navigation-compose:2.8.4")

}