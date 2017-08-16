export function dom(selector: string) {
    return function(target: any, prop: string) {
        let element: JQuery;
        const getter = function() {
            if (!element) {
                element = $(selector);
            }
            return element;
        }
        Object.defineProperty(target, prop, {get : getter});
    }
}