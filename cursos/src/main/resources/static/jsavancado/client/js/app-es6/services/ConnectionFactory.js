class ConnectionFactory {
	constructor(dbName, dbVersion, ...objStoreNames) {
		this._dbName = dbName;
		this._dbVersion = dbVersion;
		this._objStoreNames = objStoreNames;
		this._connection = null;
		this._connectionClose = null;
	}
	
	getConnection() {
		return new Promise((resolve, reject) => {
			if (this._connection) {
				resolve(this._connection);
			}
	        let dbRequest = window.indexedDB.open(this._dbName, this._dbVersion);
	        dbRequest.onupgradeneeded = event => {
	        	let connection = event.target.result;
	        	this._criaObjStores(connection);
	        };
	        dbRequest.onsuccess = event => {
	        	this._connection = event.target.result;
	        	this._connectionClose = 
	        		this._connection.close.bind(this._connection);
	        	this._connection.close = function() { 
	        		throw new Error("use connectionFactory.closeConnection()!");
	        	}
	        	resolve(this._connection);
	        };
	        dbRequest.onerror = event => {
	        	reject(event.target.error);
	        };
		});
	}
	
	closeConnection() {
		this._connectionClose();
		this._connection = null;
		this._connectionClose = null;
	}
	
	_criaObjStores(connection) {
    	this._objStoreNames.forEach(objStoreName => {
    		if (connection.objectStoreNames.contains(objStoreName)) {
    			connection.deleteObjectStore(objStoreName);
    		}
    		connection.createObjectStore(objStoreName, 
    			{autoIncrement: true})
    	});
	}
}