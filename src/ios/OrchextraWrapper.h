//
//  OrchextraWrapper.h
//  OrchextraTest
//
//  Created by Sergio López on 27/5/16.
//
//

#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

@interface OrchextraWrapper : CDVPlugin

- (void)init:(CDVInvokedUrlCommand*)command;
- (void)start:(CDVInvokedUrlCommand*)command;
- (void)openScanner:(CDVInvokedUrlCommand*)command;

- (void)handlePush:(CDVInvokedUrlCommand*)command;

@end
