class DateHelper {
	
	static textoParaData(texto) {
		if (!/^\d{2}\/\d{2}\/\d{4}$/.test(texto)) {
			throw new Error("Not in dd/mm/yyyy format!");
		}
		return new Date(texto);
	}
	
	static dataParaTexto(data) {
		return `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`;
	}
}