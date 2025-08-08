# 📌 CRM Application – Angular + Spring Boot

## 📖 Description
Cette application est un **CRM (Customer Relationship Management)** permettant à chaque utilisateur de gérer ses contacts et ses commandes de manière sécurisée.  
Elle est construite en **Full Stack** avec un frontend Angular et un backend Spring Boot, communiquant via une API REST sécurisée par **JWT** stocké dans des cookies.

---

## 🛠 Technologies utilisées

### **Frontend**
- [Angular 19](https://angular.io/) – Framework front-end
- [TypeScript](https://www.typescriptlang.org/)
- Angular Signals & Reactive Forms
- Guards de route pour protéger les pages authentifiées

### **Backend**
- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Java 21](https://openjdk.org/projects/jdk/21/)
- [Spring Security](https://spring.io/projects/spring-security) + JWT
- [PostgreSQL](https://www.postgresql.org/) – Base de données relationnelle
- Architecture en couches (Controller / Service / Repository / DTO / Mapper)

---

## 🚀 Fonctionnalités principales

### Authentification & Sécurité
- Inscription et connexion via API REST
- Authentification **JWT** stocké dans un cookie HTTP-Only (sécurité contre XSS)
- Protection des routes backend et frontend
- Vérification automatique du token à chaque requête

### Gestion des contacts
- **CRUD** (Create, Read, Update, Delete) sur les contacts
- Chaque utilisateur ne peut accéder qu’à ses propres contacts
- Liaison automatique au compte connecté

### Gestion des commandes
- Création et édition des commandes
- Association à des entreprises (Company)
- Sécurisation par utilisateur

---

## 🏗 Architecture du projet

```
crm-app/
│
├── backend/ (Spring Boot)
│   ├── auth/           → Authentification & JWT
│   ├── user/           → Gestion des utilisateurs
│   ├── contacts/       → Gestion des contacts
│   ├── orders/         → Gestion des commandes
│   ├── config/         → Sécurité, CORS, filtres
│   ├── resources/      → Configuration application.yml
│
├── frontend/ (Angular)
│   ├── src/app/
│   │   ├── pages/      → Pages principales (login, dashboard, etc.)
│   │   ├── components/ → Composants réutilisables
│   │   ├── services/   → Appels API au backend
│   │   ├── guards/     → Protection des routes
│   │   ├── interfaces/ → Interfaces TypeScript (DTO)
```


## ⚙️ Installation et lancement

### **1️⃣ Prérequis**
- Node.js 20+
- Java 21+
- PostgreSQL

### **2️⃣ Cloner le projet**
```bash
git clone https://github.com/ton-pseudo/nom-du-repo.git
cd nom-du-repo

cd backend
# Modifier application.yml avec vos identifiants PostgreSQL
./mvnw spring-boot:run

cd frontend
npm install
ng serve --open

```

##📌 Notes

L’API est protégée par CORS pour autoriser uniquement http://localhost:4200 en développement.

Les DTO et Mappers assurent que seules les données nécessaires sont exposées.

Les tests unitaires sont inclus côté Backend avec JUnit & Mockito.




<div align="center">

✍️ **Fait avec ❤️ par Nicolas Floris**

</div>
