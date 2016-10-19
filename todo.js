var connection = require('C:\\Users\\Anna\\Desktop\\connection');
var hash = require('C:\\Users\\Anna\\Desktop\\hash');

function Todo(){

	//game1 (per lente)
	this.getHeritagesGame1 = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT distinct heritage from g1h', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//tutti i game (per recuperare latitudine e longitudine dei diversi heritage)
	this.getCoordinatesHeritage = function(name,res){
		connection.acquire(function(err,con){
			con.query('SELECT latitude,longitude from heritage where name = ?',name, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};
	
	//game2 (insieme degli heritage visitati nel game2)
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

	//game1 (per game2)
	this.getVisitedHeritagesGame2 = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT distinct heritage from g2h', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game1 ottieni il codice del tesoro
	/*this.getTreasureCode = function(res){
		connection.acquire(function(err,con){
			con.query('SELECT code from treasure', function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//coordinate singoli tesori game1
	this.getCoordinatesTreasure = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT latitude,longitude from treasure where code = ?',name, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	//game1: ottieni il numero dei tesori relativi all'heritage passato come parametro
	this.getHeritageTreasureNumber = function(name,res){
		connection.acquire(function(err,con){
			con.query('SELECT count(treasure) as treasureCount from TH where heritage = ?',name, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};*/

	//game1:ottieni tutti gli elementi del tesoro relativo all'heritage passato come parametro
	this.getTreasureElements = function(name,res){
		connection.acquire(function(err,con){
			con.query('SELECT code,latitude,longitude,info from treasure where heritage = ?', name,function(err,result){
				con.release();
				res.send(result);
			});
		});
	};



	//game1: ottieni i tesori trovati(saranno i marker verdi nella mappa)
	this.getFoundTreasures = function(name,res){
		connection.acquire(function(err,con){
			con.query('SELECT found from MT where treasure = ?',name, function(err,result){
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

	//tutti i game: restituisce tutti gli elementi dell'achievement passato come paramentro
	this.getAchievementElements = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT name,description from achievement where code = ?', code, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};




	//per tutti i games(achievement description)
	/*this.getAchievementDescr = function(code,res){
		connection.acquire(function(err,con){
			con.query('SELECT description from achievement where code = ?', code, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};*/


	//per tutti i games(achievement description)
	this.getUserFromSession = function(session,res){
		connection.acquire(function(err,con){
			con.query('SELECT email from user where session = ?', session, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};


	//Insert user 
	this.createUser = function(email,password,res){
		connection.acquire(function(err,con){
			con.query('INSERT into user (email,password) values (?,?)', [email, password], function(err,result){
				con.release();
				res.send(result);
			});
		});
	};

	this.checkUser = function(email,password,res){
		connection.acquire(function(err,con){
			con.query('SELECT EXISTS (SELECT * from user where email=? and password=?)', [email, password], function(err,result){
				con.release();
				res.send(result);
			});
		});
	};



	//Insert token  
	this.createSession = function(email,password,res){
		var token = hash.createToken(email,password);
		connection.acquire(function(err,con){
			con.query('UPDATE user set session=? where email=?', [token, email], function(err,result){
				con.release();
				res.send(token);
			});
		});
	};	

	this.getSession = function(email,res){
		connection.acquire(function(err,con){
			con.query('SELECT session from user where email = ?', email, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};


	//Delete token  
	this.deleteSession = function(email,res){
		connection.acquire(function(err,con){
			con.query('UPDATE user set session=NULL where email=?', email, function(err,result){
				con.release();
				res.send(result);
			});
		});
	};	

	//Insert password
	this.setPassword = function(password,email,res){
		connection.acquire(function(err,con){
			con.query('UPDATE user set password=? where email=?', [password,email], function(err,result){
				con.release();
				res.send(result);
			});
		});
	};


	//Insert title
	this.setTitle = function(title,email,res){
		connection.acquire(function(err,con){
			con.query('UPDATE user set title=? where email=?', [title,email], function(err,result){
				con.release();
				res.send(result);
			});
		});
	};


	//Insert coins
	this.setCoins = function(coins,email,res){
		connection.acquire(function(err,con){
			con.query('UPDATE user set coins=? where email=?', [coins,email], function(err,result){
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