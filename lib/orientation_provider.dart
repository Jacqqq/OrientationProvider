import 'dart:async';

import 'package:flutter/services.dart';

class OrientationProvider {
  static const MethodChannel _channel =
      const MethodChannel('orientation_provider');

  static Future<ExactDeviceOrientation> get orientation async {
    final Map<String, dynamic> params = <String, dynamic>{};
    final String orientation = await _channel.invokeMethod<String>('getOrientation', params);
    return _fromString(orientation);
  }
}

enum ExactDeviceOrientation {
  portraitUp,
  portraitDown,
  landscapeLeft,
  landscapeRight,
  unknown
}

ExactDeviceOrientation _fromString(String orientationString) {
  switch (orientationString) {
    case "PortraitUp":
      return ExactDeviceOrientation.portraitUp;
    case "PortraitDown":
      return ExactDeviceOrientation.portraitDown;
    case "LandscapeRight":
      return ExactDeviceOrientation.landscapeRight;
    case "LandscapeLeft":
      return ExactDeviceOrientation.landscapeLeft;
    case "Unknown":
    default:
      return ExactDeviceOrientation.unknown;
  }
}
