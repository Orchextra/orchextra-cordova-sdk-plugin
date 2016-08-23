var exec = require('cordova/exec');

var orchextra = {
    init: function(options, success, failure) {
        exec(success, failure, "OrchextraWrapper", "init", [options]);
    },
    start: function(success, failure) {
        exec(success, failure, "OrchextraWrapper", "start", []);
    },
    stop: function(success, failure) {
        exec(success, failure, "OrchextraWrapper", "stop", []);
    },
    openScanner: function(success, failure) {
        exec(success, failure, "OrchextraWrapper", "openScanner", []);
    },
    bindUser: function(user, success, failure) {
        exec(success, failure, "OrchextraWrapper", "bindUser", [user]);
    }
};

window.orchextra = orchextra

module.exports = orchextra;
