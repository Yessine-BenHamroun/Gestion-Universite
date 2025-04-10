# ServiceDou - Gestion des Salles et Examens Universitaires

ServiceDou est un microservice Spring Boot pour la gestion des salles et des examens dans un environnement universitaire.

## Fonctionnalités principales

- 🏫 Gestion des salles (CRUD)
- 📝 Gestion des examens (CRUD)
- 🗓 Planification automatique des examens dans les salles disponibles
- 🔍 Vérification des jours fériés via API externe
- 📊 Consultation du statut des salles
- 🖇 Affectation/désaffectation des examens aux salles
- 📱 Génération de QR Code pour l'accès rapide aux informations

## Technologies utilisées

- **Backend**: 
  - Java 17
  - Spring Boot 3.4.2
  - Spring Data JPA
  - Spring Cloud Netflix Eureka (Service Discovery)
  
- **Base de données**: 
  - MySQL
  
- **API Externe**: 
  - Calendarific API (pour les jours fériés)
  
- **Autres**: 
  - ZXing (génération de QR Code)
  - OpenAPI (documentation d'API)


## API Endpoints

### Gestion des Salles
- `GET /salles/salles` - Liste toutes les salles
- `GET /salles/salles/status` - Statut des salles avec leurs examens
- `GET /salles/salle/{id}` - Récupère une salle par ID
- `POST /salles/ajouter` - Ajoute une nouvelle salle
- `PUT /salles/updates/{id}` - Met à jour une salle
- `DELETE /salles/deletes/{id}` - Supprime une salle

### Gestion des Examens
- `GET /salles/exams` - Liste tous les examens
- `GET /salles/exaMm/{id}` - Récupère un examen par ID
- `POST /salles/exam` - Ajoute un nouvel examen
- `PUT /salles/examen/{id}` - Met à jour un examen
- `DELETE /salles/delete/{id}` - Supprime un examen
- `POST /salles/examens/auto` - Planifie automatiquement un examen dans une salle disponible

### Affectation des Examens
- `PUT /salles/examens/{examenId}/salle/{salleId}` - Affecte un examen à une salle
- `PUT /salles/examens/{examenId}/desaffecter` - Désaffecte un examen de sa salle

### Fonctionnalités supplémentaires
- `GET /salles/qrcode` - Génère un QR code pour accéder au statut des salles
- `GET /salles/jours-feries` - Liste des jours fériés (via Calendarific API)

## Configuration requise

- Java 17
- MySQL 8+
- Maven 3.6+

Accédez à la documentation interactive :
🔗  http://localhost:8082/swagger-ui/index.html


