USE projet_gei_007;

CREATE TABLE TApplicant (
    id INT PRIMARY KEY,
    nom VARCHAR(100),
    age INT,
    dpt INT
);

CREATE TABLE TVolunteer(
    id INT PRIMARY KEY,
    nom VARCHAR(100),
    age INT,
    dpt INT

);

CREATE TABLE TRequest (
    id INT PRIMARY KEY,
    subj VARCHAR(100),
    id_volunteer INT,
    id_applicant INT,
    statut VARCHAR(1), 
    helpday DATE,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_volunteer) REFERENCES TVolunteer(id),
    FOREIGN KEY (id_applicant) REFERENCES TApplicant(id)
);