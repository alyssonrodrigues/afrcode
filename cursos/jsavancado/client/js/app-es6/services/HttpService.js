class HttpService {
	_handleErrors(result) {
		if (!result.ok) throw new Error(result.statusText);
		return result;
	}
	get(url) {
		return fetch(url)
			.then(result => this._handleErrors(result))
			.then(result => result.json());
	}
	
    post(url, dado) {
    	return fetch(url, {
    		headers : {"Content-type" : "application/json"},
    		method: "post",
    		body: JSON.stringify(dado)})
    		.then(result => this._handleErrors(result));
    }
}