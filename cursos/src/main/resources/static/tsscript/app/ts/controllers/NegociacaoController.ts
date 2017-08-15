class NegociacaoController {
    private _inputData: HTMLInputElement;
    private _inputQuantidade: HTMLInputElement;
    private _inputValor: HTMLInputElement;
    private _negociacoesList = new NegociacoesList();
    private _negociacoesView: NegociacoesView;
    private _mensagemView: MensagemView;
    private _ordemAtual = "";

    constructor() {
        this._inputData = <HTMLInputElement>document.querySelector("#data");
        this._inputQuantidade = <HTMLInputElement>document.querySelector("#quantidade");
        this._inputValor = <HTMLInputElement>document.querySelector("#valor");
        this._negociacoesView = new NegociacoesView(document.querySelector("#negociacoesView"));
        this._negociacoesView.update(this._negociacoesList);
        this._mensagemView = new MensagemView(document.querySelector("#mensagemView"));
    }

    adiciona(event: Event) {
        event.preventDefault();
        let negociacao = new Negociacao(
            DateHelper.textoParaData(this._inputData.value),
            parseInt(this._inputQuantidade.value),
            parseFloat(this._inputValor.value)
        );
        this._negociacoesList.adiciona(negociacao);
        this._negociacoesView.update(this._negociacoesList);
        this._mensagemView.update(new Mensagem("Negociação incluída com sucesso!"));
        this._reset();
    }

    private _reset() {
        this._inputData.value = "";
        this._inputQuantidade.value = "1";
        this._inputValor.value = "0.0";
        this._inputData.focus();
    }

}