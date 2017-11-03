"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var NegociacaoDao = function () {
	function NegociacaoDao(connection, constants) {
		_classCallCheck(this, NegociacaoDao);

		this._connection = connection;
		this._objStoreName = constants.negociacaoStoreName;
	}

	_createClass(NegociacaoDao, [{
		key: "adiciona",
		value: function adiciona(negociacao) {
			var _this = this;

			return new Promise(function (resolve, reject) {
				var tx = _this._connection.transaction([_this._objStoreName], "readwrite");
				var objStore = tx.objectStore(_this._objStoreName);
				var addRequest = objStore.add(negociacao);
				addRequest.onsuccess = function (event) {
					return resolve();
				};
				addRequest.onerror = function (event) {
					return reject(event.target.error);
				};
			});
		}
	}, {
		key: "adicionaNegociacoes",
		value: function adicionaNegociacoes(negociacoes) {
			var _this2 = this;

			var promises = [];
			negociacoes.forEach(function (negociacao) {
				return promises.push(_this2.adiciona(negociacao));
			});
			return Promise.all(promises);
		}
	}, {
		key: "removeTodos",
		value: function removeTodos() {
			var _this3 = this;

			return new Promise(function (resolve, reject) {
				var tx = _this3._connection.transaction([_this3._objStoreName], "readwrite");
				var objStore = tx.objectStore(_this3._objStoreName);
				var removeRequest = objStore.clear();
				removeRequest.onsuccess = function (event) {
					return resolve();
				};
				removeRequest.onerror = function (event) {
					return reject(event.target.error);
				};
			});
		}
	}, {
		key: "listaTodos",
		value: function listaTodos() {
			var _this4 = this;

			return new Promise(function (resolve, reject) {
				var tx = _this4._connection.transaction([_this4._objStoreName], "readwrite");
				var objStore = tx.objectStore(_this4._objStoreName);
				var negociacoes = [];
				var cursorRequest = objStore.openCursor();
				cursorRequest.onsuccess = function (event) {
					var pointer = event.target.result;
					if (pointer) {
						var negociacaoStr = pointer.value;
						negociacoes.push(new Negociacao(new Date(negociacaoStr._data), negociacaoStr._quantidade, negociacaoStr._valor));
						pointer.continue();
					} else {
						resolve(negociacoes);
					}
				};
				cursorRequest.onerror = function (event) {
					return resolve(event.target.error);
				};
			});
		}
	}]);

	return NegociacaoDao;
}();
//# sourceMappingURL=NegociacaoDao.js.map