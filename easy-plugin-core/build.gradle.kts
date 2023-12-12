plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

gradlePlugin {
    plugins.register("multiplatform-module") {
        id = "ep.multiplatform"
        implementationClass = "williankl.easyplugin.buildSrc.MultiplatformModulePlugin"
    }
    plugins.register("android-app-module") {
        id = "ep.android.app"
        implementationClass = "williankl.easyplugin.buildSrc.AndroidModulePlugin"
    }
    plugins.register("kotlin-module") {
        id = "ep.kotlin"
        implementationClass = "williankl.easyplugin.buildSrc.KotlinModulePlugin"
    }
}

dependencies {
    implementation(libs.plugin.android)
    implementation(libs.plugin.kotlin)
    implementation(libs.plugin.kotlin.serialization)
    implementation(libs.plugin.multiplatform.compose)
    implementation(libs.plugin.ksp)
    implementation(libs.plugin.buildKonfig)
    implementation(libs.plugin.ktlint)
    implementation(libs.plugin.detekt)
}