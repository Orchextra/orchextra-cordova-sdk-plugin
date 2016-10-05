//
//  OrchextraWrapper.m
//  OrchextraTest
//
//  Created by Sergio López on 27/5/16.
//
//

#import "OrchextraWrapper.h"

#import "CDVInvokedUrlCommand+Orchextra.h"


@interface OrchextraWrapper()

@property (strong, nonatomic) Orchextra *orchextra;

@property (strong, nonatomic) NSString *apiKey;
@property (strong, nonatomic) NSString *apiSecret;
@property (strong, nonatomic) NSString *schemeCallback;

@end


@interface OrchextraWrapper() <OrchextraCustomActionDelegate>
@end


@implementation OrchextraWrapper


#pragma mark - PUBLIC

- (void)init:(CDVInvokedUrlCommand*)command;
{
    self.orchextra = [Orchextra sharedInstance];
    self.orchextra.delegate = self;
    
    self.apiKey    = [command apiKey];
    self.apiSecret = [command apiSecret];
    self.schemeCallback = command.callbackId;
    
    [Orchextra logLevel:[command logLevel]];
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
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)bindUser:(CDVInvokedUrlCommand*)command
{
    ORCUser *user = [command user];
    [self.orchextra bindUser:user];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)handlePush:(CDVInvokedUrlCommand*)command
{
    id localNotificationInfo = [command localNotificationInfo];
    [ORCPushManager handlePush:localNotificationInfo];
}

- (void)openScanner:(CDVInvokedUrlCommand*)command
{
    [[Orchextra sharedInstance] startScanner];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
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

#pragma mark - DELEGATES
#pragma mark - <OrchextraCustomActionDelegate>

- (void)executeCustomScheme:(NSString *)scheme
{
    if (self.schemeCallback)
    {
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:scheme];
        [pluginResult setKeepCallbackAsBool:YES];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:self.schemeCallback];
    }
}

@end
