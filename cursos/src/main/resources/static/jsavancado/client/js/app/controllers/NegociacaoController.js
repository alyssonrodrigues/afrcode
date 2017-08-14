"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var NegociacaoController = function () {
    function NegociacaoController() {
        _classCallCheck(this, NegociacaoController);

        var $ = document.querySelector.bind(document);

        this._inputData = $("#data");
        this._inputQuantidade = $("#quantidade");
        this._inputValor = $("#valor");

        this._negociacoesList = new Bind(new NegociacoesList(), new NegociacoesView($("#negociacoesView")), "adiciona", "esvazia", "ordena", "inverteOrdem");

        this._mensagem = new Bind(new Mensagem(), new MensagemView($("#mensagemView")), "texto");

        this._negociacoesService = new NegociacoesService();
        this._ordemAtual = "";
        this._init();
    }

    _createClass(NegociacaoController, [{
        key: "_init",
        value: function _init() {
            var _this = this;

            this._negociacoesService.listaTodos(this._negociacoesList).catch(function (error) {
                return _this._mensagem.texto = error;
            });
            setInterval(function () {
                return _this.importaNegociacoes(false);
            }, 30000);
        }
    }, {
        key: "adiciona",
        value: function adiciona(event) {
            var _this2 = this;

            event.preventDefault();
            var negociacao = new Negociacao(DateHelper.textoParaData(this._inputData.value), this._inputQuantidade.value, this._inputValor.value);
            this._negociacoesService.adiciona(negociacao, this._negociacoesList).then(function () {
                _this2._mensagem.texto = "Negociação incluída com sucesso!";
                _this2._limpaFormulario();
            }).catch(function (error) {
                return _this2._mensagem.texto = error;
            });
        }
    }, {
        key: "apaga",
        value: function apaga() {
            var _this3 = this;

            this._negociacoesService.removeTodos(this._negociacoesList).then(function () {
                return _this3._mensagem.texto = "Negociações apagadas com sucesso!";
            }).catch(function (error) {
                return _this3._mensagem.texto = error;
            });
        }
    }, {
        key: "importaNegociacoes",
        value: function importaNegociacoes(exibeMensagem) {
            var _this4 = this;

            this._negociacoesService.importaNegociacoes(this._negociacoesList).then(function () {
                return _this4._mensagem.texto = exibeMensagem ? "Negociações importadas com sucesso!" : "";
            }).catch(function (error) {
                return _this4._mensagem.texto = error;
            });
        }
    }, {
        key: "ordena",
        value: function ordena(coluna) {
            if (this._ordemAtual == coluna) {
                this._negociacoesList.inverteOrdem();
            } else {
                this._negociacoesList.ordena(function (a, b) {
                    return a[coluna] - b[coluna];
                });
            }
            this._ordemAtual = coluna;
        }
    }, {
        key: "_limpaFormulario",
        value: function _limpaFormulario() {
            this._inputData.value = "";
            this._inputQuantidade.value = 1;
            this._inputValor.value = 0.0;
            this._inputData.focus();
        }
    }]);

    return NegociacaoController;
}();
//# sourceMappingURL=NegociacaoController.js.map