class View {
	constructor(private _elemento: Element) {
	}
	
	update(model: any) {
		this._elemento.innerHTML = this.template(model);
	}
	
	template(model: any): string {
		throw new Error("template method not implemented!");
	}
}