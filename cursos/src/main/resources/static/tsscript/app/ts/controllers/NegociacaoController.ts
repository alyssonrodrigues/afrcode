class NegociacaoController {
    private _inputData: JQuery;
    private _inputQuantidade: JQuery;
    private _inputValor: JQuery;
    private _negociacoesList = new NegociacoesList();
    private _negociacoesView: NegociacoesView;
    private _mensagemView: MensagemView;
    private _ordemAtual = "";

    constructor() {
        this._inputData = $("#data");
        this._inputQuantidade = $("#quantidade");
        this._inputValor = $("#valor");
        this._negociacoesView = new NegociacoesView($("#negociacoesView"));
        this._negociacoesView.update(this._negociacoesList);
        this._mensagemView = new MensagemView($("#mensagemView"));
    }

    adiciona(event: Event) {
        event.preventDefault();
        let negociacao = new Negociacao(
            DateHelper.textoParaData(this._inputData.val()),
            parseInt(this._inputQuantidade.val()),
            parseFloat(this._inputValor.val())
        );
        this._negociacoesList.adiciona(negociacao);
        this._negociacoesView.update(this._negociacoesList);
        this._mensagemView.update(new Mensagem("Negociação incluída com sucesso!"));
        this._reset();
    }

    private _reset() {
        this._inputData.val("");
        this._inputQuantidade.val("1");
        this._inputValor.val("0.0");
        this._inputData.focus();
    }

}