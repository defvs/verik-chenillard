plugins {
    kotlin("jvm") version "1.9.0"
    id("io.verik.verik-plugin") version "0.1.16"
}

group = "defvs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

val topLevel = "Chenillard"
val xilPart = "xc7a100tcsg324-3"

verik {
    import {
        val verilogExtensions = listOf("v", "sv")
        // Import all *.v files
        importedFiles = projectDir.toPath().resolve("src/main/verilog/").toFile()
            .listFiles { it -> it.isFile && verilogExtensions.contains(it.extension) }?.map { it.toPath() } ?: listOf()
    }
    // Metrics dsim target
    dsim {
        compileTops = listOf(topLevel)
        sim {
            name = "sim"
        }
    }
    // Icarus Verilog target
    iverilog {
        top = topLevel
    }
    // Xilinx vivado target
    vivado {
        part = xilPart
        simTop = topLevel
    }
    // Cadence xrun target
    xrun {
        top = topLevel
    }
}