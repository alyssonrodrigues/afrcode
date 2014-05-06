var NavegadorUtil = {
    init: function () {
        this.navegador = this.identificarNavegador(this.dadosNavegadores) || "Navegador desconhecido";
        this.versao = this.identificarVersion(navigator.userAgent) || this.identificarVersion(navigator.appVersion) || "Versão desconhecida";
    },

    identificarNavegador: function (dadosNavegadores) {
        for (var i = 0 ; i < dadosNavegadores.length ; i++) {
            var umUa = dadosNavegadores[i].ua;
            this.versaoString = dadosNavegadores[i].subString;

            if (umUa.indexOf(dadosNavegadores[i].subString) != -1) {
                return dadosNavegadores[i].id;
            }
        }
    },

    identificarVersion: function (dados) {
        var index = dados.indexOf(this.versaoString);
        if (index == -1) {
        	return;
        } else {
            return parseFloat(dados.substring(index + this.versaoString.length + 1));
        }
    },
    
    identificarEAlertarSeNavegadorNaoSuportado: function() {
    	this.init();
        if (this.navegador == "Explorer" && this.versao < 9) {
        	return true;
        }
        return false;
    },

    dadosNavegadores: [
        { ua: navigator.userAgent, subString: "Chrome",  id: "Chrome" },
        { ua: navigator.userAgent, subString: "MSIE",    id: "Explorer" },
        { ua: navigator.userAgent, subString: "Firefox", id: "Firefox" },
        { ua: navigator.userAgent, subString: "Safari",  id: "Safari" },
        { ua: navigator.userAgent, subString: "Opera",   id: "Opera" }
    ]
};
