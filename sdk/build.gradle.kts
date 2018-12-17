/*
 * Copyright (C) 2018 theta4j project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.config.KotlinCompilerVersion
import java.util.Date

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    `maven-publish`
    id("digital.wup.android-maven-publish") version "3.6.2"
    id("com.jfrog.bintray") version "1.8.4"
}

version = "0.0.2"

android {
    compileSdkVersion(25)
    defaultConfig {
        minSdkVersion(25)
    }
    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

tasks {
    create<Jar>("sourceJar") {
        from(android.sourceSets["main"].java.srcDirs)
        classifier = "sources"
    }
    create<Javadoc>("javadoc") {
        source(android.sourceSets["main"].java.srcDirs)
        classpath += project.files(android.bootClasspath.joinToString(File.pathSeparator))
    }
    create<Jar>("javadocJar") {
        val javadoc = tasks["javadoc"] as Javadoc
        dependsOn(javadoc)
        from(javadoc.destinationDir)
        classifier = "javadoc"
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setPublications("maven")
    pkg.apply {
        userOrg = "theta4j"
        repo = "maven"
        name = "theta-plugin-sdk"
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/theta4j/theta-plugin-sdk.git"
        version.apply {
            name = project.version as String
            released = Date().toString()
            vcsTag = "v${project.version}"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["android"])
            groupId = "org.theta4j"
            artifactId = "theta-plugin-sdk"
            version = project.version as String
            artifact(tasks["sourceJar"])
            artifact(tasks["javadocJar"])
        }
    }

    repositories {
        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri("$buildDir/repo")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation("com.android.support", "support-annotations", "28.0.0")
}
