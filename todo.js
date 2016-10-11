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

	//tutti i game (per recuperare latitudine e longitudine dei diversi heritage)
	this.getHeritageLatitude = function(name,res){
		connection.acquire(function(err,con){
			con.query('SELECT latitude from heritage where name = ?',name, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};
	this.getHeritageLongitude = function(name,res){
		connection.acquire(function(err,con){
			con.query('SELECT longitude from heritage where name = ?',name, function(err,result){
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



	//per scrollbar game1
	this.getTreasureCode = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT code from treasure', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//tutti i game (per recuperare latitudine e longitudine dei diversi treasure)
	this.getTreasureLatitude = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT latitude from treasure where code = ?',code, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};
	this.getTreasureLongitude = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT longitude from treasure where code = ?',code, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};



	
	//per tutti i game (medaglie )
	this.getMedals= function(type,res){
		connection.acquire(function(err,con){
			con.query('SELECT name from medal where type = ?',type, function(err,result){
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
	this.getPuzzleFromHeritage = function(name,res){
		connection.acquire(function(err,con){
			con.query('SELECT code from puzzle where heritage = ?',name, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game3 (livelli)
	this.getPuzzleLevel = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT level from puzzle where code = ?',code, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game3 (puzzle attivati)
	this.getEnabledPuzzle = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT code from puzzle where enabled = 1', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game3 (puzzle disattivati)
	this.getSoonPuzzle = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT code from puzzle where enabled = 0', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game3 (descrizione puzzle)
	this.getPuzzleDescription = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT description from puzzle where code = ?',code, function(err,result){
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
	this.getAchievementName = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT name from achievement where code = ?', code, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};


	//per tutti i games(achievement description)
	this.getAchievementDescr = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT description from achievement where code = ?', code, function(err,result){
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