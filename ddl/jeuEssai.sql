-- alter table Personne modify dateNaissance date after prenom;

	
-- ============================================================
--   Jeu d'essai                               
-- ============================================================
insert into Personne values(1,'Jacob','Ismael','1999-05-06'); 
insert into Personne values(2,'Reynaud','Hugo','1997-02-03'); 
insert into Personne values(3,'Blanchard','Kenza','1990-07-08');   
insert into Personne values(4,'Floch','Alain','1988-02-09');  
commit;

select * from Personne;


insert into Adresse values(1,'21 rue la rode','aix',4);
insert into Adresse values(2,'06 Avenue St jean','toulon',3);
insert into Adresse values(3,'98 Bd champs de mars','nancy',2);
insert into Adresse values(4,'2 rue nationale','le mans',1);
commit;

select * from Adresse;


insert into Numero values(1,'0707070707','Mobile'); 
insert into Numero values(2,'0255783669','Domicile'); 
insert into Numero values(3,'0255693652','Pro'); 
insert into Numero values(4,'0604112222','Mobile');  
insert into Numero values(5,'0455693652','Pro'); 
insert into Numero values(6,'0724979230','Mobile');  
insert into Numero values(7,'0601111425','Mobile');  
insert into Numero values(8,'0506251478','Domicile');
insert into Numero values(9,'0332615488','Domicile');
insert into Numero values(10,'0902154854','Pro');
commit;


select * from Numero;


insert into Personne_Numero(personnes_id,numeros_id) values(1,8); 
insert into Personne_Numero(personnes_id,numeros_id) values(1,4); 
insert into Personne_Numero(personnes_id,numeros_id) values(1,10); 
insert into Personne_Numero(personnes_id,numeros_id) values(2,8); 
insert into Personne_Numero(personnes_id,numeros_id) values(2,5); 
insert into Personne_Numero(personnes_id,numeros_id) values(2,7);
insert into Personne_Numero(personnes_id,numeros_id) values(1,3); 
insert into Personne_Numero(personnes_id,numeros_id) values(3,9); 
insert into Personne_Numero(personnes_id,numeros_id) values(3,7); 
insert into Personne_Numero(personnes_id,numeros_id) values(3,3); 
insert into Personne_Numero(personnes_id,numeros_id) values(4,2); 
insert into Personne_Numero(personnes_id,numeros_id) values(4,9); 
insert into Personne_Numero(personnes_id,numeros_id) values(4,6); 
commit;

select * from Personne_Numero;



