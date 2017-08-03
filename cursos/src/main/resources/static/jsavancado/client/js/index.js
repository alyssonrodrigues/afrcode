let data = document.querySelector("#data");
let quantidade = document.querySelector("#quantidade");
let valor = document.querySelector("#valor");

let form = document.querySelector("form");
let tbody = document.querySelector("table tbody");

form.addEventListener("submit", function (event) {
	event.preventDefault();

	let negociacao = new Negociacao(
			new Date(data.value), quantidade.value, valor.value);

	let tr = document.createElement("tr");
	
	negociacao.toArray().forEach(function(prop) {
		let td = document.createElement("td");
		td.textContent = prop;
		tr.appendChild(td);
	});
	
	tbody.appendChild(tr);
	
	data.value = "";
	quantidade.value = "1";
	valor.value = "0";
	data.focus();
});