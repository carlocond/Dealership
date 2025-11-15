
# 🚗 Dealership  
Web Application per la gestione completa di un concessionario — sviluppata con **Java**, **Spring Boot**, **Spring Security (JWT)** e un frontend statico leggero.

---

## 📌 Descrizione del progetto

**Dealership** è una web app progettata per digitalizzare e semplificare le operazioni di un concessionario automobilistico.  
Gli utenti possono consultare i veicoli disponibili, filtrare per marca o prezzo, salvare i preferiti e inviare richieste.  
Gli amministratori possono gestire completamente veicoli, utenti e richieste attraverso un pannello dedicato.

Il progetto nasce con l’obiettivo di rafforzare le mie competenze backend, applicare architetture REST moderne e utilizzare Spring Security con JWT per un’autenticazione sicura e scalabile.

---

## 🧩 Funzionalità principali

### 👤 Utente
- Registrazione e login tramite JWT  
- Visualizzazione veicoli disponibili  
- Filtri per categoria, marca, chilometraggio e prezzo  
- Dettaglio veicolo  
- Salvataggio nei preferiti  
- Invio richieste informazioni o test drive  

### 🛠️ Admin
- Dashboard riepilogativa  
- Gestione parco veicoli (aggiunta, modifica, eliminazione)  
- Gestione utenti  
- Gestione richieste ricevute  
- Accesso protetto tramite role-based security (`ROLE_ADMIN`)  

---

## 🏗️ Architettura

Il progetto è strutturato seguendo un’architettura REST:

- **Entities** → Veicolo, Utente, Richiesta, Preferito  
- **Controllers** → `/api/vehicles`, `/api/auth`, `/api/users`, `/api/requests`  
- **Services** → Gestione logica applicativa  
- **Repositories** → Spring Data JPA per la persistenza  
- **Security** → JWT + filtri personalizzati + ruoli utente/admin  

---

## 🔐 Sicurezza

Implementata tramite:
- **Spring Security**
- **JWT (JSON Web Token)** per autenticazione stateless
- Ruoli applicativi: `USER` e `ADMIN`
- Filtri dedicati alla validazione del token
- Protezione endpoint con accesso differenziato

---

## 💾 Database

Supporta:
- **H2 Database** (modalità sviluppo)
- **PostgreSQL / MySQL** (produzione)

Gestito tramite:
- **Spring Data JPA**
- **Hibernate ORM**

---

## 🧪 Testing delle API

L’applicazione è completamente testabile tramite **Postman**:  
- Login → ricezione token JWT  
- Token → accesso agli endpoint protetti  

Una collezione Postman può essere inclusa nella repo.

---

## 🖥️ Frontend

Frontend statico, composto da:
- **HTML5**
- **CSS3**
- **JavaScript Vanilla**

Funzionalità:
- Dashboard utente e admin  
- Integrazione API REST  
- Gestione del token JWT tramite `localStorage`  
- UI semplice e intuitiva  

---

## 🛠️ Tecnologie utilizzate

- Java 17+  
- Spring Boot 3.x  
- Spring Security (JWT)  
- Spring Data JPA  
- Hibernate  
- H2 / PostgreSQL  
- Postman  
- Git & GitHub  
- HTML, CSS, JavaScript  
- GitHub Copilot e ChatGPT per supporto frontend e assistenza nello sviluppo  

---

## 🎯 Obiettivi del progetto

- Approfondire concetti avanzati di backend  
- Applicare sicurezza JWT in un caso reale  
- Strutturare un’architettura REST scalabile e pulita  
- Integrare un frontend semplice ma funzionale  
- Migliorare il workflow completo di un progetto software  

---

## 🤝 Contributi

Feedback, consigli, critiche costruttive e contributi sono **più che benvenuti**.  
Chiunque voglia contribuire allo sviluppo può aprire liberamente una **pull request**! 🚀  

---

## 📎 Licenza

Questo progetto è distribuito sotto licenza MIT.  
Puoi usarlo, modificarlo e migliorarlo liberamente.
