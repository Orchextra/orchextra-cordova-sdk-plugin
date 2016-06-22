//
//  CDVInvokedUrlCommand+Orchextra.m
//  OrchextraTest
//
//  Created by Sergio López on 31/5/16.
//
//

#import "CDVInvokedUrlCommand+Orchextra.h"
#import "ORCUser+JSON.h"


typedef NS_ENUM(NSUInteger,ORCInitParam)
{
    ORCInitParamApiKey = 0,
    ORCInitParamApiSecret = 1
};


@implementation CDVInvokedUrlCommand (Orchextra)

#pragma mark - PUBLIC

#pragma mark - Initialization

- (NSString *)apiKey
{
    return [self argumentAtIndex:ORCInitParamApiKey withDefault:nil];
}

- (NSString *)apiSecret
{
    return [self argumentAtIndex:ORCInitParamApiSecret withDefault:nil];
}

#pragma mark - Local Notifications

- (id)localNotificationInfo
{
    return [self argumentAtIndex:0 withDefault:nil];
}

#pragma mark - User

- (ORCUser *)user
{
    NSDictionary *userDictionary = [self argumentAtIndex:0 withDefault:nil];
    
    ORCUser *user = [ORCUser userWithDictionary:userDictionary];
    
    return user;
}

@end
