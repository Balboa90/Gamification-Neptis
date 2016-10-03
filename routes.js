var todo = require('C:\\Users\\Anna\\Desktop\\todo');
module.exports = {
		configure: function(app){
			
			app.get('/getHeritages/',function(req,res){
				todo.getHeritages(res);
			});

			app.get('/getHeritagesCoordinates/',function(req,res){
				todo.getHeritagesCoordinates(res);
			});

			app.get('/getVisitedHeritagesCount/',function(req,res){
				todo.getVisitedHeritagesCount(res);
			});

			app.get('/getHeritagesCount/',function(req,res){
				todo.getHeritagesCount(res);
			});

			app.get('/getVisitedHeritagesCoordinates/',function(req,res){
				todo.getVisitedHeritagesCoordinates(res);
			});

			app.get('/getTreasureCoordinates/',function(req,res){
				todo.getTreasureCoordinates(res);
			});

			app.get('/getTreasureCode/',function(req,res){
				todo.getTreasureCode(res);
			});

			app.get('/getMedals/',function(req,res){
				todo.getMedals(res);
			});

			app.get('/getPuzzle/',function(req,res){
				todo.getPuzzle(res);
			});

			app.get('/getPuzzleFromHeritage/',function(req,res){
				todo.getPuzzleFromHeritage(res);
			});


			app.get('/getAchievementGame1/',function(req,res){
				todo.getAchievementGame1(res);
			});

			app.get('/getAchievementGame2/',function(req,res){
				todo.getAchievementGame2(res);
			});
			app.get('/getAchievementGame3/',function(req,res){
				todo.getAchievementGame3(res);
			});

			app.get('/getAchievementGame4/',function(req,res){
				todo.getAchievementGame4(res);
			});

			app.get('/getAchievementDescr/',function(req,res){
				todo.getAchievementDescr(res);
			});










			app.post('/post/',function(req,res){
				todo.create(req.body, res);
			});
			app.put('/todo/',function(req,res){
				todo.update(req.body, res);
			});
			app.delete('/todo/:id/',function(req,res){
				todo.cancel(req.params.id, res);
			});
			
		}
};