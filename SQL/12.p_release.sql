CREATE TABLE p_release (
  	rId int(8) unsigned NOT NULL AUTO_INCREMENT,
	rInvoiceId varchar(20) NOT NULL DEFAULT "", 
	rTransportId int unsigned default 0,
	rDate datetime NOT NULL DEFAULT current_timestamp,
	rState varchar(10) DEFAULT "��ۿ�û",
  	PRIMARY KEY (rId),
	FOREIGN KEY (rInvoiceId) REFERENCES invoice(vId)
) AUTO_INCREMENT=40000001 DEFAULT CHARSET=utf8;
