rootProject.name = "gradle-setup-plugin"
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    versionCatalogs {
        create("deps") {
            from("io.github.shabinder:soundbound-extensions-catalog:0.17")
        }
    }
}
