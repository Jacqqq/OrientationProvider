## Sample usage

import 'dart:async';

import 'package:flutter/material.dart';
import 'package:orientation_provider/orientation_provider.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  ExactDeviceOrientation _deviceOrientation;

  @override
  Widget build(BuildContext context) {
    return OrientationBuilder(builder: (context, orientation) {
      OrientationProvider.orientation.then((value) {
        if (_deviceOrientation != value) {
          setState(() {
            _deviceOrientation = value;
          });
        }
      });
      return MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            title: const Text('OrientationProviderPlugin example app'),
          ),
          body: Center(
            child: Text('Current device orientation: $_deviceOrientation\n'),
          ),
        ),
      );
    });
  }
}
