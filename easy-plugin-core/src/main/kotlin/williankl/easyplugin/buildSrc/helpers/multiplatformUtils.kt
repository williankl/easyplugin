package williankl.easyplugin.buildSrc.helpers

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

public fun Project.addJvmTarget() {
    setupMultiplatformTargets(withJvm = true)
}

public fun Project.setupMultiplatformTargets(
    withJvm: Boolean = false,
) {
    if (withJvm) applyJvmTarget()
    applyAndroidTarget()
    applyIOSTarget()
    setDependencies(withJvm)
}

private fun Project.applyAndroidTarget() {
    findAndroidExtension().apply {
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        setupAndroid()
    }

    extensions.configure<KotlinMultiplatformExtension> {
        androidTarget()
    }
}

private fun Project.applyJvmTarget() {
    extensions.configure<KotlinMultiplatformExtension> {
        jvm("jvm")
    }
}

private fun Project.applyIOSTarget() {
    extensions.configure<KotlinMultiplatformExtension> {
        applyDefaultHierarchyTemplate()
        iosX64()
        iosArm64()
    }
}

private fun Project.setDependencies(withJvm: Boolean) {
    extensions.configure<KotlinMultiplatformExtension> {
        sourceSets {
            val commonMain by getting

            if (withJvm) {
                val jvmMain by getting {
                    dependsOn(commonMain)
                }
            }

            val androidMain by getting {
                dependsOn(commonMain)
            }

            val iosMain by getting {
                dependsOn(commonMain)
            }
        }
    }
}