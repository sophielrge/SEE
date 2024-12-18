# SEE : Application d’aide aux personnes vulnérables

## Description

Ce projet vise à développer une **application de bénévolat** permettant de connecter les personnes dans le besoin avec des bénévoles prêts à offrir leur aide. Il offre également un système de validation pour garantir la qualité et la pertinence des services proposés.

## Objectifs

- **Faciliter l'interaction entre les personnes dans le besoin et les bénévoles.**
- **Permettre aux bénévoles de répondre aux demandes d'aide ou de proposer des services spontanés.**
- **Assurer un processus de validation par des utilisateurs autorisés (ex. : hôpitaux).**
- **Gérer les missions avec différents statuts : en attente, validée, réalisée.**
- **Collecter les avis des utilisateurs pour améliorer les services.**

## Fonctionnalités principales

- **Gestion des utilisateurs :**
  - Ajout des utilisateurs demandant de l’aide.
  - Inscription des bénévoles.
  - Création d’utilisateurs valideurs pour superviser les actions (ex. : personnel hospitalier).

- **Gestion des demandes d’aide :**
  - Ajout de demandes par les utilisateurs.
  - Validation ou rejet des demandes avec un motif en cas de refus.
  - Suivi des missions avec différents statuts : *en attente*, *validée*, *réalisée*.

- **Propositions spontanées :**
  - Les bénévoles peuvent proposer des services spontanés.

- **Évaluation des services :**
  - Les utilisateurs peuvent donner un avis après chaque mission.

## Technologies utilisées

- **Langages** : [Java, Python, etc. selon vos choix]
- **Frameworks** : [Spring Boot, Django, etc.]
- **Base de données** : [MySQL, PostgreSQL, MongoDB, etc.]
- **Interface utilisateur** : [JavaFX, React, ou autre technologie front-end]
- **Outils** : [Git, Maven, Docker, etc.]

## Structure 

SEE/
├── .github/
│   └── workflows/
├── .vscode/
├── SEE/
│   ├── src/main/java/org/pdla/assistance_app/
│       ├── accounts/
│           ├── User.java
│           ├── Applicant.java
│           ├── Validator.java
│           └── Volunteer.java
│       ├── structures/
│           ├── BDD.java
│           ├── Menu.java
│           ├── Request.java
│           └── Traitement_texte.java
│       └── Main.java
│   └── src/test/java/org/pdla/assistance_app/structure
│       ├── accounts/
│           ├── User.java
├── .gitignore
├── BD_SEE.mwb
├── README.md
└── Rapport.md

## Explication test
Pour tester BDD, on utilise Mockito pour simuler l'utilisation de la base. Comme ça, on ne touche pas réellement le Base de Données et donc on ne la rend pas éronnée.

On peut creer:
demandeur et ajouter requete
Valideur et voir les requêtes 
bénévole et voir les requêtes 
il faut corriger sélectionner requête 
donner l'id
On peut se connecter:
ajouter case compte
