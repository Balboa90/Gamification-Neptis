var todo = require('C:\\Users\\Anna\\Desktop\\todo');
module.exports = {
		configure: function(app){
			
			app.get('/getHeritagesGame1/',function(req,res){
				todo.getHeritagesGame1(res);
			});

			app.get('/getCoordinatesHeritage/:name',function(req,res){
				todo.getCoordinatesHeritage(req.params.name,res);
			});

			app.get('/getVisitedHeritagesCount/:code',function(req,res){
				todo.getVisitedHeritagesCount(req.params.code,res);
			});

			app.get('/getHeritagesCount/',function(req,res){
				todo.getHeritagesCount(res);
			});

			app.get('/getVisitedHeritagesGame2/:code',function(req,res){
				todo.getVisitedHeritagesGame2(req.params.code,res);
			});

			app.get('/getTreasureElements/:name',function(req,res){
				todo.getTreasureElements(req.params.name,res);
			});


			app.get('/getInfoTreasure/:code',function(req,res){
				todo.getInfoTreasure(req.params.code,res);
			});
			

			app.get('/getHeritagesGame2/',function(req,res){
				todo.getHeritagesGame2(res);
			});

			

			app.get('/getFoundTreasures/:code',function(req,res){
				todo.getFoundTreasures(req.params.code,res);
			});
				
			//********GESTIONE CARTE*********//
			app.get('/getCardCount/',function(req,res){
				todo.getCardCount(res);
			});

			app.get('/getCardCode/',function(req,res){
				todo.getCardCode(res);
			});

			app.get('/getAllCards/',function(req,res){
				todo.getAllCards(res);
			});

			/*app.get('/getTreasureCardInfo/:code',function(req,res){
				todo.getTreasureCardInfo(req.params.code,res);
			});*/

			app.get('/getTreasureCardInfo/:email/:code',function(req,res){
				todo.getTreasureCardInfo(req.params.email,req.params.code,res);
			});

			
				

	
			

			//********FINE GESTIONE CARTE*********//


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

			app.get('/getAchievementElements/:code/',function(req,res){
				todo.getAchievementElements(req.params.code,res);
			});
			
			/*app.get('/getAchievementDescr/:code',function(req,res){
				todo.getAchievementDescr(req.params.code,res);
			});*/

			app.get('/getUserFromSession/:session',function(req,res){
				todo.getUserFromSession(req.params.session,res);
			});

			//Insert email e password per login
			app.get('/createUser/:email/:password',function(req,res){
				todo.createUser(req.params.email,req.params.password,res);
			});

			app.get('/checkUser/:email/:password',function(req,res){
				todo.checkUser(req.params.email,req.params.password,res);
			});

			
			


			app.get('/createSession/:email/:password',function(req,res){
				todo.createSession(req.params.email,req.params.password,res);
			});

			app.get('/getSession/:email',function(req,res){
				todo.getSession(req.params.email,res);
			});

			app.get('/deleteSession/:email',function(req,res){
				todo.deleteSession(req.params.email,res);
			});

			app.get('/setPassword/:password/:email',function(req,res){
				todo.setPassword(req.params.password,req.params.email,res);
			});

			app.get('/setTitle/:title/:email',function(req,res){
				todo.setTitle(req.params.title,req.params.email,res);
			});

			app.get('/setCoins/:coins/:email',function(req,res){
				todo.setCoins(req.params.coins,req.params.email,res);
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