/*
 * Copyright (C) 2018 theta4j project
 */

import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(25)
    defaultConfig {
        minSdkVersion(25);
    }
    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

dependencies {
    implementation(project(":sdk"))
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
}
