//
//  OrchextraWrapper.h
//  OrchextraTest
//
//  Created by Sergio López on 27/5/16.
//
//

#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>

@interface ORCOrchextraWrapper : CDVPlugin

- (void)startOrchextra:(CDVInvokedUrlCommand*)command;
- (void)startScanner:(CDVInvokedUrlCommand*)command;

- (void)handlePush:(CDVInvokedUrlCommand*)command;

@end
