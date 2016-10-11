var todo = require('C:\\Users\\Anna\\Desktop\\todo');
module.exports = {
		configure: function(app){
			
			app.get('/getHeritages/',function(req,res){
				todo.getHeritages(res);
			});

			app.get('/getHeritageLatitude/:name',function(req,res){
				todo.getHeritageLatitude(req.params.name,res);
			});

			app.get('/getHeritageLongitude/:name',function(req,res){
				todo.getHeritageLongitude(req.params.name,res);
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

			app.get('/getTreasureLatitude/:code',function(req,res){
				todo.getTreasureLatitude(req.params.code,res);
			});

			app.get('/getTreasureLongitude/:code',function(req,res){
				todo.getTreasureLongitude(req.params.code,res);
			});

			app.get('/getMedals/:type',function(req,res){
				todo.getMedals(req.params.type,res);
			});

			app.get('/getPuzzle/',function(req,res){
				todo.getPuzzle(res);
			});

			app.get('/getPuzzleFromHeritage/:name',function(req,res){
				todo.getPuzzleFromHeritage(req.params.name,res);
			});

			app.get('/getEnabledPuzzle/',function(req,res){
				todo.getEnabledPuzzle(res);
			});

			app.get('/getSoonPuzzle/',function(req,res){
				todo.getSoonPuzzle(res);
			});

			app.get('/getPuzzleDescription/:code',function(req,res){
				todo.getPuzzleDescription(req.params.code,res);
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

			app.get('/getAchievementName/:code/',function(req,res){
				todo.getAchievementName(req.params.code,res);
			});

			app.get('/getAchievementDescr/:code',function(req,res){
				todo.getAchievementDescr(req.params.code,res);
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