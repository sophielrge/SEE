USE projet_gei_007;

DROP TABLE IF EXISTS TApplicant;
CREATE TABLE TApplicant (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL , 
    nom VARCHAR(100) NOT NULL,
    age INT,
    dpt INT NOT NULL,
    psw VARCHAR(100) NOT NULL
);

CREATE TABLE TVolunteer(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
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
    psw VARCHAR(100) NOT NULL
);

CREATE TABLE TRequest (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    subj VARCHAR(100),
    id_volunteer INT DEFAULT NULL,
    id_applicant INT NOT NULL,
    id_validator INT DEFAULT NULL,
    statut VARCHAR(1) DEFAULT 'P', 
    helpday DATE,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    motif VARCHAR(100) DEFAULT NULL,
    FOREIGN KEY (id_volunteer) REFERENCES TVolunteer(id),
    FOREIGN KEY (id_validator) REFERENCES TValidator(id),
    FOREIGN KEY (id_applicant) REFERENCES TApplicant(id)
);




