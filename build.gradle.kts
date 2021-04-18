buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        // jcenter allow us to download org.jetbrains.kotlinx:kotlinx-collections-immutable
        // check https://github.com/Kotlin/kotlinx.collections.immutable/issues/61
        jcenter()
    }

    val kotlinVersion = "1.4.0"
    val sqlDelightVersion: String by project

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("com.android.tools.build:gradle:7.0.0-alpha14")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}