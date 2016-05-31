//
//  OrchextraWrapper.m
//  OrchextraTest
//
//  Created by Sergio López on 27/5/16.
//
//

#import "ORCOrchextraWrapper.h"

#import <Orchextra/Orchextra.h>
#import "CDVInvokedUrlCommand+Orchextra.h"


@interface ORCOrchextraWrapper()

@property (strong, nonatomic) Orchextra *orchextra;

@end

@implementation ORCOrchextraWrapper

#pragma mark - Init

- (void)pluginInitialize
{
    [super pluginInitialize];
    
    self.orchextra = [Orchextra sharedInstance];
    [Orchextra logLevel:ORCLogLevelAll];
}

#pragma mark - PUBLIC

- (void)startOrchextra:(CDVInvokedUrlCommand*)command
{
    NSString *apiKey    = [command apiKey];
    NSString *apiSecret = [command apiSecret];

    ORCOrchextraWrapper* __weak weakSelf = self;
    [self.orchextra setApiKey:apiKey
                                apiSecret:apiSecret
                               completion:^(BOOL success, NSError *error)
                                {
                                    if (success)
                                    {
                                        [weakSelf didSucceedOrchextraInitializationCallBackID:command.callbackId];
                                    }
                                    else
                                    {
                                        [weakSelf didFailOrchextraInitializationWithError:error callBackID:command.callbackId];
                                    }
                                }];
}

- (void)stopOrchextraServices:(CDVInvokedUrlCommand*)command
{
    [self.orchextra stopOrchextraServices];
}

- (void)handlePush:(CDVInvokedUrlCommand*)command
{
    NSDictionary *localNotificationInfo = [command localNotificationInfo];
    [ORCPushManager handlePush:localNotificationInfo];
}

- (void)logLevel:(CDVInvokedUrlCommand*)command
{
    [Orchextra logLevel:ORCLogLevelAll];
}

- (void)startScanner:(CDVInvokedUrlCommand*)command
{
    [[Orchextra sharedInstance] startScanner];
}

- (void)setUser:(CDVInvokedUrlCommand*)command
{
    ORCUser *user = [[ORCUser alloc] init];
    [self.orchextra setUser:user];
}

#pragma mark - Callback Methods

- (void)didSucceedOrchextraInitializationCallBackID:(NSString *)callbackId
{
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

- (void)didFailOrchextraInitializationWithError:(NSError *)error callBackID:(NSString *)callbackId
{
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.localizedDescription];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:callbackId];
}

@end
