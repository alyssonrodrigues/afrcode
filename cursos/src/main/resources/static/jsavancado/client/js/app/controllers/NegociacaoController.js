class NegociacaoController {
    constructor() {
        let $ = document.querySelector.bind(document);
        
        this._inputData = $("#data");
        this._inputQuantidade = $("#quantidade");
        this._inputValor = $("#valor");
         
        this._negociacoesList = new Bind(
            new NegociacoesList(), 
            new NegociacoesView($("#negociacoesView")), 
            "adiciona", "esvazia", "ordena", "inverteOrdem");
       
        this._mensagem = new Bind(
            new Mensagem(), new MensagemView($("#mensagemView")),
            "texto"); 
        
        this._negociacoesService = new NegociacoesService();
        this._ordemAtual = "";
        this._init();
    }
    
    _init() {
        this._negociacoesService.listaTodos(this._negociacoesList)
    		.catch(error => this._mensagem.texto = error);
        setInterval(() => this.importaNegociacoes(false), 30000);
    }
    
    adiciona(event) {
        event.preventDefault();
        let negociacao = new Negociacao(
				DateHelper.textoParaData(this._inputData.value),
				this._inputQuantidade.value,
	            this._inputValor.value);
    	this._negociacoesService.adiciona(negociacao, this._negociacoesList)
            .then(() => {
            	this._mensagem.texto = "Negociação incluída com sucesso!";
            	this._limpaFormulario();
            }).catch(error => this._mensagem.texto = error);
    }
    
    apaga() {
    	this._negociacoesService.removeTodos(this._negociacoesList)
			.then(() => this._mensagem.texto = "Negociações apagadas com sucesso!")
			.catch(error => this._mensagem.texto = error);
    }
    
    importaNegociacoes(exibeMensagem) {
    	this._negociacoesService.importaNegociacoes(this._negociacoesList)
			.then(() => this._mensagem.texto = exibeMensagem ? "Negociações importadas com sucesso!" : "")
			.catch(error => this._mensagem.texto = error);
    }
    
    ordena(coluna) {
        if(this._ordemAtual == coluna) {
            this._negociacoesList.inverteOrdem();
        } else {
            this._negociacoesList.ordena((a, b) => a[coluna] - b[coluna]);    
        }
        this._ordemAtual = coluna;
    }
    
    _limpaFormulario() {
        this._inputData.value = "";
        this._inputQuantidade.value = 1;
        this._inputValor.value = 0.0;
        this._inputData.focus();   
    }
}