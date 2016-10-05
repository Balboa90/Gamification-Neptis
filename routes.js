var todo = require('C:\\Users\\Anna\\Desktop\\todo');
module.exports = {
		configure: function(app){
			
			app.get('/getHeritages/',function(req,res){
				todo.getHeritages(res);
			});

			app.get('/getHeritageLatitude/',function(req,res){
				todo.getHeritageLatitude(res);
			});

			app.get('/getHeritageLongitude/',function(req,res){
				todo.getHeritageLongitude(res);
			});

			
			app.get('/getVisitedHeritagesCount/',function(req,res){
				todo.getVisitedHeritagesCount(res);
			});

			app.get('/getHeritagesCount/',function(req,res){
				todo.getHeritagesCount(res);
			});


			app.get('/getTreasureCode/',function(req,res){
				todo.getTreasureCode(res);
			});

			app.get('/getTreasureLatitude/',function(req,res){
				todo.getTreasureLatitude(res);
			});

			app.get('/getTreasureLongitude/',function(req,res){
				todo.getTreasureLongitude(res);
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

			app.get('/getEnabledPuzzle/',function(req,res){
				todo.getEnabledPuzzle(res);
			});

			app.get('/getSoonPuzzle/',function(req,res){
				todo.getSoonPuzzle(res);
			});

			app.get('/getPuzzleDescription/',function(req,res){
				todo.getPuzzleDescription(res);
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

			app.get('/getAchievementName/',function(req,res){
				todo.getAchievementDescr(res);
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