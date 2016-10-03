var connection = require('C:\\Users\\Anna\\Desktop\\connection');

function Todo(){

	//game1 (per lente)
	this.getHeritages = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT distinct heritage from g1h', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game1 e 2 e 3
	this.getHeritagesCoordinates = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT coordinates from heritage', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game2 (insieme degli heritage visitati)
	this.getVisitedHeritagesCount = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT count(distinct heritage) as visitConto from g2h', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game2 (insieme degli heritage)
	this.getHeritagesCount = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT count(name) as conto from heritage', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};


	//game2 per punti rossi e verdi (coordinate)
	this.getVisitedHeritagesCoordinates = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT coordinates from heritage WHERE name IN(SELECT heritage from g2h)', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//utile per tutti i portali
	this.getTreasureCoordinates = function(todo,res){
		connection.acquire(function(err,con){
			con.query('SELECT coordinates from treasure WHERE heritage = ?',todo, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//per scrollbar game1
	this.getTreasureCode = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT code from treasure', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	
	//per tutti i game (medaglie )
	this.getMedals= function(todo,res){
		connection.acquire(function(err,con){
			con.query('SELECT name from medal where type = ?',todo, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game3 
	this.getPuzzle = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT code from puzzle', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};
	
	//game3 
	this.getPuzzleFromHeritage = function(todo,res){
		connection.acquire(function(err,con){
			con.query('SELECT code from puzzle where heritage = ?',todo, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game3 (livelli)
	this.getPuzzleLevel = function(todo,res){
		connection.acquire(function(err,con){
			con.query('SELECT level from puzzle where code = ?',todo, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game1 (achievement)
	this.getAchievementGame1 = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT achievement from ag1', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game2 (achievement)
	this.getAchievementGame2 = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT achievement from ag2', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game3 (achievement)
	this.getAchievementGame3 = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT achievement from ag3', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game4 (achievement)
	this.getAchievementGame4 = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT achievement from ag4', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//per tutti i games(achievement description)
	this.getAchievementDescr = function(todo,res){
		connection.acquire(function(err,con){
			con.query('SELECT description from achievement where code = ?', todo, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};


	
	this.create = function (todo,res){
		connection.acquire(function(err,con){
			con.query('INSERT into tab1 set ?',todo,function(err,result){
				con.release();
				if(err){
					res.send({status: 1, message: 'TODO creation failed'});
				} else {
					res.send({status: 0, message: 'TODO created successfully'});
				}
			});
		});
	};
	
	this.update = function (todo,res){
		connection.acquire(function(err,con){
			con.query('UPDATE into tab1 set ? WHERE id = ?',[todo,todo.id],function(err,result){
				con.release();
				if(err){
					res.send({status: 1, message: 'TODO update failed'});
				} else {
					res.send({statu: 0, message: 'TODO updated successfully'});
				}
			});
		});
	};
	
	this.del = function (id,res){
		connection.acquire(function(err,con){
			con.query('DELETE from tab1 WHERE id = ?',[id],function(err,result){
				con.release();
				if(err){
					res.send({status: 1, message: 'Failed to delete'});
				} else {
					res.send({statu: 0, message: 'Deleted successfully'});
				}
			});
		});
	};
	
}

module.exports = new Todo();