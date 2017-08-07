class NegociacoesList {
	constructor(callback) {
		this._negociacoes = [];
		this._callback = callback;
	}
	
	adiciona(negociacao) {
		this._negociacoes.push(negociacao);
		this._callback(this);
	}
	
	esvazia() {
		this._negociacoes = [];
		this._callback(this);
	}
	
	get negociacoes() {
		return [].concat(this._negociacoes);
	}
}