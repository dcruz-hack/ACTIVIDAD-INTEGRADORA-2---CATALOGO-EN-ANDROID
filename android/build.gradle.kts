allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory =
    rootProject.layout.buildDirectory
        .dir("../../build")
        .get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

subprojects {
    project.evaluationDependsOn(":app")
}

/*
 * Keep Android plugin modules (for example image_cropper) aligned with
 * the app compile SDK. Some AndroidX dependencies require API 34 or newer.
 
subprojects {
    afterEvaluate {
        val androidExtension = extensions.findByName("android")
        if (androidExtension != null) {
            val compileSdkSetter = androidExtension.javaClass.methods.firstOrNull {
                (it.name == "setCompileSdk" || it.name == "setCompileSdkVersion") &&
                    it.parameterCount == 1
            }
            compileSdkSetter?.invoke(androidExtension, 35)
        }
    }
}*/

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
