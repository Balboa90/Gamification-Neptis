var express = require ('express');
var bodyparser = require ('body-parser');
var connection = require('C:\\Program Files\\eclipse\\Progetti\\ProvaNode\\connection');
var routes = require ('C:\\Program Files\\eclipse\\Progetti\\ProvaNode\\routes');


var app = express ();
app.use(bodyparser.urlencoded({extended: true}));
app.use(bodyparser.json());

var server = app.listen(8000, function() {
	console.log('Server listening on port '+server.address().port);
});

connection.init();
routes.configure(app);
