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

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    `maven-publish`
    id("digital.wup.android-maven-publish") version "3.6.2"
    id("com.jfrog.bintray") version "1.8.4"
}

version = "0.0.4"

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(25)
        targetSdkVersion(28)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks {
    create<Jar>("sourceJar") {
        from(android.sourceSets["main"].java.srcDirs)
        classifier = "sources"
    }
    create<Javadoc>("javadoc") {
        source(android.sourceSets["main"].java.srcDirs)
        classpath += files(android.bootClasspath.joinToString(File.pathSeparator))
    }
    create<Jar>("javadocJar") {
        val javadoc = project.tasks["javadoc"] as Javadoc
        dependsOn(javadoc)
        from(javadoc.destinationDir)
        classifier = "javadoc"
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["android"])
        groupId = "org.theta4j"
        artifactId = "theta-plugin-sdk"
        version = project.version as String
        artifact(tasks["sourceJar"])
        artifact(tasks["javadocJar"])
        pom {
            name.set("THETA Plug-in SDK")
            description.set("Utilities for RICOH THETA Plug-in API")
            url.set("https://github.com/theta4j/theta-plugin-sdk")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
                developers {
                    developer {
                        name.set("theta4j Project")
                        email.set("info@theta4j.org")
                    }
                }
                scm {
                    url.set("https://github.com/theta4j/theta-plugin-sdk.git")
                }
            }
        }
    }

    repositories.maven {
        url = uri("$buildDir/repo")
    }
}

bintray {
    user = properties["bintray.user"]?.toString().orEmpty()
    key = properties["bintray.key"]?.toString().orEmpty()
    setPublications("maven")
    pkg.apply {
        userOrg = "theta4j"
        repo = "maven"
        name = "theta-plugin-sdk"
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/theta4j/theta-plugin-sdk.git"
        version.apply {
            name = project.version as String
            vcsTag = "v${project.version}"
            gpg.sign = true
            mavenCentralSync.apply {
                sync = true
                user = properties["ossrh.user"]?.toString().orEmpty()
                password = properties["ossrh.password"]?.toString().orEmpty()
            }
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    implementation("com.android.support", "support-annotations", "28.0.0")
}
