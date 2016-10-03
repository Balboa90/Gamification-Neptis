var express = require ('express');
var bodyparser = require ('body-parser');
var connection = require('C:\\Users\\Anna\\Desktop\\connection');
var routes = require ('C:\\Users\\Anna\\Desktop\\routes');


var app = express ();
app.use(bodyparser.urlencoded({extended: true}));
app.use(bodyparser.json());

var server = app.listen(8000, function() {
	console.log('Server listening on port '+server.address().port);
});

connection.init();
routes.configure(app);