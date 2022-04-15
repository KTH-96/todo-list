const express = require('express');
const path = require('path');
const port = process.env.PORT || 9000;
const app = express();

app.use(express.static(__dirname + '/bundle'));

app.get('*', (req, res) => {
  res.sendFile(path.resolve('index.html'));
});

app.listen(port);