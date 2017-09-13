var soap = require('soap');
var url = 'http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx?wsdl';

soap.createClient(url, 
    (error, client) => {
        client.CalcPrazo(
            {
                'nCdServico': '40010',
                'sCepOrigem': '04101300',
                'sCepDestino': '65000600'
            }, 
            (error, result) => {
                console.log(JSON.stringify(result));
        }
    );
});