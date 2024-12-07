USE projet_gei_007;

CREATE TABLE TApplicant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    age INT,
    dpt INT NOT NULL,
    psw VARCHAR(100) NOT NULL
);

CREATE TABLE TVolunteer(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    age INT CHECK (age>17),
    dpt INT NOT NULL,
    note FLOAT DEFAULT NULL,
    nb_avis INT DEFAULT 0,
    psw VARCHAR(100) NOT NULL
);

CREATE TABLE TValidator (
    id INT AUTO_INCREMENT PRIMARY KEY ,
    nom VARCHAR(100) NOT NULL,
    age INT CHECK (age>17),
    dpt INT NOT NULL,
    orga VARCHAR(100) NOT NULL,
    psw VARCHAR(100) NOT NULL
);

SHOW STATUS WHERE `variable_name` = 'Threads_connected';

CREATE TABLE TRequest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subj VARCHAR(100) NOT NULL,
    id_volunteer INT DEFAULT NULL,
    id_applicant INT NOT NULL,
    id_validator INT DEFAULT NULL,
    statut VARCHAR(1) DEFAULT 'P', 
    helpday DATE NOT NULL,
    motif VARCHAR(100) DEFAULT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_volunteer) REFERENCES TVolunteer(id),
    FOREIGN KEY (id_validator) REFERENCES TValidator(id),
    FOREIGN KEY (id_applicant) REFERENCES TApplicant(id)
);