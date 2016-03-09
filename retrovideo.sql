CREATE DATABASE IF NOT EXISTS retrovideo;
USE retrovideo;

DROP TABLE IF EXISTS genres;
CREATE TABLE genres (
  id int(11) NOT NULL AUTO_INCREMENT,
  naam varchar(20) NOT NULL,
  PRIMARY KEY (id),
  KEY Genre(naam) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


ALTER TABLE `genres` DISABLE KEYS;
INSERT INTO genres(id,naam) VALUES
 (1,'Aktiefilm'),
 (2,'Avontuur'),
 (3,'Cowboyfilm'),
 (4,'Erotiek'),
 (5,'Griezel'),
 (6,'Humor'),
 (7,'Kinderfilm'),
 (8,'Oorlog'),
 (9,'Piratenfilm'),
 (10,'Science fiction'),
 (11,'Sentimenteel'),
 (12,'Speelfilm'),
 (13,'Thriller');
ALTER TABLE `genres` ENABLE KEYS;


DROP TABLE IF EXISTS films;
CREATE TABLE films (
  id int(11) NOT NULL AUTO_INCREMENT,
  genreid int(11) NOT NULL,
  titel varchar(30) NOT NULL,
  voorraad int(10) unsigned NOT NULL,
  gereserveerd int(10) unsigned NOT NULL,
  prijs decimal(19,2) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_films_genres (genreid),
  KEY Titel (titel),
  CONSTRAINT FK_films_genres FOREIGN KEY (genreid) REFERENCES genres(id)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;


ALTER TABLE `films` DISABLE KEYS;
INSERT INTO films(id,genreid,titel,voorraad,gereserveerd,prijs) VALUES
 (1,2,'Raiders of the lost ark',3,0,'3.50'),
 (2,7,'Harry Potter',3,0,'3.00'),
 (3,11,'Love story',1,0,'3.00'),
 (4,4,'Two moon junction',8,0,'3.50'),
 (5,6,'Police academy',3,0,'3.50'),
 (6,3,'Once upon a time in the west',2,0,'3.00'),
 (7,2,'In de ban van de ring',3,0,'3.50'),
 (8,7,'Babe',2,0,'3.00'),
 (9,2,'Zorro',2,0,'3.50'),
 (10,6,'Hector',2,0,'3.50'),
 (11,3,'High noon',4,0,'3.00'),
 (12,9,'Captain blood',2,0,'3.00'),
 (13,2,'The last emperor',3,0,'3.50'),
 (14,12,'The deer hunter',9,0,'3.50'),
 (15,6,'The gods must be crazy',6,0,'3.50'),
 (16,13,'Silent night, deadly night',4,0,'3.00'),
 (17,13,'The gangs of new york',4,0,'3.00'),
 (18,13,'Kickboxer',4,0,'3.00'),
 (19,2,'Batman',12,0,'3.50'),
 (23,11,'Cramer vs Cramer',1,0,'3.00'),
 (24,11,'Titanic',5,0,'3.00'),
 (25,3,'El gringo',5,0,'3.00'),
 (26,11,'The graduate',3,0,'3.00'),
 (28,13,'The omen',5,0,'3.00'),
 (29,4,'Sex,lies and videotapes',0,0,'3.50'),
 (30,1,'Chicago',7,0,'3.00'),
 (31,7,'De smurfen',6,0,'3.00'),
 (32,13,'First blood',3,0,'3.00'),
 (33,4,'Her alibi',5,0,'3.50'),
 (34,8,'De langste dag',3,0,'3.50'),
 (35,8,'The guns of navarone',2,0,'3.50'),
 (37,2,'The revenge of jaws',6,0,'3.50'),
 (38,13,'Lock up',3,0,'3.00'),
 (39,5,'Hellraiser',5,0,'3.00'),
 (40,5,'The exorcist',2,0,'3.00'),
 (42,13,'Road house',5,0,'3.00'),
 (43,11,'Matador',5,0,'3.00'),
 (44,8,'Missing in action',4,0,'3.50'),
 (45,2,'Licence to kill',6,0,'3.50');
ALTER TABLE `films` ENABLE KEYS;


DROP TABLE IF EXISTS klanten;
CREATE TABLE klanten(
  id int(11) NOT NULL AUTO_INCREMENT,
  familienaam varchar(30) NOT NULL,
  voornaam varchar(20) NOT NULL,
  straatNummer varchar(30) NOT NULL,
  postcode varchar(10) NOT NULL,
  gemeente varchar(30) NOT NULL,
  PRIMARY KEY (id) USING BTREE,
  KEY Familienaam (familienaam)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


ALTER TABLE `klanten` DISABLE KEYS;
INSERT INTO klanten(id,familienaam,voornaam,straatNummer,postcode,gemeente) VALUES
 (1,'Heiremans','Inge','Koekelbergstraat 32','9330','Dendermonde'),
 (2,'Goessens','Joris','Diepeweg 1','9000','Gent'),
 (3,'Van delsen','Lode','Kouterstraat 10','9263','Bavegem'),
 (4,'Van den berghe','Piet','Melkerijstraat 34','8900','Ieper'),
 (5,'Van den bosche','Christel','Heirbaan 34','9311','Impe'),
 (6,'Verbiest','Karen','Dorpsstraat 35','9000','Gent'),
 (7,'Boelens','Luc','Gravenstraat 23','9402','Meerbeke'),
 (8,'Verplancken','Mia','Kempeland 3','9200','Wetteren'),
 (9,'Meert','Sabine','Oosthoek 23','9230','Melle'),
 (10,'Boelens','Kristel','Koekoekstraat 2','9000','Gent'),
 (11,'De clerq','Hilde','Molenstraat 23','9140','Zele'),
 (12,'De coninck','Philippe','Stationstraat 23','9402','Meerbeke'),
 (13,'Cousaert','Nathalie','Stationstraat 234','9300','Aalst'),
 (14,'De coninck','Kathleen','Vogelzang 34','9000','Gent'),
 (15,'Lorez','Veronique','Beverhoekstraat 23','9200','Wetteren'),
 (16,'Heyman','Lieve','Dendermondse stwg 112','9010','Gentbrugge'),
 (17,'Huysman','Ann','Noordlaan 12','9300','Aalst'),
 (18,'Gevaert','Jan','Wortegemstraat 3','1890','Opwijk'),
 (19,'Nijs','Pascal','Lindestraat 23','9200','Wetteren'),
 (20,'Coppens','Roland','Dorp 6','9411','Erondegem'),
 (21,'Gysels','Rita','Kasteeldreef 45','9000','Gent'),
 (22,'Janssens','Etienne','Blikstraat 21','9370','Lebbeke'),
 (23,'Goeman','Christine','Eikelstraat 345','9160','Hamme'),
 (24,'Van de sompel','Luc','Voermanstraat 45','9170','Waasmunster'),
 (25,'Van de poele','Trees','Stationstraat 11','9000','Gent'),
 (26,'Matthijs','Paul','Sticheldreef 37','9140','Zele'),
 (27,'Lefever','Hendrik','Lijsterstraat 2','9290','Berlare'),
 (28,'Lenaerds','Marc','Dragonderwegel 23','9281','Overmere'),
 (29,'Lampens','Lieven','Drapstraat 45','9282','Uitbergen'),
 (30,'Verpoest','Steven','Dammenlaan','9200','Dendermonde');
ALTER TABLE `klanten` ENABLE KEYS;

DROP TABLE IF EXISTS reservaties;
CREATE TABLE reservaties(
  klantid int(11) NOT NULL AUTO_INCREMENT,
  filmid int(11) NOT NULL,
  reservatieDatum datetime NOT NULL,
  PRIMARY KEY (klantid,filmid,reservatieDatum) USING BTREE,
  CONSTRAINT FK_reservaties_films FOREIGN KEY (filmid) REFERENCES films(id),
  CONSTRAINT FK_reservaties_klanten FOREIGN KEY (klantid) REFERENCES klanten(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

grant all on retrovideo.* to 'cursist'@'localhost' identified by 'cursist';