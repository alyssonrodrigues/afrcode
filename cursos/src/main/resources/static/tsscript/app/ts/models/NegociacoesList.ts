class NegociacoesList {
	private _negociacoes: Negociacao[] = [];
	constructor() {
	}
	
	adiciona(negociacao: Negociacao) {
		if (!this.contains(negociacao)) {
			this._negociacoes.push(negociacao);
		}
	}
	
	esvazia() {
		this._negociacoes = [];
	}
	
	get negociacoes(): Negociacao[] {
		return [].concat(this._negociacoes);
	}
	
	get volumeTotal() {
		return this._negociacoes.reduce(
        		(total, negociacao) => total + negociacao.volume, 
        		0.0);
	}
	
    ordena(criterio?: (a: Negociacao, b: Negociacao) => number) {
        this._negociacoes.sort(criterio);
    }
    
    inverteOrdem() {
        this._negociacoes.reverse();
    }
    
    contains(negociacao: Negociacao) {
    	return this._negociacoes.some(n => n.equals(negociacao));
    }
}