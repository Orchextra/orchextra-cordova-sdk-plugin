//
//  CDVInvokedUrlCommand+Orchextra.m
//  OrchextraTest
//
//  Created by Sergio López on 31/5/16.
//
//

#import "CDVInvokedUrlCommand+Orchextra.h"
#import "ORCUser+JSON.h"
#import "NSDictionary+JSON.m"


@implementation CDVInvokedUrlCommand (Orchextra)

#pragma mark - PUBLIC

#pragma mark - Initialization

- (NSString *)apiKey
{
    NSDictionary *startOptions = [self startOptionsDictionary];
    return [startOptions stringForKey:@"apiKey"];
}

- (NSString *)apiSecret
{
    NSDictionary *startOptions = [self startOptionsDictionary];
    return [startOptions stringForKey:@"apiSecret"];
}

- (ORCLogLevel)logLevel
{
    NSDictionary *startOptions = [self startOptionsDictionary];
    NSString *logLevel = startOptions[@"logLevel"];
    
    if ([logLevel isEqualToString:@"error"])
    {
        return ORCLogLevelError;
    }
    else if ([logLevel isEqualToString:@"warning"])
    {
        return ORCLogLevelWarning;
    }
    else if ([logLevel isEqualToString:@"debug"])
    {
        return ORCLogLevelDebug;
    }
    else if ([logLevel isEqualToString:@"all"])
    {
        return ORCLogLevelAll;
    }
    else
    {
        return ORCLogLevelOff;
    }
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

#pragma mark - Convenience Methods

- (NSDictionary *)startOptionsDictionary
{
    return [self argumentAtIndex:0 withDefault:nil];
}

@end
