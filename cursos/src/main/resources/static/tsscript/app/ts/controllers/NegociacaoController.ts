import { DateHelper } from "../helpers/DateHelper";
import { Mensagem } from "../models/Mensagem";
import { Negociacao } from "../models/Negociacao";
import { NegociacoesList } from "../models/NegociacoesList";
import { NegociacoesView} from "../views/NegociacoesView";
import { MensagemView } from "../views/MensagemView";
import { NegociacaoService } from "../services/NegociacaoService";
import { FetchHandler } from "../services/NegociacaoService";
import { dom } from "../helpers/dom";
import { throttle } from "../helpers/throttle";
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
    private _negociacaoService = new NegociacaoService();
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

    @throttle()
    async importaDados() {
        function _handleError(result : Response) {
            if (!result.ok) throw new Error(result.statusText);
            return result;
        }
        try {
            const negociacoes = await this._negociacaoService.importaDados(_handleError);
            negociacoes.forEach(negociacao => this._negociacoesList.adiciona(negociacao));
            this._negociacoesView.update(this._negociacoesList);
        } catch(error) {
            this._mensagemView.update(new Mensagem(`Negociações NÃO importadas: ${error.message}`));
        }
    }

    private _reset() {
        this._inputData.val("");
        this._inputQuantidade.val("1");
        this._inputValor.val("0.0");
        this._inputData.focus();
    }

}