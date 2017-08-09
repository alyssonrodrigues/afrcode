class NegociacaoController {
    constructor() {
        let $ = document.querySelector.bind(document);
        
        this._inputData = $("#data");
        this._inputQuantidade = $("#quantidade");
        this._inputValor = $("#valor");
         
        this._negociacoesList = new Bind(
            new NegociacoesList(), 
            new NegociacoesView($("#negociacoesView")), 
            "adiciona", "esvazia");
       
        this._mensagem = new Bind(
            new Mensagem(), new MensagemView($("#mensagemView")),
            "texto"); 
        
        this._negociacoesImport = new NegociacoesImport();
    }
    
    adiciona(event) {
        event.preventDefault();
        this._negociacoesList.adiciona(new Negociacao(
                DateHelper.textoParaData(this._inputData.value),
                this._inputQuantidade.value,
                this._inputValor.value));
        this._mensagem.texto = "Negociação incluída com sucesso!"; 
        this._limpaFormulario();   
    }
    
    apaga() {
        this._negociacoesList.esvazia();
        this._mensagem.texto = "Negociações apagadas com sucesso!";
    }
    
    importaNegociacoes() {
    	let callback = (error, negociacoes) => {
			if (error) {
				this._mensagem.texto = 
					`Negociações NÃO importadas! ${error}`;
				return;
			}
			negociacoes.forEach(negociacao => 
				this._negociacoesList.adiciona(negociacao));
			this._mensagem.texto =  
				"Negociações importadas com sucesso!";
		}
    	this._negociacoesImport.getNegociacoesRetrasada(callback);
    	this._negociacoesImport.getNegociacoesAnterior(callback);
		this._negociacoesImport.getNegociacoesSemana(callback);
    }
    
    _limpaFormulario() {
        this._inputData.value = "";
        this._inputQuantidade.value = 1;
        this._inputValor.value = 0.0;
        this._inputData.focus();   
    }
}