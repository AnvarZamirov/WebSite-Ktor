plugins {
    application
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core:2.1.3") // Используйте актуальную версию
    implementation("io.ktor:ktor-server-netty:2.1.3") // Используйте актуальную версию
//    implementation("io.ktor:ktor-html-builder:2.1.3") // Проверьте доступные версии
    implementation("io.ktor:ktor-server-html-builder:2.1.3") // Проверьте доступные версии
    implementation("io.ktor:ktor-server-sessions:2.1.3") // Проверьте доступные версии
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.3") // Проверьте доступные версии
    implementation("io.ktor:ktor-server-content-negotiation:2.1.3") // Проверьте доступные версии
    implementation("ch.qos.logback:logback-classic:1.2.6")

}

tasks.test {
    useJUnitPlatform()
}