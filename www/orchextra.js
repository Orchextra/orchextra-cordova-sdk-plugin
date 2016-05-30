var exec = require('cordova/exec');

var HttpNative = {
    hello: function(url, params, header, success, failure) {
        exec(success, failure, "OrchextraWrapper", "hello", []);
    }
};

window.HttpNative = HttpNative

module.exports = HttpNative;
