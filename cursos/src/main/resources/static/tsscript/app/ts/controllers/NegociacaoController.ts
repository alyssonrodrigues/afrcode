import { DateHelper } from "../helpers/DateHelper";
import { Mensagem } from "../models/Mensagem";
import { Negociacao } from "../models/Negociacao";
import { NegociacoesList } from "../models/NegociacoesList";
import { NegociacoesView} from "../views/NegociacoesView";
import { MensagemView } from "../views/MensagemView";
import { dom } from "../helpers/dom";
export class NegociacaoController {
    @dom("#data")
    private _inputData: JQuery;
    @dom("#quantidade")
    private _inputQuantidade: JQuery;
    @dom("#valor")
    private _inputValor: JQuery;
    private _negociacoesList = new NegociacoesList();
    private _negociacoesView: NegociacoesView;
    private _mensagemView: MensagemView;
    private _ordemAtual = "";

    constructor() {
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