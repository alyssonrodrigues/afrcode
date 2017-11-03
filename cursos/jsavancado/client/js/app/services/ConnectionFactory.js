"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var ConnectionFactory = function () {
	function ConnectionFactory(dbName, dbVersion) {
		_classCallCheck(this, ConnectionFactory);

		this._dbName = dbName;
		this._dbVersion = dbVersion;

		for (var _len = arguments.length, objStoreNames = Array(_len > 2 ? _len - 2 : 0), _key = 2; _key < _len; _key++) {
			objStoreNames[_key - 2] = arguments[_key];
		}

		this._objStoreNames = objStoreNames;
		this._connection = null;
		this._connectionClose = null;
	}

	_createClass(ConnectionFactory, [{
		key: "getConnection",
		value: function getConnection() {
			var _this = this;

			return new Promise(function (resolve, reject) {
				if (_this._connection) {
					resolve(_this._connection);
				}
				var dbRequest = window.indexedDB.open(_this._dbName, _this._dbVersion);
				dbRequest.onupgradeneeded = function (event) {
					var connection = event.target.result;
					_this._criaObjStores(connection);
				};
				dbRequest.onsuccess = function (event) {
					_this._connection = event.target.result;
					_this._connectionClose = _this._connection.close.bind(_this._connection);
					_this._connection.close = function () {
						throw new Error("use connectionFactory.closeConnection()!");
					};
					resolve(_this._connection);
				};
				dbRequest.onerror = function (event) {
					reject(event.target.error);
				};
			});
		}
	}, {
		key: "closeConnection",
		value: function closeConnection() {
			this._connectionClose();
			this._connection = null;
			this._connectionClose = null;
		}
	}, {
		key: "_criaObjStores",
		value: function _criaObjStores(connection) {
			this._objStoreNames.forEach(function (objStoreName) {
				if (connection.objectStoreNames.contains(objStoreName)) {
					connection.deleteObjectStore(objStoreName);
				}
				connection.createObjectStore(objStoreName, { autoIncrement: true });
			});
		}
	}]);

	return ConnectionFactory;
}();
//# sourceMappingURL=ConnectionFactory.js.map