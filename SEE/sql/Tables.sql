CREATE TABLE TApplicant (
    id INT PRIMARY KEY,
    nom VARCHAR(100),
    age INT,
    dpt INT,
);

CREATE TABLE TVolunteer(
    id INT PRIMARY KEY,
    nom VARCHAR(100),
    age INT,
    dpt INT,

);

CREATE TYPE statut AS ENUM ('pending', 'approved', 'rejected','completed');

CREATE TABLE TRequest (
    id INT PRIMARY KEY,
    subj VARCHAR(100),
    id_volunteer INT, --doit lier un Volunteer
    id_applicant INT, --doit lier un Applicant
    statut statut, --doit lier un type statut
    helpday DATE,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    CONSTRAINT fk_volunteer FOREIGN KEY (id_volunteer) REFERENCES TVolunteer(id),
    CONSTRAINT fk_applicant FOREIGN KEY (id_applicant) REFERENCES TApplicant(id)
);