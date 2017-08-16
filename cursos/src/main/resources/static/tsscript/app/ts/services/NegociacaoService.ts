import { NegociacaoExt } from "../models/NegociacaoExt";
import { Negociacao } from "../models/Negociacao";
export class NegociacaoService {
    importaDados(handler: FetchHandler): Promise<Negociacao[]> {
        return fetch("http://localhost:8080/dados")
        .then(result => handler(result))
        .then(result => result.json())
        .then((dados: NegociacaoExt[]) => 
            dados.map(dado => new Negociacao(new Date(), dado.vezes, dado.montante))
        );
    }
}

export interface FetchHandler {
    (result: Response): Response;
}