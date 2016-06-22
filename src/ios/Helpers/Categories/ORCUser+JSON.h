//
//  NSString+Date.h
//  OrchextraTest
//
//  Created by Sergio López on 22/6/16.
//
//

#import <Foundation/Foundation.h>
#import <Orchextra/ORCUser.h>

@interface ORCUser (JSON)

+ (ORCUser *)userWithDictionary:(NSDictionary *)NSDictionary;

@end

