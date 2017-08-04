class NegociacaoController {
	constructor() {
		let $ = document.querySelector.bind(document);
		
		this._inputData = $("#data");
		this._inputQuantidade = $("#quantidade");
		this._inputValor = $("#valor");
		this._negociacoesList = new NegociacoesList();
		
		this._tbody = $("table tbody");
	}

	adiciona(event) {
		event.preventDefault();
		
		let negociacao = this._criaNegociacao();
		this._negociacoesList.adiciona(negociacao);
		this._exibeNegociacao(negociacao);
		
		this._reset();
	}
	
	_exibeNegociacao(negociacao) {
		let tr = document.createElement("tr");
		
		negociacao.toArray().forEach(prop => {
			let td = document.createElement("td");
			td.textContent = prop;
			tr.appendChild(td);
		});
		
		this._tbody.appendChild(tr);
	}
	
	_criaNegociacao() {
		return new Negociacao(
				DateHelper.textoParaData(this._inputData.value), 
				this._inputQuantidade.value, 
				this._inputValor.value
		);
	}
	
	_reset() {
		this._inputData.value = "";
		this._inputQuantidade.value = "1";
		this._inputValor.value = "0";
		this._inputData.focus();
	}
}