-keep class com.colors.game.data.model.** { *; }
-keepattributes *Annotation*
-dontwarn org.slf4j.**

# Hilt
-keep class dagger.hilt.** { *; }

# Kotlin Serialization
-keepattributes RuntimeVisibleAnnotations
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class ** {
    @kotlinx.serialization.SerialName <fields>;
}
