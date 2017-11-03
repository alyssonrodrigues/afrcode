System.register([], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var NegociacoesList;
    return {
        setters: [],
        execute: function () {
            NegociacoesList = class NegociacoesList {
                constructor() {
                    this._negociacoes = [];
                }
                adiciona(negociacao) {
                    if (!this.contains(negociacao)) {
                        this._negociacoes.push(negociacao);
                    }
                }
                esvazia() {
                    this._negociacoes = [];
                }
                get negociacoes() {
                    return [].concat(this._negociacoes);
                }
                get volumeTotal() {
                    return this._negociacoes.reduce((total, negociacao) => total + negociacao.volume, 0.0);
                }
                ordena(criterio) {
                    this._negociacoes.sort(criterio);
                }
                inverteOrdem() {
                    this._negociacoes.reverse();
                }
                contains(negociacao) {
                    return this._negociacoes.some(n => n.equals(negociacao));
                }
            };
            exports_1("NegociacoesList", NegociacoesList);
        }
    };
});
