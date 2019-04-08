task("assemble")
apply { from(rootProject.file("git-hooks.gradle")) }
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath(Libs.com_android_tools_build_gradle)
        classpath(Libs.android_junit5)
        classpath(Libs.kotlin_noarg)
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.ktlint_gradle)
        classpath(Libs.google_services)
    }
}
plugins {
    id("de.fayard.buildSrcVersions") version "0.3.2"
}
plugins.apply("org.jlleitschuh.gradle.ktlint")

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            setUrl("https://plugins.gradle.org/m2/")
        }
    }
}

subprojects {
    plugins.apply("org.jlleitschuh.gradle.ktlint")
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}
