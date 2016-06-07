

#import "AppDelegate+notification.h"
#import <Orchextra/Orchextra.h>

@implementation AppDelegate (notification)

#pragma mark - NOTIFICATION ( Local Push Notification)
- (void)application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification
{
    [ORCPushManager handlePush:notification];
}

@end
