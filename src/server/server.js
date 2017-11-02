const path = require('path');
const bodyParser = require('body-parser');
const express = require('express');
const sqlConn = require('../database/connection');

const router = express();
sqlConn.connect((err) => {
    if (err) {
        console.log('Error connecting: ', err.stack);
        return;
    } else {
        console.log('Connected as id:  ',  sqlConn.threadId);
    }
});

router.use('/', express.static(path.resolve('src/client')));
router.use(bodyParser.urlencoded({extended: true}));
router.use(bodyParser.json());

router.get('/', (req, res) => {
    res.sendFile(path.resolve('src/client/index.html'));
});

router.post('/signin', (req, res) => {
    let user = req.body.user,
        password = req.body.password;
    console.log('Login: ', user, ' - ', password);

    let query = `SELECT * FROM user WHERE UserName='${user}' AND Password='${password}'`;

    sqlConn.query(query, (err, result, fields) => {
        if (err) {
            console.log('Error Querying: ', err.stack);
            return;
        } else {
            res.sendFile(path.resolve('src/client/homepage.html'));
        }
    });
})

router.get('/data', (req, res) => {
    res.send('Hello');
})

router.listen(3000, () => {
    console.log('Listening on port 3000...');
});