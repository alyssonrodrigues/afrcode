var http = require('http');

http.get(
    {
        hostname: 'localhost',
        port: 3000,
        path: '/',
        headers: {
            'Accept' : 'application/json'
        }
    },
    (response) => {
        response.on('data', (body) => console.log(`${body}`))
    }
);