import Flutter
import UIKit

public class SwiftOrientationProviderPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "orientation_provider", binaryMessenger: registrar.messenger())
    let instance = SwiftOrientationProviderPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
  if(call.method.elementsEqual("getOrientation")){
  var orientation
        switch UIDevice.current.orientation{
        case .portrait:
            orientation="PortraitUp"
        case .portraitUpsideDown:
            orientation="PortraitDown"
        case .landscapeLeft:
            orientation="LandscapeLeft"
        case .landscapeRight:
            orientation="LandscapeRight"
        default:
            orientation="Unknown"
        }
      result(text)
    }
  }
}
