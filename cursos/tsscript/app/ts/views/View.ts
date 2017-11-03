import { log } from "../helpers/log";
export abstract class View<T> {
	constructor(private _elemento: JQuery) {
	}
	
	update(model: T) {
		this._elemento.html(this.template(model));
	}
	
	abstract template(model: T): string;
}