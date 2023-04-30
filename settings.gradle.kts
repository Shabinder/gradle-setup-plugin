rootProject.name = "gradle-setup-plugin"
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    versionCatalogs {
        create("deps") {
            version("kvisionVersion", "6.3.0")

            from("in.shabinder:soundbound-extensions-catalog:0.2")
            library("google-services", "com.google.gms:google-services:4.3.15")
            library("firebase-bom", "com.google.firebase:firebase-bom:31.5.0")
            library("firebase-plugins-crashlytics", "com.google.firebase:firebase-crashlytics-gradle:2.9.5")
            library("rudderstack-analytic-java", "com.rudderstack.sdk.java.analytics:analytics:3.0.0")
        }
    }
}
