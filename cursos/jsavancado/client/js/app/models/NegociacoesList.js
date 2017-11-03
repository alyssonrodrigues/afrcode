"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var NegociacoesList = function () {
	function NegociacoesList() {
		_classCallCheck(this, NegociacoesList);

		this._negociacoes = [];
	}

	_createClass(NegociacoesList, [{
		key: "adiciona",
		value: function adiciona(negociacao) {
			this._negociacoes.push(negociacao);
		}
	}, {
		key: "esvazia",
		value: function esvazia() {
			this._negociacoes = [];
		}
	}, {
		key: "ordena",
		value: function ordena(criterio) {
			this._negociacoes.sort(criterio);
		}
	}, {
		key: "inverteOrdem",
		value: function inverteOrdem() {
			this._negociacoes.reverse();
		}
	}, {
		key: "contains",
		value: function contains(negociacao) {
			return this._negociacoes.some(function (n) {
				return n.equals(negociacao);
			});
		}
	}, {
		key: "negociacoes",
		get: function get() {
			return [].concat(this._negociacoes);
		}
	}, {
		key: "volumeTotal",
		get: function get() {
			return this._negociacoes.reduce(function (total, negociacao) {
				return total + negociacao.volume;
			}, 0.0);
		}
	}]);

	return NegociacoesList;
}();
//# sourceMappingURL=NegociacoesList.js.map