var exec = require('cordova/exec');

var HttpNative = {
    init: function(url, params, header, success, failure) {
        exec(success, failure, "OrchextraWrapper", "init", []);
    },
    start: function(url, params, header, success, failure) {
        exec(success, failure, "OrchextraWrapper", "start", []);
    },
    openScanner: function(url, params, header, success, failure) {
        exec(success, failure, "OrchextraWrapper", "openScanner", []);
    },
};

window.HttpNative = HttpNative

module.exports = HttpNative;
