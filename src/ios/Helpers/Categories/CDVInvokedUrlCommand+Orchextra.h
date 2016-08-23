//
//  CDVInvokedUrlCommand+Orchextra.h
//  OrchextraTest
//
//  Created by Sergio López on 31/5/16.
//
//

#import <Cordova/CDV.h>
#import <Orchextra/Orchextra.h>

@class ORCUser;

@interface CDVInvokedUrlCommand (Orchextra)

#pragma mark - PUBLIC

#pragma mark - Initialization

- (NSString *)apiKey;
- (NSString *)apiSecret;
- (ORCLogLevel)logLevel;

#pragma mark -

- (id)localNotificationInfo;
- (ORCUser *)user;

@end
