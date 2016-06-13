var exec = require('cordova/exec');

var HttpNative = {
    init: function(url, params, success, failure) {
        exec(success, failure, "OrchextraWrapper", "init", []);
    },
    start: function(url, params, success, failure) {
        exec(success, failure, "OrchextraWrapper", "start", []);
    },
    stop: function(url, params, success, failure) {
        exec(success, failure, "OrchextraWrapper", "stop", []);
    },
    openScanner: function(url, params, success, failure) {
        exec(success, failure, "OrchextraWrapper", "openScanner", []);
    },
    setUser: function(url, params, header, failure) {
        exec(success, failure, "OrchextraWrapper", "setUser", []);
    }
    
};

window.HttpNative = HttpNative

module.exports = HttpNative;
