plugins {
  kotlin("multiplatform") version "1.9.25" apply true
  id("io.kotest.multiplatform") version "5.9.1" apply true
  id("io.arrow-kt.analysis.kotlin") version "2.0.2"
}

group "org.example"
version "1.0"

repositories {
  mavenCentral()
}

buildscript {
  repositories {
    mavenCentral()
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
        implementation("io.kotest:kotest-property:5.9.1")
        implementation("io.kotest:kotest-framework-engine:5.9.1")
        implementation("io.kotest:kotest-assertions-core:5.9.1")
      }
    }
  }
}
