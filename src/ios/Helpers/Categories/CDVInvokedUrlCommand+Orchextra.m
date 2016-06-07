//
//  CDVInvokedUrlCommand+Orchextra.m
//  OrchextraTest
//
//  Created by Sergio López on 31/5/16.
//
//

#import "CDVInvokedUrlCommand+Orchextra.h"
#import <Orchextra/ORCUser.h>

typedef NS_ENUM(NSUInteger,ORCInitParam)
{
    ORCInitParamApiKey = 0,
    ORCInitParamApiSecret = 1
};

typedef NS_ENUM(NSUInteger,ORCUserParam)
{
    ORCUserParamID = 0,
    ORCUserParamBirthday = 1,
    ORCUserParamGender = 2,
    ORCUserParamTags = 3
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
    ORCUser *user = [[ORCUser alloc] init];
    
    user.crmID = [self argumentAtIndex:ORCUserParamID withDefault:nil];
    user.birthday = [self birthday];
    user.gender = [self gender];
    user.tags = [self argumentAtIndex:ORCUserParamTags withDefault:nil];
    
    return user;
}

#pragma mark - Private

- (NSDate *)birthday
{
    NSString* dateString = [self argumentAtIndex:ORCUserParamBirthday withDefault:nil];
    if (!dateString) return nil;
    
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy/MM/dd"];
    NSDate *date = [dateFormatter dateFromString:dateString];
    
    return date;
}

- (ORCUserGender)gender
{
    NSString* genderString = [[self argumentAtIndex:ORCUserParamGender withDefault:nil] lowercaseString];
    
    if ([genderString isEqualToString:@"f"])
    {
        return ORCGenderFemale;
    }
    else if([genderString isEqualToString:@"m"])
    {
        return ORCGenderMale;
    }
    else
    {
        return ORCGenderNone;
    }
}

@end
