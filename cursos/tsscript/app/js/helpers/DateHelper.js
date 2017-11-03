System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var DateHelper;
    return {
        setters: [],
        execute: function () {
            DateHelper = class DateHelper {
                static textoParaData(texto) {
                    if (!/^\d{2}\/\d{2}\/\d{4}$/.test(texto)) {
                        throw new Error("Not in mm/dd/yyyy format!");
                    }
                    return new Date(texto);
                }
                static dataParaTexto(data) {
                    return `${data.getMonth() + 1}/${data.getDate()}/${data.getFullYear()}`;
                }
            };
            exports_1("DateHelper", DateHelper);
        }
    };
});
