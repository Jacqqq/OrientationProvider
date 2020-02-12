package com.jacekkrasowski.orientation_provider

import android.content.Context
import android.content.res.Configuration
import android.view.Surface
import android.view.WindowManager
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** OrientationProviderPlugin */
class OrientationProviderPlugin : FlutterPlugin, MethodCallHandler {

    private lateinit var context: Context

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        val channel = MethodChannel(flutterPluginBinding.binaryMessenger, "orientation_provider")
        val orientationProviderPlugin = OrientationProviderPlugin()
        orientationProviderPlugin.context = flutterPluginBinding.applicationContext
        channel.setMethodCallHandler(orientationProviderPlugin)
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "orientation_provider")
            val orientationProviderPlugin = OrientationProviderPlugin()
            orientationProviderPlugin.context = registrar.context()
            channel.setMethodCallHandler(orientationProviderPlugin)
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getOrientation") {
            result.success(getOrientation(context).name)
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }
}

enum class Orientation {
    PortraitUp, PortraitDown, LandscapeLeft, LandscapeRight, Unknown
}

fun getOrientation(context: Context): Orientation {
    val rotation: Int = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation
    return when (context.resources.configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_90) {
            Orientation.PortraitUp
        } else {
            Orientation.PortraitDown
        }
        Configuration.ORIENTATION_LANDSCAPE -> if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_90) {
            Orientation.LandscapeLeft
        } else {
            Orientation.LandscapeRight
        }
        else -> Orientation.Unknown
    }
}