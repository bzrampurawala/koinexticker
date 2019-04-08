import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val android_arch_navigation: String = "1.0.0" 

    const val annotation: String = "1.0.2" 

    const val appcompat: String = "1.1.0-alpha01" 

    const val constraintlayout: String = "1.1.3" 

    const val androidx_core_core: String = "1.1.0-alpha03" 

    const val androidx_fragment: String = "1.1.0-alpha03" 

    const val androidx_lifecycle: String = "2.1.0-alpha01" 

    const val androidx_palette: String = "1.0.0" 

    const val androidx_preference: String = "1.1.0-alpha02" 

    const val recyclerview: String = "1.0.0" 

    const val androidx_room: String = "2.1.0-alpha03" 

    const val swiperefreshlayout: String = "1.1.0-alpha01" 

    const val viewpager2: String = "1.0.0-alpha02" 

    const val lottie: String = "3.0.0" 

    const val com_android_tools_build_gradle: String = "3.3.2" 

    const val lint_gradle: String = "26.3.2" 

    const val com_facebook_stetho: String = "1.5.1" 

    const val material: String = "1.1.0-alpha02" 

    const val com_google_dagger: String = "2.21" 

    const val firebase_database: String = "16.1.0" 

    const val google_services: String = "4.2.0" 

    const val retrofit2_kotlin_coroutines_adapter: String = "0.9.2" 

    const val com_jakewharton_rxbinding2: String = "2.2.0" 

    const val rxrelay: String = "2.1.0" 

    const val timber: String = "4.7.1" 

    const val telescope: String = "2.1.0" 

    const val moshi_kotlin: String = "1.8.0" 

    const val com_squareup_okhttp3: String = "3.14.0" 

    const val okio: String = "2.2.2" 

    const val com_squareup_retrofit2: String = "2.5.0" 

    const val autodispose: String = "1.1.0" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val android_junit5: String = "1.4.0.0" 

    const val mockk: String = "1.9.3" 

    const val rxandroid: String = "2.1.1" 

    const val rxjava: String = "2.2.8"

    const val jsr250_api: String = "1.0"

    const val junit: String = "4.12" 

    const val assertj_core: String = "3.12.2" 

    const val org_jetbrains_kotlin: String = "1.3.21" 

    const val ktlint_gradle: String = "7.2.1" 

    const val org_junit_jupiter: String = "5.4.1" 

    const val mockito_core: String = "2.25.1" 

    const val robolectric: String = "4.2.1"

    const val mvrx: String = "1.0.0"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.1.1"

        const val currentVersion: String = "5.3.1"

        const val nightlyVersion: String = "5.5-20190401000032+0000"

        const val releaseCandidate: String = ""
    }
}
