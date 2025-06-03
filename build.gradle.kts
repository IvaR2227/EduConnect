// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    // We need to explicitly define repositories here for buildscript dependencies
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    
    dependencies {
        classpath("com.android.tools.build:gradle:8.10.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.0")
        classpath("com.google.gms:google-services:4.4.0")
    }
} 