class NegociacaoController {
    constructor() {
        this._inputData = document.querySelector("#data");
        this._inputQuantidade = document.querySelector("#quantidade");
        this._inputValor = document.querySelector("#valor");
    }
    adiciona(event) {
        event.preventDefault();
        let negociacao = new Negociacao(DateHelper.textoParaData(this._inputData.value), parseInt(this._inputQuantidade.value), parseFloat(this._inputValor.value));
        console.log(negociacao);
    }
}
