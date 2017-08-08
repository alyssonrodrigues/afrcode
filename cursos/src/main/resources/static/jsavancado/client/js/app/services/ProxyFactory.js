class ProxyFactory {
    static create(objeto, props, acao) {
        return new Proxy(objeto, {
                get(target, prop, receiver) {
                	if(!props.includes(prop)) {
                		return Reflect.get(target, prop, receiver);
                	}
                	if (ProxyFactory._ehFuncao(target[prop])) {
                        return function() {
                            let retorno = Reflect.apply(target[prop], target, arguments);
                            acao(target);
                            return retorno;
                        }
                	} else {
                        let retorno = Reflect.apply(target[prop], target, arguments);
                        acao(target);
                        return retorno;
                	}
                },
                set(target, prop, value, receiver) {
                    let retorno = Reflect.set(target, prop, value, receiver);
                    if(props.includes(prop)) {
                    	acao(target);
                    }
                    return retorno;
                }
        });
    }
    
    static _ehFuncao(func) {
        return typeof(func) == typeof(Function);
    }
}