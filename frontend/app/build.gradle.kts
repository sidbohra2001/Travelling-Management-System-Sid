plugins {
    id("com.android.application")
}

android {
    namespace = "com.travel.app"
    compileSdk = 33

    packaging{
        resources.excludes.add("META-INF/license.txt")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/notice.txt")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/NOTICE")
    }

    defaultConfig {
        applicationId = "com.travel.app"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Self Added Dependencies
    compileOnly("org.projectlombok:lombok:1.18.30")                                 //lombok dependency
    annotationProcessor("org.projectlombok:lombok:1.18.30")                         //lombok dependency
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")                  //Zxing dependency for QR Scanner
    implementation("com.google.zxing:core:3.5.2")                                   //Zxing dependency for QR Scanner
    implementation("androidx.biometric:biometric:1.1.0")                            //Biometric Dependency
    implementation("org.springframework.android:spring-android-rest-template:1.0.1.RELEASE")    //Rest for android
}