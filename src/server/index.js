const path = require('path');
const express = require('express');
const app = express();

app.get('/', (req, res) => {
    res.sendFile(path.resolve('src/client/index.html'));
});

app.use('/js', express.static(path.resolve('src/client/js')));
app.use('/css', express.static(path.resolve('src/client/css')));
app.use('/image', express.static(path.resolve('src/client/image')));

app.listen(3000, () => {
    console.log('App listening on port 3000...');
});