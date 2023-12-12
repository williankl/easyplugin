package williankl.easyplugin.buildSrc

import williankl.easyplugin.buildSrc.helpers.applyCommonPlugins
import williankl.easyplugin.buildSrc.helpers.applyKotlinOptions
import williankl.easyplugin.buildSrc.helpers.applyRepositories
import williankl.easyplugin.buildSrc.helpers.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("org.jetbrains.kotlin.android")

            applyCommonPlugins()
            applyKotlinOptions()
            applyRepositories()
            setupAndroid()
        }
    }
}