use erasmusTracking;
insert into account (nome, cognome, e_mail, password, ruolo) Values ('Silvio', 'Cresci', 'root@gmail.com','root','coordinatore');
insert into account (nome, cognome, e_mail, password, ruolo) Values ('Pascale', 'Capitone', 'pascale@gmail.com','root','studente');
insert into account (nome, cognome, e_mail, password, ruolo) Values ('Carlo', 'Di Domenico', 'chardido@gmail.com','pizza','amministratore');

insert into sendingInstitute (codice_erasmus, dipartimento, indirizzo) Values ('15784', 'Unisa', 'Fisciano');

insert into coordinatore (sending_institute, account) Values (1, 1);
insert into studente (matricola, data_nascita, luogo_nascita, sesso, nazionalita, telefono, ciclo_studi, anno_accademico, account, coordinatore) Values ('1234','12/12/12','Napoli', 'M', 'Italia', '377822', '1-triennale', 2, 2, 1);
insert into amministratore(account) Values (3);

insert into location (citta, nazione, coordinatore, codice_erasmus) Values ('Roma', 'Polonia', 1, 054783);

insert into learningAgreement (id_learning_agreement, tipologiaErasmus, stato, livello_conoscenza_lingua, studente) VALUES (1, 'studio', 'convalidato', 'A1', 2);

insert into receivingInstitute (nome_contatto, e_mail_contatto, size_of_enterprise, nome_mentore, e_mail_mentore, website, location) Values ('giorgio', 'gio@gmail.com', 'media', 'franco', 'fra@gmail.com', 'www.unirm.it', 1);