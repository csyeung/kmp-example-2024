import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.apollo)
}

kotlin {
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
            baseName = "ComposeApp"
            binaryOption("bundleId", "com.jonathan.sample2024")
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.apollo.graphql)
            implementation(libs.koin)
            implementation(libs.koin.viewmodel)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
        }
    }
}

android {
    namespace = "com.jonathan.sample2024"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.jonathan.sample2024"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

apollo {
    service("countries") {
        srcDir("src/commonMain/graphql/countries")
        packageName.set("com.jonathan.sample2024.graphql.countries")
        introspection {
            endpointUrl.set("https://review.kakomon.me/graphql")
            schemaFile.set(file("src/commonMain/graphql/countries/schema.graphqls"))
        }
        generateApolloMetadata.set(true)
        alwaysGenerateTypesMatching.set(listOf(".*"))
    }
    service("kakomon") {
        srcDir("src/commonMain/graphql/kakomon")
        packageName.set("com.jonathan.sample2024.graphql.kakomon")
        introspection {
            endpointUrl.set("https://review.kakomon.me/graphql")
            schemaFile.set(file("src/commonMain/graphql/kakomon/schema.graphqls"))
        }
        generateApolloMetadata.set(true)
        alwaysGenerateTypesMatching.set(listOf(".*"))
    }
}

