plugins {
    id(SpringBoot.ID) version SpringBoot.VERSION
    id(SpringBoot.DEPENDENCY_MANAGEMENT) version SpringBoot.DEPENDENCY_MANAGEMENT_VERSION

    kotlin(Kotlin.WITH_JVM) version Kotlin.KOTLIN_VERSION
    kotlin(Kotlin.KAPT) version Kotlin.KOTLIN_VERSION
    kotlin(Kotlin.WITH_SPRING) version Kotlin.KOTLIN_VERSION
    kotlin(Kotlin.WITH_JPA) version Kotlin.KOTLIN_VERSION
    kotlin(Kotlin.WITH_LOMBOK) version Kotlin.KOTLIN_VERSION
}

group = Versioning.GROUP
version = Versioning.VERSION

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(Versioning.JAVA)
    }
}

repositories {
    mavenCentral()
}

apply {
    from(SpringBootLibs.PATH)
    from(ApiDocsLibs.PATH)
    from(DatabaseLibs.PATH)
    from(QueryDslLibs.PATH)

    from(KotlinLibs.PATH)

    from(TestLibs.PATH)
    from(SpringSecurityLibs.PATH)
    from(CacheLibs.PATH)
    from(ReactorLibs.PATH)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val generated = file(QueryDslLibs.FILE_PATH)

tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(generated)
}

sourceSets {
    main {
        kotlin.srcDirs += generated
    }
}

sourceSets {
    main {
        java.srcDir(QueryDslLibs.FILE_PATH)
        kotlin.srcDirs += generated
    }
}

kapt {
//    correctErrorTypes = true
//    keepJavacAnnotationProcessors = true
//    useBuildCache = false
    generateStubs = true
}

tasks.named("clean") {
    doLast {
        generated.deleteRecursively()
    }
}