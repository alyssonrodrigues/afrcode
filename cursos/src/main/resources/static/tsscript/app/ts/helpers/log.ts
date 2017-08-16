export function log() {
    return function(target: any, prop: string, descriptor: PropertyDescriptor) {
        const cur = descriptor.value;
        descriptor.value = function(...args: any[]) {
            const a = performance.now();
            const r = cur.apply(this, args);
            const b = performance.now();
            console.log(`${prop} (rt): ${b - a} (ms)...`);
            return r;
        }
        return descriptor;
    };
}