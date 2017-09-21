var winston = require('winston');

const logger = new (winston.Logger)({
        transports: [
            new (winston.transports.Console)(),
            new (winston.transports.File)({
                    name: 'server-log',
                    filename: 'server.log'
                }
            )
        ]
    }
);

module.exports = logger;