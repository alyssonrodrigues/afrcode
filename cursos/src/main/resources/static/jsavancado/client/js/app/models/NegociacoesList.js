class NegociacoesList {
	constructor() {
		this._negociacoes = [];
		Object.freeze(this);
	}
	
	adiciona(negociacao) {
		this._negociacoes.push(negociacao);
	}
	
	get negociacoes() {
		return [].concat(this._negociacoes);
	}
}