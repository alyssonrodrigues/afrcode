var http = require('http');

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
        response.on('data', (body) => console.log(`${body}`))
    }
);

http.request(
    {
        hostname: 'localhost',
        port: 3000,
        path: '/',
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    },
    (response) => {
        response.on('data', (body) => console.log(`${body}`))
    }
).end(JSON.stringify(
    {
        titulo: 'Oceanos',
        descricao: 'Sobre Oceanos',
        preco: '1.99'
    }
));