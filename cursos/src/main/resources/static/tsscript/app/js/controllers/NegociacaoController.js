System.register(["../helpers/DateHelper", "../models/Mensagem", "../models/Negociacao", "../models/NegociacoesList", "../views/NegociacoesView", "../views/MensagemView"], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var DateHelper_1, Mensagem_1, Negociacao_1, NegociacoesList_1, NegociacoesView_1, MensagemView_1, NegociacaoController;
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
            }
        ],
        execute: function () {
            NegociacaoController = class NegociacaoController {
                constructor() {
                    this._negociacoesList = new NegociacoesList_1.NegociacoesList();
                    this._ordemAtual = "";
                    this._inputData = $("#data");
                    this._inputQuantidade = $("#quantidade");
                    this._inputValor = $("#valor");
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
                _reset() {
                    this._inputData.val("");
                    this._inputQuantidade.val("1");
                    this._inputValor.val("0.0");
                    this._inputData.focus();
                }
            };
            exports_1("NegociacaoController", NegociacaoController);
        }
    };
});
