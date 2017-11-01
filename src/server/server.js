const path = require('path');
const express = require('express');
const bodyParser = require('body-parser');
const router = express();

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
    res.sendFile(path.resolve('src/client/homepage.html'));
})

router.get('/data', (req, res) => {
    res.send('Hello');
})

router.listen(3000, () => {
    console.log('Listening on port 3000...');
});