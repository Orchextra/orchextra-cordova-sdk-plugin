//
//  NSString+Date.m
//  OrchextraTest
//
//  Created by Sergio López on 22/6/16.
//
//

#import "ORCUser+JSON.h"
#import "NSDictionary+JSON.h"
#import "NSString+Gender.h"

@implementation ORCUser (Orchextra)

+ (ORCUser *)userWithDictionary:(NSDictionary *)userDictionary
{
    ORCUser *user = [[ORCUser alloc] init];
    
    user.crmID = [userDictionary stringForKey:@"crmId"];
    
    user.birthday = [userDictionary dateForKey:@"birthday" format:@"yyyy/MM/dd"];
    
    NSString *genderString = [userDictionary stringForKey:@"gender"];
    user.gender = [genderString toGender];
    
    user.tags = [userDictionary arrayForKey:@"tags"];
    
    return user;
}

@end