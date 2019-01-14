DROP database IF EXISTS erasmusTracking;
CREATE database erasmusTracking; USE erasmusTracking;


DROP TABLE IF EXISTS account;
CREATE TABLE account (
id_account int auto_increment not null primary key,
nome varchar(20) not null,
cognome varchar(20) not null,
e_mail varchar(40) not null,
password varchar(40) not null,
ruolo enum('studente','coordinatore','amministratore') not null
) engine=InnoDB;

DROP TABLE IF EXISTS amministratore;
CREATE TABLE amministratore (
account int primary key not null,
FOREIGN KEY (account) REFERENCES account(id_account)
) engine=InnoDB;

DROP TABLE IF EXISTS documenti;
CREATE TABLE documenti (
id_documento int auto_increment not null primary key,
nome varchar(50) not null,
data_caricamento varchar(10),
url blob not null,
proprietario int not null,
FOREIGN KEY (proprietario) REFERENCES account(id_account)
) engine=InnoDB;

DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
id_ticket int auto_increment not null primary key,
oggetto varchar(30) not null,
data_creazione varchar(10),
mittente int not null,
destinatario int not null,
stato enum('aperto', 'chiuso') not null,
FOREIGN KEY (mittente) REFERENCES account(id_account),
FOREIGN KEY (destinatario) REFERENCES account(id_account)
) engine=InnoDB;

DROP TABLE IF EXISTS messaggio_ticket;
CREATE TABLE messaggio_ticket (
id_messaggio int auto_increment not null primary key,
contenuto varchar(100) not null,
data_invio varchar(10),
ora_invio varchar(5),
ticket int not null,
proprietario int not null,
FOREIGN KEY (ticket) REFERENCES ticket(id_ticket),
FOREIGN KEY (proprietario) REFERENCES account(id_account)
) engine=InnoDB;

DROP TABLE IF EXISTS sendingInstitute;
CREATE TABLE sendingInstitute (
id_sending_institute int auto_increment not null primary key,
codice_erasmus varchar(10),
dipartimento varchar(50),
indirizzo varchar(40)
) engine=InnoDB;

DROP TABLE IF EXISTS coordinatore;
CREATE TABLE coordinatore (
sending_institute int,
account int primary key not null,
FOREIGN KEY (account) REFERENCES account(id_account),
FOREIGN KEY (sending_institute) REFERENCES sendingInstitute(id_sending_institute)
) engine=InnoDB;

DROP TABLE IF EXISTS studente;
CREATE TABLE studente (
matricola varchar(10) not null,
data_nascita varchar(10),
luogo_nascita varchar(30),
sesso enum('M','F'),
nazionalita varchar(20),
telefono varchar(15),
ciclo_studi enum('1-triennale','2-magistrale','3-dottorando'),
anno_accademico int,
account int primary key not null,
coordinatore int not null,
FOREIGN KEY (account) REFERENCES account(id_account),
FOREIGN KEY (coordinatore) REFERENCES coordinatore(account)
) engine=InnoDB;

DROP TABLE IF EXISTS learningAgreement;
CREATE TABLE learningAgreement (
id_learning_agreement int auto_increment not null primary key,
tipologiaErasmus enum('lavoro','studio'),
stato enum('convalidato','compilato'),
livello_conoscenza_lingua enum('A1','A2','A3','A4','A5','Native Speaker'),
studente int not null,
FOREIGN KEY (studente) REFERENCES studente(account)
) engine=InnoDB;

DROP TABLE IF EXISTS location;
CREATE TABLE location (
id_location int not null auto_increment primary key,
nome varchar(20),
codice_erasmus varchar (10),
citta varchar(30) not null,
nazione varchar(30) not null,
coordinatore int not null,
FOREIGN KEY (coordinatore) REFERENCES coordinatore(account)
) engine=InnoDB;

DROP TABLE IF EXISTS receivingInstitute;
CREATE TABLE receivingInstitute (
id_receiving_institute int auto_increment not null primary key,
nome_contatto varchar(40),
e_mail_contatto varchar(40),
size_of_enterprise varchar(20),
nome_mentore varchar(40),
e_mail_mentore varchar(40),
website varchar(100),
location int not null,
FOREIGN KEY (location) REFERENCES location(id_location)
) engine=InnoDB;

DROP TABLE IF EXISTS mobilitaErasmus;
CREATE TABLE mobilitaErasmus (
id_mobilita_erasmus int auto_increment not null primary key,
data_inizio varchar(10),
data_fine varchar(10),
stato varchar(10),
sending_institute int not null,
receiving_institute int not null,
learning_agreement int not null,
FOREIGN KEY (sending_institute) REFERENCES sendingInstitute(id_sending_institute),
FOREIGN KEY (receiving_institute) REFERENCES receivingInstitute(id_receiving_institute),
FOREIGN KEY (learning_agreement) REFERENCES learningAgreement(id_learning_agreement)
) engine=InnoDB;

DROP TABLE IF EXISTS mappingEsame;
CREATE TABLE mappingEsame (
id_mapping_esame int auto_increment not null primary key,
esame_interno varchar(30),
codice_esame_interno varchar(10),
ects_esame_interno int,
esame_esterno varchar(30),
codice_esame_esterno varchar(10),
ects_esame_esterno int,
lingua varchar(20),
stato boolean,
learning_agreement int not null,
FOREIGN KEY (learning_agreement) REFERENCES learningAgreement(id_learning_agreement)
) engine=InnoDB;