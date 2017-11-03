var app = require('../config/express')();
var supertest = require('supertest')(app);

describe('#ProdutosController', () => {
    const removerTodos = (done) => {
        let connection = app.services.connectionFactory();
        connection.query('delete from produtos', (error, result) => {
            if (error) {
                console.log(error);
                throw new Error(JSON.stringify(error));
            }
            done();
        });
    };

    beforeEach(removerTodos);
    afterEach(removerTodos);

    it('#recuperarTodosJSON', (done) => {
        supertest.get('/')
            .set('Accept', 'application/json')
            .expect('Content-type', /json/)
            .expect(200, done);
    });

    it('#recuperarTodosHTML', (done) => {
        supertest.get('/')
            .set('Accept', 'text/html')
            .expect('Content-type', /html/)
            .expect(200, done);
    });

    it('#salvarJSONProdutoInvalido', (done) => {
        supertest.post('/')
            .send({
                titulo: '',
                descricao: '',
                preco: ''
            })
            .expect(400, done);
    });

    it('#salvarJSON', (done) => {
        supertest.post('/')
            .send({
                titulo: 'Abobrinhas',
                descricao: 'Sobre Abobrinhas',
                preco: '1.55'
            })
            .expect(302, done);
    });
});