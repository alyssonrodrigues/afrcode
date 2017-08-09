class NegociacoesImport {
	getNegociacoesRetrasada(callback) {
    	return this._getNegociacoes("negociacoes/retrasada", callback);
	}

	getNegociacoesAnterior(callback) {
    	return this._getNegociacoes("negociacoes/anterior", callback);
	}

	getNegociacoesSemana(callback) {
    	return this._getNegociacoes("negociacoes/semana", callback);
	}
	
	_getNegociacoes(url, callback) {
    	let xhr = new XMLHttpRequest();
    	xhr.open("GET", url);
    	xhr.onreadystatechange = () => {
	    	if(xhr.readyState == 4) {
	    		if (xhr.status == 200) {
	    			let negociacoesArray = JSON.parse(xhr.responseText)
	    				.map(obj => new Negociacao(
	    						new Date(obj.data), 
	    						obj.quantidade, 
	    						obj.valor));
	    			callback(null, negociacoesArray);
	    		} else {
	    			callback(xhr.responseText, null);
	    		}
	    	}
    	};
    	xhr.send();
    	return true;
	}
}