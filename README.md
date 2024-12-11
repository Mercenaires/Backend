# README

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Build](https://github.com/Mercenaires/Backend/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/Mercenaires/Backend/actions/workflows/gradle.yml)



````markdown
# Projet MBTI Gaming - Backend et Frontend

Ce guide vous expliquera comment lancer le backend (API) et le frontend (interface utilisateur) de ce projet.

## Prérequis avant de commencer

Avant de lancer ce projet, assurez-vous d’avoir installé certains logiciels indispensables :

- **Java** : Le langage de programmation utilisé pour le backend. Téléchargez et installez [Java JDK 17 ou plus](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- **Node.js** : Nécessaire pour le frontend. Téléchargez et installez [Node.js](https://nodejs.org) (version 14 ou plus).
- **npm** : Installé automatiquement avec Node.js, il permet de gérer les dépendances pour le frontend.
- **Git** : Utilisé pour cloner le code source du projet. Téléchargez et installez [Git](https://git-scm.com/).

## 1. Lancer le Backend

Le backend est la partie de l'application qui gère la logique, les données, et fournit des fonctionnalités à l'interface utilisateur via une API.

### 1.1 Cloner le dépôt Backend

1. **Ouvrez un terminal (ou invite de commandes)**.
2. Tapez la commande suivante pour cloner le code source du backend. Cette opération copie le code depuis le dépôt vers votre ordinateur.

   ```bash
   git clone https://github.com/Mercenaires/Backend.git
   ```
````

1. **Déplacez-vous dans le dossier du backend** en tapant la commande ci-dessous. Cela signifie que vous dites à votre terminal de travailler dans le dossier que vous venez de cloner.

   ```bash
   cd Backend

   ```

### 1.2 Installer les dépendances du Backend

1. Le backend utilise un gestionnaire de dépendances appelé **Gradle**. Gradle s’assure que tous les outils nécessaires pour exécuter le code sont présents.
2. **Tapez la commande suivante** pour demander à Gradle de télécharger toutes les dépendances :

   ```bash
   bash
   Copier le code
   ./gradlew build

   ```

   **Remarque :** Si vous êtes sur Windows et que cette commande ne fonctionne pas, essayez avec `gradlew.bat build`.

### 1.3 Lancer le Backend

Une fois que toutes les dépendances sont en place, vous pouvez démarrer le backend :

1. Tapez la commande suivante :

   ```bash
   ./gradlew bootRun

   ```

   - Cette commande demande à Gradle de lancer le backend en démarrant un serveur local.
   - Le serveur se met par défaut sur le port `8080`, ce qui signifie que vous pouvez y accéder via `http://localhost:8080` dans un navigateur.

2. **Vérifier que le backend fonctionne** : Une fois le serveur lancé, ouvrez votre navigateur et allez sur `http://localhost:8080`. Vous devriez voir une page ou un message indiquant que le backend fonctionne.

### 1.4 Lancer les tests du Backend

Les tests permettent de vérifier que le backend fonctionne comme prévu.

1. Pour exécuter les tests, tapez cette commande :

   ```bash
   ./gradlew test

   ```

2. **Vérifiez les résultats des tests** : Si les tests passent, vous verrez un message de réussite. Si des tests échouent, le terminal affichera des informations sur les erreurs.

---

## 2. Lancer le Frontend

Le frontend est l’interface utilisateur. C’est ce que les utilisateurs verront et avec quoi ils interagiront.

### 2.1 Cloner le dépôt Frontend

1. Ouvrez un nouveau terminal, ou allez dans une nouvelle fenêtre de terminal.
2. Tapez la commande suivante pour cloner le code source du frontend :

   ```bash
   bash
   Copier le code
   git clone https://github.com/Mercenaires/Frontend.git

   ```

3. Déplacez-vous dans le dossier du frontend :

   ```bash
   bash
   Copier le code
   cd Frontend

   ```

### 2.2 Installer les dépendances du Frontend

1. **Installer les dépendances** : Le frontend utilise **npm** (le gestionnaire de paquets de Node.js) pour télécharger les bibliothèques nécessaires.
2. Tapez cette commande :

   ```bash
   bash
   Copier le code
   npm install

   ```

   - Cela va télécharger toutes les bibliothèques définies pour le projet dans un dossier `node_modules`.

### 2.3 Lancer le Frontend

1. Une fois les dépendances installées, tapez la commande suivante pour lancer le frontend :

   ```bash
   bash
   Copier le code
   npm start

   ```

   - Cette commande démarre le serveur de développement de React, qui permet de voir votre interface utilisateur.
   - Le frontend se lancera par défaut sur le port `3000`, ce qui signifie que vous pouvez y accéder en allant à `http://localhost:3000` dans un navigateur.

2. **Vérifier que le frontend fonctionne** : Une fois que le serveur est lancé, ouvrez votre navigateur et allez sur `http://localhost:3000`. Vous devriez voir l’interface utilisateur du projet.

---

## 3. Lancer le projet complet (Backend + Frontend)

1. **Démarrer d'abord le backend** en suivant les étapes de la section 1.
2. Ensuite, **démarrer le frontend** en suivant les étapes de la section 2.
3. Une fois les deux serveurs en cours d'exécution :
   - Allez sur `http://localhost:3000` pour voir l'interface utilisateur.
   - Assurez-vous que le backend fonctionne toujours sur `http://localhost:8080`, car le frontend envoie des requêtes API à cette adresse.

---

## Informations supplémentaires et dépannage

### Variables d'environnement

Certains services, comme **SonarCloud** pour l’analyse de code, peuvent nécessiter des **variables d'environnement**. Configurez-les en créant un fichier `.env` à la racine du projet si besoin.

### Problèmes fréquents

- **Port occupé** : Si un port (par exemple `8080` ou `3000`) est déjà utilisé, changez le port dans la configuration, ou terminez le processus qui utilise ce port.
- **Erreurs de dépendances** : Si des erreurs surviennent lors de l’installation des dépendances, vérifiez que vous avez les bonnes versions de Java et Node.js installées.
