System.register(["../models/Negociacao"], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Negociacao_1, NegociacaoService;
    return {
        setters: [
            function (Negociacao_1_1) {
                Negociacao_1 = Negociacao_1_1;
            }
        ],
        execute: function () {
            NegociacaoService = class NegociacaoService {
                importaDados(handler) {
                    return fetch("http://localhost:8080/dados")
                        .then(result => handler(result))
                        .then(result => result.json())
                        .then((dados) => dados.map(dado => new Negociacao_1.Negociacao(new Date(), dado.vezes, dado.montante)));
                }
            };
            exports_1("NegociacaoService", NegociacaoService);
        }
    };
});
