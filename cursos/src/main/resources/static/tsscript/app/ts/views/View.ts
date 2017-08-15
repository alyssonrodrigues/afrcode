abstract class View<T> {
	constructor(private _elemento: Element) {
	}
	
	update(model: T) {
		this._elemento.innerHTML = this.template(model);
	}
	
	abstract template(model: T): string;
}