//
//  NSString+Gender.m
//  OrchextraTest
//
//  Created by Sergio López on 22/6/16.
//
//

#import "NSString+Gender.h"
#import <Orchextra/ORCUser.h>

@implementation NSString (Gender)

- (ORCUserGender)toGender
{
    NSString *lowerCaseGender = [self lowercaseString];
    
    if ([lowerCaseGender isEqualToString:@"f"])
    {
        return ORCGenderFemale;
    }
    else if([lowerCaseGender isEqualToString:@"m"])
    {
        return ORCGenderMale;
    }
    else
    {
        return ORCGenderNone;
    }
}

@end
