package williankl.easyplugin.buildSrc

import org.gradle.api.Plugin
import org.gradle.api.Project
import williankl.easyplugin.buildSrc.helpers.applyCommonPlugins
import williankl.easyplugin.buildSrc.helpers.applyKotlinOptions
import williankl.easyplugin.buildSrc.helpers.applyRepositories

internal class KotlinModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("org.jetbrains.kotlin.jvm")

            applyCommonPlugins()
            applyKotlinOptions()
            applyRepositories()
        }
    }
}