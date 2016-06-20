var exec = require('cordova/exec');

var OrchextraPlugin = {
    init: function(apiKey, apiSecret, success, failure) {
        exec(success, failure, "OrchextraWrapper", "init", [apiKey, apiSecret]);
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
    setUser: function(user, params, header, failure) {
        exec(success, failure, "OrchextraWrapper", "setUser", [user]);
    }
    
};

window.OrchextraPlugin = OrchextraPlugin

module.exports = OrchextraPlugin;
