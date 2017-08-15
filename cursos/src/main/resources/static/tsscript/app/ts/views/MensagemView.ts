class MensagemView extends View<Mensagem> {
	template(model: Mensagem) {
		return model.texto ? 
				`<p class="alert alert-info">${model.texto}</p>` : 
					"<p></p>";
	}
}