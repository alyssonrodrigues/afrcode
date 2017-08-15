export class DateHelper {

	static textoParaData(texto: string) {
		if (!/^\d{2}\/\d{2}\/\d{4}$/.test(texto)) {
			throw new Error("Not in mm/dd/yyyy format!");
		}
		return new Date(texto);
	}
	
	static dataParaTexto(data: Date) {
		return `${data.getMonth() + 1}/${data.getDate()}/${data.getFullYear()}`;
	}
}