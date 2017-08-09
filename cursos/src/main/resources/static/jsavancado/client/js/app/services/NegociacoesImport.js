class NegociacoesImport {
	getNegociacoesSemana(callback) {
    	let xhr = new XMLHttpRequest();
    	xhr.open("GET", "negociacoes/semana");
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