class Constants {
	constructor() {
        this._dbName = "aluraframe";
        this._dbVersion = 3;
        this._negociacaoStoreName = "negociacoes";
        Object.freeze(this);
	}
	
	get dbName() {
		return this._dbName;
	}
	
	get dbVersion() {
		return this._dbVersion;
	}
	
	get negociacaoStoreName() {
		return this._negociacaoStoreName;
	}
}