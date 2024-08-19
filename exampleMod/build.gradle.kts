plugins {
    id("java")
}

group = "org.jephacake.exampleMod"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation(project(":parentModule"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.yaml:snakeyaml:2.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.AMAZON)    //TODO: Change when MC goes up to java 22 back to JvmVendorSpec.ORACLE
        implementation.set(JvmImplementation.VENDOR_SPECIFIC)
    }
}

tasks.test {
    useJUnitPlatform()
}