class View {
    constructor(_elemento) {
        this._elemento = _elemento;
    }
    update(model) {
        this._elemento.html(this.template(model));
    }
}
