email = "ale@hotmail.it";
password = "ciao";
unisci = email+password;
sequenzaAscii = "";
valore = 0;
for (i=0; i<unisci.length; i++) {
	valore = unisci.charCodeAt(i);
	valoreHash = (valore % 89);
	sequenzaAscii = sequenzaAscii + valoreHash;
}
console.log(sequenzaAscii);