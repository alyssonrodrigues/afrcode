var soap = require('soap');
var url = 'http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?wsdl';

var dados = {
    'nCdServico': '40010',
    'sCepOrigem': '04101300',
    'sCepDestino': '65000600'
};

soap.createClient(url, (error, client) => {
    client.CalcPrazo(dados, (error, result) => {
        console.log(JSON.stringify(result));
    });
});