# Trello API BDD test Automation with cucumber
Automated API testing framework for trello board and card using BDD approach.
## Tech Stack

| Tool | Version |
|------|---------|
| Java | 17 |
| RestAssured | 5.3.2 |
| JSON Schema Validator | 5.3.2 |
| Cucumber Java | 7.34.3 |
| Cucumber JUnit Platform Engine | 7.34.3 |
| Cucumber PicoContainer | 7.13.0 |
| JUnit Jupiter | 5.14.2 |
| JUnit Platform Launcher | 1.14.2 |
| JUnit Platform Suite Engine | 1.14.2 |
| Dotenv Java | 3.0.0 |
| Gradle | 8.14 |



## ✅ Test Coverage

| Feature | Scenarios |
|---------|-----------|
| Create Board | Successfully create a board, board appears in list |
| Create Board Validation | Invalid body, invalid auth (empty key, empty token) |
| Get Board | Get a board, board appears in list |
| Get Board Validation | Invalid id (400, 404), invalid auth |
| Update Board | Update board name, verify after update |
| Update Board Validation | Invalid id (400, 404), invalid auth |
| Delete Board | Delete a board, verify deleted |
| Delete Board Validation | Invalid id (400, 404), invalid auth |
| Create Card | Successfully create a card, card appears in list |
| Create Card Validation | Invalid body (empty name, empty idList, invalid idList) |
| Get Card | Get a card by id |
| Get Card Validation | Invalid id (400, 404), invalid auth |
| Update Card | Update card name, verify after update |
| Update Card Validation | Invalid id (400, 404), invalid auth |
| Delete Card | Successfully delete a card |

## ⚙️ Setup & Run

1. Clone repo
2. Buat file `.env` di root project:
   - Run: `./gradlew test`
   - Report: `build/reports/tests/test/index.html`