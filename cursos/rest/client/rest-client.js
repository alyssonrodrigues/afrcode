var restify = require('restify-clients');
var url = 'http://localhost:3000';

var client = restify.createJsonClient(url);

var pagamento = {
    valor: 19.99,
    descricao: 'Descrição qualquer!',
    data: '2017-09-13'
};

client.post('/api/pagamentos', pagamento, (error, request, response, result) => {
    if (error) {
        console.log(JSON.stringify(error));
        return;
    }
    console.log(JSON.stringify(result));
});

client.get('/api/pagamentos', (error, request, response, result) => {
    if (error) {
        console.log(JSON.stringify(error));
        return;
    }
    console.log(JSON.stringify(result));
});