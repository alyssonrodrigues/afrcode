class NegociacoesService {
	constructor() {
		this._httpService = new HttpService();
	}
	
	getNegociacoesSemanaRetrasada() {
    	return this._getNegociacoes("negociacoes/retrasada");
	}

	getNegociacoesSemanaAnterior() {
    	return this._getNegociacoes("negociacoes/anterior");
	}

	getNegociacoesSemana() {
    	return this._getNegociacoes("negociacoes/semana");
	}
	
	getTodasNegociacoes() {
    	let promises = [this.getNegociacoesSemana(),
    		this.getNegociacoesSemanaAnterior(),
    		this.getNegociacoesSemanaRetrasada()];
    	
    	return new Promise((resolve, reject) => 
    		Promise.all(promises)
    		.then(negociacoesArrayDeArrays => 
    			resolve(negociacoesArrayDeArrays.reduce(
    				(negociacoes, umNegociacoesArray) => 
    					negociacoes.concat(umNegociacoesArray), []))
    		).catch(error => reject(error)));
	}
	
	_getNegociacoes(url) {
		return new Promise((resolve, reject) => 
			this._httpService.get(url)
			.then(negociacoesStringArray =>
				resolve(negociacoesStringArray.map(negociacaoStr => 
					new Negociacao(
						new Date(negociacaoStr.data), 
						negociacaoStr.quantidade, 
						negociacaoStr.valor)))
			).catch(error => reject(error)));
	}
}