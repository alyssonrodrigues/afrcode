class NegociacoesImport {
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
	
	_getNegociacoes(url) {
		return new Promise((resolve, reject) => 
			this._httpService.get(url).then(negociacoesStringArray => {
				let negociacoesArray = negociacoesStringArray.map(obj => 
					new Negociacao(
						new Date(obj.data), 
						obj.quantidade, 
						obj.valor));
				resolve(negociacoesArray);
				}).catch(error => reject(error)));
	}
}