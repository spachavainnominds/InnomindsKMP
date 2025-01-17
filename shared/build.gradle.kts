import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
//    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)

    kotlin("plugin.serialization") version "1.8.0"
}

kotlin {
    jvm()
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.encoding)
                implementation(libs.kotlinx.datetime)

                implementation(libs.slf4j.api)
                implementation(libs.logback.classic)
                implementation(libs.slf4j.api)
                implementation(libs.slf4j.android)

                implementation(libs.ktor.client.cio) // or the latest version
//                implementation(libs.ktor.client.serialization)
//                implementation(libs.ktor.client.serialization)

            }
            // Exclude Logback
            configurations {
                all {
                    exclude(group = "ch.qos.logback")
                }
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }

}
    android {
        namespace = "org.innominds.kmplogin.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        defaultConfig {
            minSdk = libs.versions.android.minSdk.get().toInt()
        }
    }
