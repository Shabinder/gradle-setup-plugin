rootProject.name = "gradle-setup-plugin"
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    versionCatalogs {
        create("deps") {
            from("in.shabinder:soundbound-extensions-catalog:0.2")
        }
    }
}
