System.register(["../helpers/DateHelper", "../models/Mensagem", "../models/Negociacao", "../models/NegociacoesList", "../views/NegociacoesView", "../views/MensagemView", "../helpers/dom", "../helpers/throttle"], function (exports_1, context_1) {
    "use strict";
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __moduleName = context_1 && context_1.id;
    var DateHelper_1, Mensagem_1, Negociacao_1, NegociacoesList_1, NegociacoesView_1, MensagemView_1, dom_1, throttle_1, NegociacaoController;
    return {
        setters: [
            function (DateHelper_1_1) {
                DateHelper_1 = DateHelper_1_1;
            },
            function (Mensagem_1_1) {
                Mensagem_1 = Mensagem_1_1;
            },
            function (Negociacao_1_1) {
                Negociacao_1 = Negociacao_1_1;
            },
            function (NegociacoesList_1_1) {
                NegociacoesList_1 = NegociacoesList_1_1;
            },
            function (NegociacoesView_1_1) {
                NegociacoesView_1 = NegociacoesView_1_1;
            },
            function (MensagemView_1_1) {
                MensagemView_1 = MensagemView_1_1;
            },
            function (dom_1_1) {
                dom_1 = dom_1_1;
            },
            function (throttle_1_1) {
                throttle_1 = throttle_1_1;
            }
        ],
        execute: function () {
            NegociacaoController = class NegociacaoController {
                constructor() {
                    this._negociacoesList = new NegociacoesList_1.NegociacoesList();
                    this._ordemAtual = "";
                    this._negociacoesView = new NegociacoesView_1.NegociacoesView($("#negociacoesView"));
                    this._negociacoesView.update(this._negociacoesList);
                    this._mensagemView = new MensagemView_1.MensagemView($("#mensagemView"));
                }
                adiciona(event) {
                    event.preventDefault();
                    let negociacao = new Negociacao_1.Negociacao(DateHelper_1.DateHelper.textoParaData(this._inputData.val()), parseInt(this._inputQuantidade.val()), parseFloat(this._inputValor.val()));
                    this._negociacoesList.adiciona(negociacao);
                    this._negociacoesView.update(this._negociacoesList);
                    this._mensagemView.update(new Mensagem_1.Mensagem("Negociação incluída com sucesso!"));
                    this._reset();
                }
                importaDados() {
                    function _handleError(result) {
                        if (!result.ok)
                            throw new Error(result.statusText);
                        return result;
                    }
                    fetch("http://localhost:8080/dados")
                        .then(result => _handleError(result))
                        .then(result => result.json())
                        .then((dados) => {
                        dados.map(dado => new Negociacao_1.Negociacao(new Date(), dado.vezes, dado.montante))
                            .forEach(negociacao => this._negociacoesList.adiciona(negociacao));
                        this._negociacoesView.update(this._negociacoesList);
                    });
                }
                _reset() {
                    this._inputData.val("");
                    this._inputQuantidade.val("1");
                    this._inputValor.val("0.0");
                    this._inputData.focus();
                }
            };
            __decorate([
                dom_1.dom("#data")
            ], NegociacaoController.prototype, "_inputData", void 0);
            __decorate([
                dom_1.dom("#quantidade")
            ], NegociacaoController.prototype, "_inputQuantidade", void 0);
            __decorate([
                dom_1.dom("#valor")
            ], NegociacaoController.prototype, "_inputValor", void 0);
            __decorate([
                throttle_1.throttle()
            ], NegociacaoController.prototype, "importaDados", null);
            exports_1("NegociacaoController", NegociacaoController);
        }
    };
});
