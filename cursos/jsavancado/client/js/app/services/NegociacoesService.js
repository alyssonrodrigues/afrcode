"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var NegociacoesService = function () {
	function NegociacoesService() {
		_classCallCheck(this, NegociacoesService);

		this._httpService = new HttpService();
		this._constants = new Constants();
		this._connectionFactory = new ConnectionFactory(this._constants.dbName, this._constants.dbVersion, this._constants.negociacaoStoreName);
	}

	_createClass(NegociacoesService, [{
		key: "adiciona",
		value: function adiciona(negociacao, negociacoesList) {
			var _this = this;

			return new Promise(function (resolve, reject) {
				_this._connectionFactory.getConnection().then(function (connection) {
					return new NegociacaoDao(connection, _this._constants);
				}).then(function (negociacaoDao) {
					return negociacaoDao.adiciona(negociacao);
				}).then(function () {
					negociacoesList.adiciona(negociacao);
					resolve();
				}).catch(function (error) {
					return reject(error);
				});
			});
		}
	}, {
		key: "removeTodos",
		value: function removeTodos(negociacoesList) {
			var _this2 = this;

			return new Promise(function (resolve, reject) {
				_this2._connectionFactory.getConnection().then(function (connection) {
					return new NegociacaoDao(connection, _this2._constants);
				}).then(function (negociacaoDao) {
					return negociacaoDao.removeTodos();
				}).then(function () {
					negociacoesList.esvazia();
					resolve();
				}).catch(function (error) {
					return reject(error);
				});
			});
		}
	}, {
		key: "importaNegociacoes",
		value: function importaNegociacoes(negociacoesList) {
			var _this3 = this;

			return new Promise(function (resolve, reject) {
				_this3._connectionFactory.getConnection().then(function (connection) {
					return new NegociacaoDao(connection, _this3._constants);
				}).then(function (negociacaoDao) {
					return _this3.getTodasNegociacoes().then(function (negociacoes) {
						return negociacoes.filter(function (negociacao) {
							return !negociacoesList.contains(negociacao);
						});
					}).then(function (negociacoes) {
						return negociacaoDao.adicionaNegociacoes(negociacoes).then(function () {
							negociacoes.forEach(function (negociacao) {
								return negociacoesList.adiciona(negociacao);
							});
							resolve();
						});
					}).catch(function (error) {
						return reject(error);
					});
				}).catch(function (error) {
					return reject(error);
				});
			});
		}
	}, {
		key: "listaTodos",
		value: function listaTodos(negociacoesList) {
			var _this4 = this;

			return new Promise(function (resolve, reject) {
				_this4._connectionFactory.getConnection().then(function (connection) {
					return new NegociacaoDao(connection, _this4._constants);
				}).then(function (negociacaoDao) {
					return negociacaoDao.listaTodos();
				}).then(function (negociacoes) {
					negociacoes.forEach(function (negociacao) {
						return negociacoesList.adiciona(negociacao);
					});
					resolve();
				}).catch(function (error) {
					return reject(error);
				});
			});
		}
	}, {
		key: "getNegociacoesSemanaRetrasada",
		value: function getNegociacoesSemanaRetrasada() {
			return this._getNegociacoes("negociacoes/retrasada");
		}
	}, {
		key: "getNegociacoesSemanaAnterior",
		value: function getNegociacoesSemanaAnterior() {
			return this._getNegociacoes("negociacoes/anterior");
		}
	}, {
		key: "getNegociacoesSemana",
		value: function getNegociacoesSemana() {
			return this._getNegociacoes("negociacoes/semana");
		}
	}, {
		key: "getTodasNegociacoes",
		value: function getTodasNegociacoes() {
			var promises = [this.getNegociacoesSemana(), this.getNegociacoesSemanaAnterior(), this.getNegociacoesSemanaRetrasada()];

			return new Promise(function (resolve, reject) {
				return Promise.all(promises).then(function (negociacoesArrayDeArrays) {
					return resolve(negociacoesArrayDeArrays.reduce(function (negociacoes, umNegociacoesArray) {
						return negociacoes.concat(umNegociacoesArray);
					}, []));
				}).catch(function (error) {
					return reject(error);
				});
			});
		}
	}, {
		key: "_getNegociacoes",
		value: function _getNegociacoes(url) {
			var _this5 = this;

			return new Promise(function (resolve, reject) {
				return _this5._httpService.get(url).then(function (negociacoesStringArray) {
					return resolve(negociacoesStringArray.map(function (negociacaoStr) {
						return new Negociacao(new Date(negociacaoStr.data), negociacaoStr.quantidade, negociacaoStr.valor);
					}));
				}).catch(function (error) {
					return reject(error);
				});
			});
		}
	}]);

	return NegociacoesService;
}();
//# sourceMappingURL=NegociacoesService.js.map