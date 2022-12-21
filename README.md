# Getting Started

Projekt auschecken und `./gradlew bootRun` ausführen zum Starten.

# Beschreibung

Die Präsentationschicht wird hier durch das Package *api* abgebildet.
Hier kommen alle Klassen rein, die auschließlich für die Web-API relevant sind.
Die Controller-Klassen sollen sich hierbei nur um das Handling der Requests u. Responses kümmern.
Das Exception-Handling passiert im *ErrorResponseExceptionHandler*, der dafür sorgt das die Fehlermeldungen als JSON
in der eigenen vorgegebenen Struktur zurückgegeben werden.

Die Domänenschicht wird hier durch das Package *domain* abgebildet.
Denke mal der Punkt ist selbsterklärend.

Die Logik-Schicht soll durch das Package *service* abgebildet werden,
der den Code für die Geschäftslogik beinhalten soll und hier als Schnittstelle zwischen der Präsentationschicht und der
Persistenzschicht fungieren soll.

Die Persistenzschicht wird durch das Package *repository* abgebildet,
worin die Klassen rein kommen sollen, die für die Datenoperationen zuständig sind.

# Mögliche Verbesserungen

- das api package könnte man auch als Modul *petstore-api* trennen und der restliche Code dann als Modul *petstore-core*
- anstatt einem Pet DTO-Objekt könnte man ein Request sowie Response Model erstellen
- besseres Fehlerhandling mit aussagekräftigeren Fehlermeldungen
- Authentifizierung und Authentisierung
- Persistente Daten mit einer Datenbank anstatt In-Memory
- Integration-Tests u. allgemein mehr Tests
- zu einer RESTful API
- Versionierung REST API