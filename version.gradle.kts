plugins {
    `java-library`
    id("xyz.unifycraft.gradle.multiversion")
    id("xyz.unifycraft.gradle.tools")
    id("xyz.unifycraft.gradle.tools.loom")
    id("xyz.unifycraft.gradle.tools.shadow")
    id("xyz.unifycraft.gradle.tools.publishing")
}

loomHelper {
    disableRunConfigs(xyz.unifycraft.gradle.utils.GameSide.SERVER)
}

dependencies {
    implementation("com.mojang:brigadier:1.0.18")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${when (mcData.version) {
        11902 -> "0.61.0+1.19.2"
        11802 -> "0.58.0+1.18.2"
        11701 -> "0.46.1+1.17"
        11605 -> "0.41.3+1.16"
        else -> throw IllegalStateException("Invalid MC version: ${mcData.version}")
    }}")
}

tasks {
    remapJar {
        archiveBaseName.set("${modData.name}-${mcData.versionStr}")
    }
}