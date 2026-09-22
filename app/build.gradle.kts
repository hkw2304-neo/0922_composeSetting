import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

val localProps = Properties()

val localPropFiles = rootProject.file("local.properties")

if(localPropFiles.exists()){
    localProps.load(localPropFiles.inputStream())
}

val webViewPath = localProps.getProperty("WEBVIEW_PATH")


android {
    namespace = "com.hkw.roomfit"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.hkw.roomfit"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String","WEBVIEWPATH" ,"\"${webViewPath}\"")
        }
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            buildConfigField("String","WEBVIEWPATH" ,"\"${webViewPath}\"")
        }
    }

    flavorDimensions += "environment"

    productFlavors {
        create("dev"){
            dimension = "environment"
            applicationIdSuffix = ".debug"
            manifestPlaceholders["appName"] = "hkw_roomFit-Debug"

        }
        create("prod"){
            dimension = "environment"
            applicationIdSuffix = ".release"
            manifestPlaceholders["appName"] = "hkw_roomFit"

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

androidComponents {
    onVariants { variant ->
        val appName = "hkw_roomFit"
        val flavor = variant.flavorName ?: ""
        val buildType = variant.buildType ?: ""

        variant.outputs.forEach { output ->
            if (output is com.android.build.api.variant.impl.VariantOutputImpl) {
                val fileName = "${appName}_${flavor}_${buildType}_v1.0.apk"
                output.outputFileName.set(fileName)
            }
        }
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.foundation)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.coil.compose)
    implementation(libs.photoview)

    implementation(libs.android.pdf.viewer)

    implementation(libs.accompanist.permissions)

    implementation(libs.androidx.datastore.preferences)

}