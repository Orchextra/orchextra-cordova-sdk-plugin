//
//  OrchextraWrapper.m
//  OrchextraTest
//
//  Created by Sergio López on 27/5/16.
//
//

#import "OrchextraWrapper.h"
#import <Orchextra/Orchextra.h>

@implementation OrchextraWrapper

- (void)hello:(CDVInvokedUrlCommand*)command
{
    [[Orchextra sharedInstance] setApiKey:@"6282cb80baa634a9ef83bab500bd5af5076e1ffa" apiSecret:@"7bf9dfc8be45a873b25d02c9c9cbf0a6905871e3" completion:^(BOOL success, NSError *error) {}];
    [Orchextra logLevel:ORCLogLevelAll];
}

@end
