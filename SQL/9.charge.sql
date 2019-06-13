CREATE TABLE charge (
  	gId int(8) unsigned NOT NULL AUTO_INCREMENT,
	gAdminId int(5) unsigned NOT NULL,
	gBankId varchar(30) NOT NULL DEFAULT "",
	gTotalPrice int(10) unsigned NOT NULL,
	gDate datetime NOT NULL DEFAULT current_timestamp,
	gState varchar(10) DEFAULT "û����û",
  	PRIMARY KEY (gId),
	FOREIGN KEY (gAdminId) REFERENCES admins(aId),
	FOREIGN KEY (gBankId) REFERENCES bank(bId)
) AUTO_INCREMENT=10000001 DEFAULT CHARSET=utf8;