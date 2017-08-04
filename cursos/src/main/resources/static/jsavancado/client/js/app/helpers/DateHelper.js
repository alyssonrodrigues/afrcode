class DateHelper {
	
	static textoParaData(texto) {
		if (!/^\d{4}-\d{2}-\d{2}$/.test(texto)) {
			throw new Error("Not in yyyy-mm-dd format!");
		}
		let data = texto.split("-").map((item, indice) => item - indice % 2);
		return new Date(...data);
	}
	
	static dataParaTexto(data) {
		return `${data.getMonth() + 1}/${data.getDate()}/${data.getFullYear()}`;
	}
}