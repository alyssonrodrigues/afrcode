var cluster = require('cluster');

if (cluster.isMaster) {
    cluster.on('listening', 
        worker => console.log(`Thread[${worker.process.pid}] is running...`));
    for (i = 0; i < 2; i++) {
        cluster.fork();
    }
} else {
    require('./index.js');
}