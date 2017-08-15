class View<T> {
	constructor(private _elemento: Element) {
	}
	
	update(model: T) {
		this._elemento.innerHTML = this.template(model);
	}
	
	template(model: T): string {
		throw new Error("template method not implemented!");
	}
}