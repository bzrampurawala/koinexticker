plugins {
    id("com.android.application")
    id("de.mannodermaus.android-junit5")
    kotlin("android.extensions")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.example.koinexticker"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        isCheckAllWarnings = true
    }
}
kapt {
    generateStubs = true
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.dagger)
    kapt(Libs.dagger_compiler)
    compileOnly("javax.annotation:jsr250-api:1.0")

    implementation(Libs.annotation)
    implementation(Libs.appcompat)
    implementation(Libs.androidx_core_core)
    implementation(Libs.constraintlayout)
    implementation(Libs.material)
    implementation(Libs.fragment)
    implementation(Libs.fragment_ktx)

    implementation(Libs.mvrx)

    implementation(Libs.lifecycle_extensions)
    kapt(Libs.lifecycle_compiler)
    implementation(Libs.room_runtime)
    implementation(Libs.room_rxjava2)
    implementation(Libs.autodispose)
    kapt(Libs.room_compiler)

    implementation(Libs.okhttp)
    implementation(Libs.okio)
    implementation(Libs.retrofit)
    implementation(Libs.converter_moshi)
    implementation(Libs.adapter_rxjava2)
    implementation(Libs.retrofit2_kotlin_coroutines_adapter)
    implementation(Libs.rxandroid)
    implementation(Libs.rxjava)
    implementation(Libs.rxbinding)
    implementation(Libs.rxbinding_support_v4)
    implementation(Libs.rxbinding_design)
    implementation(Libs.timber)
    debugImplementation(Libs.logging_interceptor)
    debugImplementation(Libs.retrofit_mock)
    implementation(Libs.lottie)
    debugImplementation(Libs.stetho)
    debugImplementation(Libs.stetho_okhttp3)
    debugImplementation(Libs.stetho_timber)

    testImplementation(Libs.junit_jupiter_api)
    testRuntimeOnly(Libs.junit_jupiter_engine)
    testImplementation(Libs.junit_jupiter_params)
    testImplementation(Libs.rxrelay)
    testImplementation(Libs.robolectric)
    testImplementation(Libs.mockwebserver)
    testImplementation(Libs.mockk)
    testImplementation(Libs.assertj_core)
}
