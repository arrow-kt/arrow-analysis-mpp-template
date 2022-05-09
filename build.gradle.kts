plugins {
  kotlin("multiplatform") version "1.6.21" apply true
  id("io.kotest.multiplatform") version "5.3.0" apply true
}

group "org.example"
version "1.0"

repositories {
  mavenCentral()
}

buildscript {
  dependencies {
    classpath("io.arrow-kt.analysis.kotlin:io.arrow-kt.analysis.kotlin.gradle.plugin:2.0.2-alpha.20")
  }
}

kotlin {
  jvm()

  /* js(IR) {
    browser()
    nodejs()
  } */

  linuxX64()

  mingwX64()

  sourceSets {
    commonMain {
      dependencies {
        implementation(kotlin("stdlib-common"))
      }
    }
    commonTest {
      dependencies {
        implementation("io.kotest:kotest-property:5.3.0")
        implementation("io.kotest:kotest-framework-engine:5.3.0")
        implementation("io.kotest:kotest-assertions-core:5.3.0")
      }
    }
  }
}

apply(plugin = "io.arrow-kt.analysis.kotlin")
