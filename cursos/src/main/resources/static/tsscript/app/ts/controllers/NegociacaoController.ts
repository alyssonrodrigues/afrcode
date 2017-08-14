class NegociacaoController {
    private _inputData: HTMLInputElement;
    private _inputQuantidade: HTMLInputElement;
    private _inputValor: HTMLInputElement;

    constructor() {
        this._inputData = <HTMLInputElement>document.querySelector("#data");
        this._inputQuantidade = <HTMLInputElement>document.querySelector("#quantidade");
        this._inputValor = <HTMLInputElement>document.querySelector("#valor");
    }

    adiciona(event: Event) {
        event.preventDefault();
        let negociacao = new Negociacao(
            DateHelper.textoParaData(this._inputData.value),
            parseInt(this._inputQuantidade.value),
            parseFloat(this._inputValor.value)
        );
        console.log(negociacao);
    }
}