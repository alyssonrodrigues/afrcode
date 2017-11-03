System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    function log() {
        return function (target, prop, descriptor) {
            const cur = descriptor.value;
            descriptor.value = function (...args) {
                const a = performance.now();
                const r = cur.apply(this, args);
                const b = performance.now();
                console.log(`${prop} (rt): ${b - a} (ms)...`);
                return r;
            };
            return descriptor;
        };
    }
    exports_1("log", log);
    return {
        setters: [],
        execute: function () {
        }
    };
});
