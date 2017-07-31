var pacientes = document.querySelectorAll(".paciente");

for (var i = 0; i < pacientes.length; i++) {
	var paciente = pacientes[i];
	var tdPeso = paciente.querySelector(".info-peso");
	var tdAltura = paciente.querySelector(".info-altura");

	var tdImc = paciente.querySelector(".info-imc");

	var peso = tdPeso.textContent;
	var altura = tdAltura.textContent;

	var alturaEhValida = true;
	var pesoEhValido = true;

	if (!validaPeso(peso)) {
		console.log("Peso inválido");
		tdImc.textContent = "Peso inválido!";
		pesoEhValido = false;
		paciente.classList.add("paciente-invalido");
	}

	if (!validaAltura) {
		console.log("Altura inválida");
		tdImc.textContent = "Altura inválida!";
		alturaEhValida = false;
		paciente.classList.add("paciente-invalido");
	}

	if (pesoEhValido && alturaEhValida) {
		var imc = calculaImc(peso, altura);
		tdImc.textContent = imc;
	}
}

function validaAltura(altura) {
	return altura > 0 && altura < 10;
}

function validaPeso(peso) {
	return peso > 0 && peso < 1000
}

function calculaImc(peso, altura) {
	var imc = peso / (altura * altura);
	return imc.toFixed(2);
}