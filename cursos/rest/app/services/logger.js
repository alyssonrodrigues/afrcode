var winston = require('winston');

const logger = new (winston.Logger)({
        transports: [
            new (winston.transports.Console)(),
            new (winston.transports.File)({
                    name: 'server-log',
                    filename: 'server.log',
                    maxsize: 1048576,
                    maxFiles: 10
                }
            )
        ]
    }
);

module.exports = logger;