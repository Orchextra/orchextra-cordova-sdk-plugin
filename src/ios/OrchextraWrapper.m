//
//  OrchextraWrapper.m
//  OrchextraTest
//
//  Created by Sergio López on 27/5/16.
//
//

#import "OrchextraWrapper.h"

#import <Orchextra/Orchextra.h>
#import "CDVInvokedUrlCommand+Orchextra.h"


@interface OrchextraWrapper()

@property (strong, nonatomic) Orchextra *orchextra;

@property (strong, nonatomic) NSString *apiKey;
@property (strong, nonatomic) NSString *apiSecret;

@end

@implementation OrchextraWrapper

#pragma mark - Init

- (void)pluginInitialize
{
    [super pluginInitialize];
    
    self.orchextra = [Orchextra sharedInstance];
    [Orchextra logLevel:ORCLogLevelAll];
}

#pragma mark - PUBLIC

- (void)init:(CDVInvokedUrlCommand*)command;
{
    self.apiKey    = [command apiKey];
    self.apiSecret = [command apiSecret];
}

- (void)start:(CDVInvokedUrlCommand*)command
{
    OrchextraWrapper* __weak weakSelf = self;
    [self.orchextra setApiKey:self.apiKey
                                apiSecret:self.apiSecret
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

- (void)stop:(CDVInvokedUrlCommand*)command
{
    [self.orchextra stopOrchextraServices];
}

- (void)setUser:(CDVInvokedUrlCommand*)command
{
    ORCUser *user = [command user];
    [self.orchextra bindUser:user];
}

- (void)handlePush:(CDVInvokedUrlCommand*)command
{
    id localNotificationInfo = [command localNotificationInfo];
    [ORCPushManager handlePush:localNotificationInfo];
}

- (void)openScanner:(CDVInvokedUrlCommand*)command
{
    [[Orchextra sharedInstance] startScanner];
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
