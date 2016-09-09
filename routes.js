var todo = require('C:\\Program Files\\eclipse\\Progetti\\ProvaNode\\todo');
module.exports = {
		configure: function(app){
			
			app.get('/todo/',function(req,res){
				todo.get(res);
			});
			app.post('/todo/',function(req,res){
				todo.create(req.body, res);
			});
			app.put('/todo/',function(req,res){
				todo.update(req.body, res);
			});
			app.del('/todo/:id/',function(req,res){
				todo.cancel(req.params.id, res);
			});
			
		}
};
