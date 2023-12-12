package williankl.easyplugin.buildSrc

import org.gradle.api.Plugin
import org.gradle.api.Project
import williankl.easyplugin.buildSrc.helpers.applyCommonPlugins
import williankl.easyplugin.buildSrc.helpers.applyKotlinOptions
import williankl.easyplugin.buildSrc.helpers.applyRepositories
import williankl.easyplugin.buildSrc.helpers.setupMultiplatformTargets

internal class MultiplatformModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("org.jetbrains.kotlin.multiplatform")
            plugins.apply("com.android.library")

            applyCommonPlugins()
            applyKotlinOptions()
            applyRepositories()
            setupMultiplatformTargets()
        }
    }
}