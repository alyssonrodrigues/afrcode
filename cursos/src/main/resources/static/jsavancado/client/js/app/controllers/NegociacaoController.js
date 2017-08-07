class NegociacaoController {
	constructor() {
		let $ = document.querySelector.bind(document);
		this._inputData = $("#data");
		this._inputQuantidade = $("#quantidade");
		this._inputValor = $("#valor");
		this._negociacoesList = new NegociacoesList();
		this._negociacoesView = new NegociacoesView($("#negociacoesView"));
		this._negociacoesView.update(this._negociacoesList);
		this._mensagem = new Mensagem();
		this._mensagemView = new MensagemView($("#mensagemView"));
		this._mensagemView.update(this._mensagem);
	}

	adiciona(event) {
		event.preventDefault();
		let negociacao = this._criaNegociacao();
		this._negociacoesList.adiciona(negociacao);
		this._negociacoesView.update(this._negociacoesList);
		this._mensagem.texto = "Negociação incluída com sucesso!";
		this._mensagemView.update(this._mensagem);
		this._reset();
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