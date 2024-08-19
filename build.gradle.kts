import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

plugins {
    id("java")
}

group = "org.jephacake.msml"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.AMAZON)    //TODO: Change when MC goes up to java 22 back to JvmVendorSpec.ORACLE
        implementation.set(JvmImplementation.VENDOR_SPECIFIC)
    }
}

layout.buildDirectory.get().asFile.mkdirs()
val paperJar = layout.buildDirectory.file("paper-1.21.1-latest.jar").get().asFile

tasks.register("downloadPaper") {
    doFirst {
        if (!paperJar.exists()) {
            val apiUrl = URL("https://api.papermc.io/v2/projects/paper/versions/1.21.1/builds")
            println("Downloading latest build for version with URL $apiUrl")
            val connection = apiUrl.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            val response = BufferedReader(InputStreamReader(connection.inputStream)).use { it.readText() }
            val latestBuild = Regex("\"build\":(\\d+)").findAll(response).map { it.groupValues[1].toInt() }.maxOrNull()
            println("Latest build for version 1.21.1 is $latestBuild")
            if (latestBuild != null) {
                val jarName = "paper-1.21.1-$latestBuild.jar"
                val paperMcUrl = URL("https://api.papermc.io/v2/projects/paper/versions/1.21.1/builds/$latestBuild/downloads/$jarName")
                paperMcUrl.openStream().use { input ->
                    paperJar.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                println("Downloaded PaperMC server to ${paperJar.absolutePath}")
            } else {
                throw RuntimeException("No stable build for version 1.21.1 found :(")
            }
        } else {
            println("PaperMC server already exists at ${paperJar.absolutePath}")
        }
    }
}

tasks.register("compilePlugin", JavaCompile::class) {
    source = fileTree("src/main/java")
    classpath = sourceSets["main"].compileClasspath
    destinationDirectory.set(file("$buildDir/classes/java/main"))
    doLast {
        copy {
            from("src/main/java/plugin.yml")
            into("$buildDir/classes/java/main")
        }
    }
}

tasks.register<Jar>("createPluginJar") {
    dependsOn("compilePlugin")
    from("$buildDir/classes/java/main")
    archiveFileName.set("plugin.jar")
    destinationDirectory.set(file("$buildDir/libs"))
}

tasks.register("movePlugin", Copy::class) {
    dependsOn("createPluginJar")
    from("$buildDir/libs/plugin.jar")
    into("$buildDir/paper-server/plugins")
}

tasks.register("runPaperServer", Exec::class) {
    dependsOn("downloadPaper", "movePlugin")
    workingDir = layout.buildDirectory.dir("paper-server").get().asFile
    commandLine("java", "-jar", paperJar.absolutePath, "--nogui")
}