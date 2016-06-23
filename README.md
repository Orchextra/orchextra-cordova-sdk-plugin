# Orchextra Cordova Plugin for iOS and Android

A cordova plugin that gives you access to iOS and Android Orchextra platform  from your hybrid app.

**Android:** <https://github.com/Orchextra/orchextra-android-sdk>

**iOS:** <https://github.com/Orchextra/orchextra-ios-sdk>

## Getting started
Start by creating a project in [Orchextra dashboard][dashboard], if you haven't done it yet. Go to "Setting" > "SDK Configuration" to get the **api key** and **api secret**, you will need these values to start Orchextra SDK.

## Installation

    cordova plugin add https://github.com/Orchextra/orchextra-cordova-sdk-plugin
    
### Suported Platforms
- **iOS** 7 or later
- **Android** Jelly Bean (v. 18) or later. But can be integrated in Android Gingerbread (v. 10)

## Methods

- `init`: Orchextra initialization.
- `start`: Start listening to events.
- `stop`: Stop listening to events.
- `bindUser`: (Optional) Set a local representation of a user persisted to the Orchextra Database to help to create a good user segmentation.
- `openScanner `: Launch scanner.


### Init
Orchextra initialization.

| Params |
| --- |
| [ ApiKey `string`, ApiSecret `string` ] |

	orchextra.init('apiKey', 'apiSecret')
--

### Start
Start listening to events.

| Params |
| --- |
| successCallback |
| errorCallback |

	orchextra.start(successCallback, errorCallback)

--
### Stop
Stop listening to events.

| Params |
| --- |****
| - |

	orchextra.stop()
--
### BindUser

Set a local representation of a user persisted to the Orchextra Database to help to create a good user segmentation. This method is optional and could be set up at any time..

| Param |
| --- |
| { 'crmId' : `string`, 'birthday' : `string`*(optional)*, 'gender' : `string`*(optional)* , 'tags' : `array[strings]`*(optional)* } | 
>*Example* `{ 'crmId' : 'sergio24', 'birthday' : '1985/10/21', 'genre' : 'M', 'tags' : ['Visa', 'Master Card'] }`

	orchextra.bindUser(UserDictionary)

**- Birthday String Format:** aaaa/mm/dd  
 
>*Example* `Ej: '1985/10/21'`

**- Gender String:**

| Gender |  |
| --- | --- |
|  Male | "M" |
| Female | "F" |

**- Tags Strings:**

>*Example* `['Visa', 'Master Card']`

--
### OpenScanner
 Launch scanner.

	orchextra.openScanner()

--

### iOS Geolocation permission description

You can provide a description of "why the app wants to use location services" in the xcode project *info.plist* by modifying the value of the following keys and providing an string with the reason.

* NSLocationAlwaysUsageDescription
* NSLocationWhenInUseUsageDescription

[dashboard]: https://dashboard.orchextra.io
