--
-- MISSION 0 - C/S 2 TIERS   JAVA + MYSQL
-- CAS : GESTION DES COMMUNES
-- 
--


--
-- CREATION DE LA BASE DE DONNEES -------------------
--
CREATE DATABASE missionZero ;

USE missionZero ; 

--
-- CREATION DE LA TABLE -----------------------------
--
CREATE TABLE communes (
	codePostal	INT 			NOT NULL ,
	nomCommune	VARCHAR(50) 	NOT NULL , 
	nbHabitants	INT
);

ALTER TABLE communes 
	ADD CONSTRAINT communes_pk 
	PRIMARY KEY (codePostal) ; 
	
--
-- NOTRE JEU D'ESSAI  -------------------------------
-- ajout de 4 communes pour essayer 
INSERT INTO communes VALUES(97400 , 'SAINT DENIS'   , 150000 ); 
INSERT INTO communes VALUES(97438 , 'STE MARIE' 	,  35000 ); 
INSERT INTO communes VALUES(97420 , 'LE PORT'  		,  35000 ); 
INSERT INTO communes VALUES(97426 , 'TROIS BASSINS' ,   8000 ); 