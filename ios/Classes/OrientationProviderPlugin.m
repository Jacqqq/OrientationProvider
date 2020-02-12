#import "OrientationProviderPlugin.h"
#if __has_include(<orientation_provider/orientation_provider-Swift.h>)
#import <orientation_provider/orientation_provider-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "orientation_provider-Swift.h"
#endif

@implementation OrientationProviderPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftOrientationProviderPlugin registerWithRegistrar:registrar];
}
@end
