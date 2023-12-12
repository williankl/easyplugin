package williankl.easyplugin.buildSrc.helpers

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType

public fun Project.setupAndroidApp(
    debugIdSuffix: String = ".stg",
    applicationId: String,
) {
    configure<BaseExtension> {
        buildTypes.getByName("debug").apply {
            applicationIdSuffix = debugIdSuffix
        }

        buildTypes.getByName("release").apply {
            minifyEnabled(true)
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }

        defaultConfig {
            this.applicationId = applicationId
            versionCode = retrieveVersionFromCatalogs("android-versionCode").toInt()
            versionName = retrieveVersionFromCatalogs("android-versionName")
        }
    }
}

public fun Project.applyAndroidCompose() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val composeCompilerVersion = libs.findVersion("compose-compiler").get().requiredVersion

    configure<BaseExtension> {
        buildFeatures.apply {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = composeCompilerVersion
        }
    }
}

internal fun Project.setupAndroid() {
    configure<BaseExtension> {
        val (compileSdkVersion, targetSdkVersion, minSdkVersion) = Triple(
            retrieveVersionFromCatalogs("android-compileSdk").toInt(),
            retrieveVersionFromCatalogs("android-targetSdk").toInt(),
            retrieveVersionFromCatalogs("android-minSdk").toInt(),
        )

        compileSdkVersion(compileSdkVersion)

        viewBinding {
            isEnabled = true
        }

        defaultConfig {
            minSdk = minSdkVersion
            targetSdk = targetSdkVersion
            vectorDrawables.useSupportLibrary = true
            renderscriptTargetApi = targetSdkVersion
            renderscriptSupportModeEnabled = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}

internal fun Project.findAndroidExtension(): BaseExtension =
    extensions.findByType<LibraryExtension>()
        ?: extensions.findByType<com.android.build.gradle.AppExtension>()
        ?: error("Could not found Android application or library plugin applied on module $name")