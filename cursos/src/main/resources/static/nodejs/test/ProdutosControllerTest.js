var http = require('http');
var assert = require('assert');

describe('#ProdutosController', () => {
    it('#recuperarTodos', (done) => {
        http.get(
            {
                hostname: 'localhost',
                port: 3000,
                path: '/',
                headers: {
                    'Accept': 'application/json'
                }
            },
            (response) => {
                assert.equal(response.statusCode, 200);
                assert.equal(response.headers['content-type'], 
                    'application/json; charset=utf-8');
                response.on('data', (body) => console.log(`${body}`));
                done();
            }
        );
    });
});