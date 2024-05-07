plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
}

android {
    namespace = "com.example.fragmentscenarioexperiment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fragmentscenarioexperiment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.preference.ktx)
    implementation(libs.androidx.rules)
    kotlin("kapt")
    implementation("com.google.dagger:dagger:2.47")
    implementation("com.google.dagger:dagger-android:2.47")
    implementation("com.google.dagger:dagger-android-support:2.47")
    kapt("com.google.dagger:dagger-compiler:2.47")
    kapt("com.google.dagger:dagger-android-processor:2.47")
    kaptAndroidTest("com.google.dagger:dagger-compiler:2.47")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation("androidx.fragment:fragment-testing:1.7.0")
    debugImplementation("androidx.test:core:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")


}