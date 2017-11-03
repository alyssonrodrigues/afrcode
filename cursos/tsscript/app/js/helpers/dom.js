System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    function dom(selector) {
        return function (target, prop) {
            let element;
            const getter = function () {
                if (!element) {
                    element = $(selector);
                }
                return element;
            };
            Object.defineProperty(target, prop, { get: getter });
        };
    }
    exports_1("dom", dom);
    return {
        setters: [],
        execute: function () {
        }
    };
});
