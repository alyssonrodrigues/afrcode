class NegociacaoController {
	constructor() {
		let $ = document.querySelector.bind(document);
		
		this._inputData = $("#data");
		this._inputQuantidade = $("#quantidade");
		this._inputValor = $("#valor");
		this._tbody = $("table tbody");
	}

	adiciona(event) {
		event.preventDefault();
		
		let data = new Date(
				...this._inputData.value
				.split("-")
				.map((item, indice) => item - indice % 2)
		);

		let negociacao = new Negociacao(
				data, 
				this._inputQuantidade.value, 
				this._inputValor.value
		);

		let tr = document.createElement("tr");
		
		negociacao.toArray().forEach((prop) => {
			let td = document.createElement("td");
			td.textContent = prop;
			tr.appendChild(td);
		});
		
		this._tbody.appendChild(tr);
		
		this._inputData.value = "";
		this._inputQuantidade.value = "1";
		this._inputValor.value = "0";
		this._inputData.focus();
	}
}