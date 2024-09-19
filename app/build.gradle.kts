import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

plugins {
    id("com.android.application")
}

val properties = Properties()
val localProperties = rootProject.file("local.properties")
if (localProperties.exists()) {
    properties.load(FileInputStream(localProperties))
}


android {
    namespace = "com.example.pocky"
    compileSdk = 34

    defaultConfig {


        applicationId = "com.example.pocky"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String","kakaoSDK", properties["kakaoSDK"].toString())
    }

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        buildFeatures {
            viewBinding  = true
            dataBinding = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation( "androidx.room:room-runtime:2.5.0")
    annotationProcessor ("androidx.room:room-compiler:2.5.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //프로필 이미지
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //kakao Login
    implementation ("com.kakao.sdk:v2-user:2.10.0")
    //zxing
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
}
