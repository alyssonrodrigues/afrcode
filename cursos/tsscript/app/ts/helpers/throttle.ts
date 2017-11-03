export function throttle(delay = 500) {
    return function(target: any, propertyKey: string, descriptor: PropertyDescriptor) {
        const cur = descriptor.value;
        let timer = 0;
        descriptor.value = function(...args: any[]) {
            clearInterval(timer);
            timer = setTimeout(() => cur.apply(this, args), delay);
        }
        return descriptor;
    }
}