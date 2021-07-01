import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.2"

    defaultConfig {
        applicationId = "com.lkb.assignment"
        minSdkVersion(22)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "https://jsonplaceholder.typicode.com")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Moshi
    implementation("com.squareup.moshi:moshi:${Versions.moshi_version}")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi_version}")
    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.0")
//
//    //Koin
    implementation ("org.koin:koin-core:${Versions.koin_version}")
//    implementation "org.koin:koin-android:$koin_version"
//    implementation "org.koin:koin-android-viewmodel:$koin_version"
//    implementation "org.koin:koin-android-architecture:$koin_architecture_version"
//    testImplementation "org.koin:koin-test:$koin_version"
//    //UI
//    implementation 'androidx.cardview:cardview:1.0.0'
//    implementation 'androidx.recyclerview:recyclerview:1.1.0'
//    implementation 'androidx.core:core-ktx:1.3.2'
//    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
//
//    //Coroutines
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
//    //ViewModel
//    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_viewmodel_version"
//
//    implementation 'com.squareup.okhttp3:logging-interceptor:4.8.1'
//    implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
//    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
//    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
//    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
}