class NegociacaoDao {
	constructor(connection, constants) {
		this._connection = connection;
		this._objStoreName = constants.negociacaoStoreName;
	}
	
    adiciona(negociacao) {
    	return new Promise((resolve, reject) => {
			let tx = this._connection.transaction([this._objStoreName], "readwrite");
			let objStore = tx.objectStore(this._objStoreName);
			let addRequest = objStore.add(negociacao);
			addRequest.onsuccess = event => resolve();
			addRequest.onerror = event => reject(event.target.error);
    	});
    }
    
    adicionaNegociacoes(negociacoes) {
    	let promises = [];
    	negociacoes.forEach(negociacao => 
    		promises.push(this.adiciona(negociacao)));
    	return Promise.all(promises);
    }

    removeTodos() {
    	return new Promise((resolve, reject) => {
			let tx = this._connection.transaction([this._objStoreName], "readwrite");
			let objStore = tx.objectStore(this._objStoreName);
			let removeRequest = objStore.clear();
			removeRequest.onsuccess = event => resolve();
			removeRequest.onerror = event =>  reject(event.target.error);
    	});
    }
    
    listaTodos() {
    	return new Promise((resolve, reject) => {
			let tx = this._connection.transaction([this._objStoreName], "readwrite");
			let objStore = tx.objectStore(this._objStoreName);
			let negociacoes = [];
			let cursorRequest = objStore.openCursor();
			cursorRequest.onsuccess = event => {
				let pointer = event.target.result;
				if (pointer) {
					let negociacaoStr = pointer.value;
					negociacoes.push(new Negociacao(
							new Date(negociacaoStr._data), 
							negociacaoStr._quantidade,
							negociacaoStr._valor));
					pointer.continue();
				} else {
					resolve(negociacoes);
				}
			};
			cursorRequest.onerror = event => resolve(event.target.error);
    	});
    }
}