# Rapport


## 1. Introduction

### Contexte du projet :

De nombreuses personnes se trouvent isolées, que ce soit à cause d’un éloignement familial ou de problèmes de santé invalidants. Ces situations créent des besoins spécifiques, souvent impossibles à satisfaire sans aide extérieure. Par exemple, il peut s’agir de récupérer un colis pour une personne âgée ou d’assurer le lavage de vêtements durant une hospitalisation, des tâches qui requièrent un soutien extérieur fiable.

Ce projet vise à concevoir une application destinée au bénévolat. Elle permettra aux personnes en difficulté de faire leurs demandes, et que ces dernières soietn alors pris en charge par un bénévole.

Pour organiser ce projet, nous avons utilisé Jira afin de définir et prioriser les user stories, suivre l’avancement des tâches et gérer les sprints de manière agile. Côté développement, nous avons choisi Java comme langage principal. Nous avons également utilisé GitHub pour le contrôle de version, facilitant la travail au sein de l’équipe.

## 2. Application de la méthode Agile
### Methode Scrum :
Avant de se lancer dans le développement, nous avons cherché à définir les acteurs concernés par l’application, ainsi que les fonctionnalités les plus importantes en se basant sur le cahier des charges. Il s’agit ici de trois types de personnes, celles en situation de vulnérabilité, les bénévoles, et des personnes responsables, dont l’approbation est indispensable pour permettre à un volontaire de répondre à un service. Pour s’organiser au mieux, nous avons planifié notre travail en suivant la méthode Scrum, à l’aide d’un Jira. 

### Gestion des user stories :
Lors de nos premières réunions, nous avons réalisé le backlog de notre projet. Pour cela, nous avons exprimé toutes les fonctionnalités nécessaires sous la forme d’user stories. Par exemple “En tant que bénévole, je veux pouvoir créer un compte pour répondre à des demandes d’aide”. Nous avons ensuite ordonné ces tâches à l’aide de niveau de priorité afin de respecter les demandes du client.

### Planification et suivi des sprints :
Nous avons ensuite organisé notre avancement en sprints. Chaque semaine dans l’idéal, nous avons choisis deux ou trois user stories réalisable par les membres de notre équipe dans le temps imparti. Les réunions nous ont permise de discuter ensemble des sous-tâches nécessaires à concevoir, programmer et tester afin d’implémenter chaque nouvelle fonctionnalité. La semaine suivante, nous terminions le sprint en récapitulant ce qui fonctionnait ou non, puis planifions le suivant.

### Répartition des tâches
Nous nous sommes réparti les tâches de cette manière :
-  **Ensemble** : création des tables
-  **Sophie** : gestion de la base de données, des tests de celle-ci, et des requêtes
-  **Elise** : gestion du menu et du traitement de texte, des tests de celui-ci, et des types de
comptes.

## 3. Organisation de Git
### Structure du dépôt :
Afin de collaborer facilement et de pouvoir travailler à distance, nous avons utilisé un dépôt Git. Chaque membre a ainsi pu travailler de son côté en synchronisant le développement à l’aide des commandes git tout en respectant certaines conditions. Lors de la création du projet, nous avons commencé par ajouter un fichier .gitignore comportant les fichiers à ne pas ajouter sur le dépôt lors des commit (par exemple les fichiers .class) afin d’éviter des conflits inutils. Chaque nouveau fichier a dû être ajouté au dépôt avec git add. Tout au long du développement, il nous a été nécessaire de bien répartir le travail et de communiquer pour être sûres que l’on ne modifiait pas les mêmes classes au même moment. Nous avons régulièrement réalisé des commit, avec des noms clairs pour y revenir facilement en cas de besoin, et des tags lors de moments clefs. 

### Ameliorations possibles
Malgré nos précautions, nous avons parfois eu des conflits que nous avons heureusement pu résoudre dans le merge editor. Afin d’éviter ce genre de problème la prochaine fois, il faut en fait travailler sur différentes branches, principale et secondaires. La branche main contient les versions stables, et on créé une branche secondaire pour chaque fonctionnalité en cours d’implémentation. On s’assure qu’une branche est stable avant de la merge avec la branche principale. Cette méthode de travail permet d’éviter tout conflit.


## 4. Mise en place de l'intégration continue (DevOps)

Pour ce projet, nous avons choisi GitHub Actions pour automatiser le pipeline CI/CD. Cet outil nous a permis de simplifier le processus de développement en automatisant les étapes essentielles comme la compilation, les tests, et le déploiement.

## Déroulement du pipeline

Le pipeline se compose de plusieurs étapes :

- Compilation avec Maven : Nous avons utilisé Maven pour compiler le code source et garantir qu'il est conforme aux exigences.

- Exécution des tests unitaires : Les tests JUnit ont été automatisés pour s'assurer que chaque fonctionnalité fonctionne comme prévu. En cas d'échec, le pipeline s'interrompt automatiquement.

- Génération d'un fichier .jar : Une fois le code validé, un artefact exécutable est produit pour faciliter le déploiement.

## Difficultés rencontrées

Nous avons rencontré plusieurs problèmes lors de la mise en place de ce pipeline. Par exemple, le déploiement était difficile à configurer en raison de permissions incorrectes et de problèmes avec les secrets. Nous avons dû reconfigurer nos fichiers pom.xml et maven.yml, en faisant attention à la syntaxe (ex : bien respecter les minsucules ou les majuscules).

## Gestion du .gitignore

Nous avons utilisé un fichier .gitignore pour exclure les fichiers inutiles ou sensibles du dépôt, comme les fichiers de compilation (ex : /target/) ou céer par le système (ex : .DS_Store). Cela nous a permis de garder le dépôt propre et organisé, tout en évitant les conflits inutiles.


## 5. Conclusion
Ce projet nous a permis de développer une application en Java avec la manipulation de base de données, tout en nous familiarisant avec des méthodes de travail indispensables pour le travail en équipe pour des clients. Nous avons aussi pu remarquer l’importance de l’utilisation des outils disponibles pour l’automatisation du développement tels que Maven et JUnit.  Le PDLA nous a ainsi apporté une expérience non négligeable dans la réalisation de projet autant par ses aspects techniques, qu’organisationnels.


