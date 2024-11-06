USE projet_gei_007;

CREATE TABLE TApplicant (
     id INT PRIMARY KEY NOT NULL,
    nom VARCHAR(100) NOT NULL,
    age INT,
    dpt INT NOT NULL,
    psw VARCHAR(100) NOT NULL
);

CREATE TABLE TVolunteer(
    id INT PRIMARY KEY NOT NULL,
    nom VARCHAR(100) NOT NULL,
    age INT CHECK (age>17),
    dpt INT NOT NULL,
    psw VARCHAR(100) NOT NULL
);

CREATE TABLE TValidator (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    nom VARCHAR(100) NOT NULL,
    age INT CHECK (age>17),
    dpt INT NOT NULL,
    orga VARCHAR(100) NOT NULL,
<<<<<<< HEAD
    note INT DEFAULT NULL,
    nb_avis INT DEFAULT 0
=======
>>>>>>> d5d1630 (ajout mdp)
    psw VARCHAR(100) NOT NULL
);

CREATE TABLE TRequest (
    id INT PRIMARY KEY NOT NULL,
    subj VARCHAR(100) NOT NULL,
    id_volunteer INT DEFAULT NULL,
    id_applicant INT NOT NULL,
    statut VARCHAR(1) DEFAULT 'P', 
    helpday DATE NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_volunteer) REFERENCES TVolunteer(id),
    FOREIGN KEY (id_applicant) REFERENCES TApplicant(id)
);