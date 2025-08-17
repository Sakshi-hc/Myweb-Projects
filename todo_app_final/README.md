# Todo App (Spring Boot + MySQL)

## Features
- MySQL backend (configure credentials in `src/main/resources/application.properties`)
- Create/view/update/delete tasks
- Priority (High / Normal / Low)
- Due date & time (datetime-local)
- 12-hour AM/PM display format
- Browser notification 5 minutes before due time (visual only)
- Auto-delete expired tasks when the app is opened/refreshed
- LIFO/FIFO toggle (remembered in browser)
- Command-line runnable jar

## Setup

1. Install Java 17+ and Maven.
2. Create MySQL database `todo_app` or any name and update `spring.datasource.url` in `src/main/resources/application.properties`.
3. Update username/password in `application.properties`.
4. (Optional) Run `db_schema.sql` to create the table, but the app will auto-create the table on first run.
5. Build and run:
   ```
   mvn clean package
   java -jar target/todo-app.jar
   ```
6. Open `http://localhost:8080/` in your browser. Grant notification permission when prompted.

Notes:
- Auto-delete runs when fetching tasks (on load/refresh). If you want periodic server-side cleanup, we can add a scheduler later.
