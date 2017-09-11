module.exports = (app) => {
    app.get('/', (request, response) => {
        response.send('<html><head></head><body><h1>Hello World!</h1></body></html>');
    });
};