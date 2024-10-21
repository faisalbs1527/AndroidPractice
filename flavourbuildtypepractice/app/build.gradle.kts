import java.io.FileNotFoundException
import java.util.Properties


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

fun getLocalProperties(): Properties {
    val localProperties = Properties()
    try {
        localProperties.load(project.rootProject.file("local.properties").inputStream())
    } catch (ex: FileNotFoundException) {
        ex.printStackTrace()
    }
    return localProperties
}

val devAPI = getLocalProperties().getProperty("BASE_URL")

android {
    namespace = "com.example.flavour_buildtype_practice"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.flavour_buildtype_practice"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    flavorDimensions += listOf("environment", "version")

    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "Flavour", "\"Development\"")
            buildConfigField("String", "BASE_URL", devAPI)
        }
        create("stage") {
            dimension = "environment"
            applicationIdSuffix = ".stage"
            versionNameSuffix = "-stage"
            buildConfigField("String", "Flavour", "\"Staging\"")
            buildConfigField("String", "BASE_URL", "\"https://stage.example.com/api/\"")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "Flavour", "\"Production\"")
            buildConfigField("String", "BASE_URL", "\"https://prod.example.com/api/\"")
        }
        create("qa") {
            dimension = "environment"
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            buildConfigField("String", "Flavour", "\"Testing\"")
            buildConfigField("String", "BASE_URL", "\"https://test.example.com/api/\"")
        }

        //version flavours

        create("free") {
            dimension = "version"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
            buildConfigField("boolean", "IS_PAID_VERSION", "false")
        }
        create("paid") {
            dimension = "version"
            versionNameSuffix = "-paid"
            buildConfigField("boolean", "IS_PAID_VERSION", "true")
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            versionNameSuffix = "-dubug"
        }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}