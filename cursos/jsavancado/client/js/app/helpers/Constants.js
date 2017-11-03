"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Constants = function () {
	function Constants() {
		_classCallCheck(this, Constants);

		this._dbName = "aluraframe";
		this._dbVersion = 3;
		this._negociacaoStoreName = "negociacoes";
		Object.freeze(this);
	}

	_createClass(Constants, [{
		key: "dbName",
		get: function get() {
			return this._dbName;
		}
	}, {
		key: "dbVersion",
		get: function get() {
			return this._dbVersion;
		}
	}, {
		key: "negociacaoStoreName",
		get: function get() {
			return this._negociacaoStoreName;
		}
	}]);

	return Constants;
}();
//# sourceMappingURL=Constants.js.map