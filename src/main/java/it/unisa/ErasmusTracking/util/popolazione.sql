use erasmusTracking;
insert into account (nome, cognome, e_mail, password, ruolo) Values ('Silvio', 'Cresci', 'root@gmail.com','root','coordinatore');
insert into account (nome, cognome, e_mail, password, ruolo) Values ('Pascale', 'Capitone', 'pascale@gmail.com','root','studente');

insert into coordinatore (sending_institute, account) Values (null, 1);
insert into studente (matricola, data_nascita, luogo_nascita, sesso, nazionalita, telefono, ciclo_studi, anno_accademico, account, coordinatore) Values ('1234','12/12/12','Napoli', 'M', 'Italia', '377822', '1-triennale', 2, 2, 1);

insert into location (citta, nazione, coordinatore, codice_erasmus) Values ('Roma', 'Polonia', 1, 054783);

