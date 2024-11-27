# Rapport


## 1. Introduction

### Contexte du projet :
Présentation succincte de l’application : une plateforme pour aider les personnes vulnérables grâce à des bénévoles.
Objectifs principaux (ex. simplifier la mise en relation entre demandeurs d'aide et bénévoles, assurer la validation des missions).

Rôles des membres :
Description des responsabilités des membres de l’équipe.

Technologies utilisées :
Langage de programmation (ex. Java), outils de gestion (Jira, GitHub), outils DevOps (Maven, GitHub Actions).

## 2. Application de la méthode Agile
Framework choisi :
Justification du choix (Scrum, Kanban ou autre). Par exemple, Scrum est pertinent pour gérer des sprints et prioriser les tâches.

Gestion des user stories :
Création des user stories avec Jira pour chaque fonctionnalité :
Exemple : "En tant que bénévole, je veux pouvoir m'inscrire pour répondre à des demandes d'aide."
Priorisation des tâches avec un backlog.

Planification des sprints :
Organisation en sprints (par exemple, chaque sprint dure 2 semaines).
Objectifs des sprints (fonctionnalités livrées, tests, etc.).

Réunions et suivi :
Récapitulatif des réunions effectuées (si applicable) : daily scrum, planification, rétrospective.

## 3. Organisation de Git
### Structure du dépôt :
Branches principales :
main pour les versions stables.
develop pour les fonctionnalités en cours.
Branches secondaires :
Une branche par fonctionnalité (ex. feature/add-user).

Workflow Git :
Explication du processus de développement :
Création de branches pour chaque fonctionnalité.
Commits réguliers respectant une convention (ex. feat: ajout d’un utilisateur).
Pull requests pour fusionner des branches.
Gestion des conflits et validation des merges.
Exemple de commandes utilisées :
git pull, git branch, git merge, git rebase, etc.

Collaborations via GitHub :
Répartition des tâches et attribution des issues.

## 4. Mise en place de l'intégration continue (DevOps)

Outil choisi :
Utilisation de GitHub Actions pour automatiser le pipeline CI/CD.
Configuration du pipeline :
Déroulé des étapes :
Build Maven : Compilation du code source.
Tests automatisés : Exécution des tests JUnit.
Analyse de qualité du code : Outils comme SonarQube ou Checkstyle (si applicable).
Génération de l'artefact : Création d’un fichier .jar.
Gestion des erreurs dans le pipeline.
Notifications :
Configuration d’alertes (par mail ou dans GitHub) pour signaler les erreurs ou succès.
Difficultés et solutions :
Problèmes rencontrés lors de la configuration ou des tests.

## 5. Conclusion
Résumé des réalisations :
Points forts de la méthode agile, de l'organisation Git, et de l'intégration continue.

Améliorations possibles :
Ce qui pourrait être fait différemment ou optimisé (par ex. tests plus poussés, meilleure priorisation).

Apprentissages :
Ce que l'équipe a appris en termes techniques et organisationnels.


## Annexes
Captures d’écran :
Jira (backlog, burndown chart, user stories).
GitHub (branches, pull requests, historique des commits).
Pipeline CI/CD (workflow GitHub Actions, logs).
Code et configurations :
Exemple de fichier YAML pour CI/CD.
Fichier pom.xml pour Maven.