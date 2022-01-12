plugins {
  kotlin("multiplatform") version "1.6.10" apply true
  id("io.kotest.multiplatform") version "5.0.3" apply true
}

group "org.example"
version "1.0"

repositories {
  maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
  mavenCentral()
}

buildscript {
  repositories {
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
  }
  dependencies {
    classpath("io.arrow-kt.analysis.kotlin:io.arrow-kt.analysis.kotlin.gradle.plugin:2.0-SNAPSHOT")
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
        implementation("io.kotest:kotest-property:5.0.3")
        implementation("io.kotest:kotest-framework-engine:5.0.3")
        implementation("io.kotest:kotest-assertions-core:5.0.3")
      }
    }
  }
}

apply(plugin = "io.arrow-kt.analysis.kotlin")
