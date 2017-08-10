class NegociacaoDao {
	constructor(connection, objStoreName) {
		this._connection = connection;
		this._objStoreName = objStoreName;
	}
	
    adiciona(data, quantidade, valor) {
    	this._connection.then(connection => {
    		let tx = connection.transaction([this._objStoreName], "readwrite");
    		let objStore = tx.objectStore(this._objStoreName);
    		let addRequest = objStore.add(new Negociacao(
    			new Date(data), quantidade, valor));
    		addRequest.onsuccess = event => {
    	        console.log(event.target.result);
    		};
    	    addRequest.onerror = event => {
    		    console.log(event.target.error);
    	    };
    	});
    }

    listaTodos() {
    	this._connection.then(connection => {
            let tx = connection.transaction([this._objStoreName], "readwrite");
            let objStore = tx.objectStore(this._objStoreName);
            let negociacoes = [];
            let cursorRequest = objStore.openCursor();
            cursorRequest.onsuccess = event => {
        	    let pointer = event.target.result;
        	    if (pointer) {
        		    negociacoes.push(pointer.value);
        		    pointer.continue();
        	    } else {
        		    console.log(negociacoes);
        	    }
            };
            cursorRequest.onerror = event => {
        	    console.log(event.target.error);
            };
        });
    }
}