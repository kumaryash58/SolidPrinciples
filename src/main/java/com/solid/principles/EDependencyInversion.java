package com.solid.principles;

public class EDependencyInversion {

    public static void main(String[] args) {
        // Violating DIP
        System.out.println("Violating DIP:");
        UserService userService = new UserService();
        userService.performAction();

        // Following DIP
        System.out.println("\nFollowing DIP:");
        DatabaseDIP mysqlDb = new MySQLDatabaseDIP();
        UserServiceDIP serviceMySQL = new UserServiceDIP(mysqlDb);
        serviceMySQL.performAction();

        DatabaseDIP postgresDb = new PostgreSQLDatabaseDIP();
        UserServiceDIP servicePostgres = new UserServiceDIP(postgresDb);
        servicePostgres.performAction();
    }
}

//=============Violating DIP
//Here, the UserService directly depends on a concrete MySQLDatabase class:
class MySQLDatabase {
	public void connect() {
		System.out.println("Connecting to MySQL...");
	}
}

class UserService {
	private MySQLDatabase database;

	public UserService() {
		this.database = new MySQLDatabase(); // tightly coupled
	}

	public void performAction() {
		database.connect();
		System.out.println("Performing user action...");
	}
}

//Problem:
//You can't easily switch to another database (e.g., PostgreSQL).
//UserService is tightly coupled to MySQLDatabase.

//====================Following DIP
//We introduce an abstraction (Database) and depend on it instead of concrete classes.
interface DatabaseDIP {
	void connect();
}

class MySQLDatabaseDIP implements DatabaseDIP {
	@Override
	public void connect() {
		System.out.println("Connecting to MySQL...");
	}
}

class PostgreSQLDatabaseDIP implements DatabaseDIP {
	@Override
	public void connect() {
		System.out.println("Connecting to PostgreSQL...");
	}
}

class UserServiceDIP {
	private DatabaseDIP database;

	public UserServiceDIP(DatabaseDIP database) {
		this.database = database;
	}

	public void performAction() {
		database.connect();
		System.out.println("Performing user action...");
	}
}

class MainDIP {
	public static void main(String[] args) {
		DatabaseDIP db = new MySQLDatabaseDIP(); // or new PostgreSQLDatabase();
		UserServiceDIP service = new UserServiceDIP(db);
		service.performAction();
	}
}

//Benefits:
//UserService depends on the abstraction, not the concrete implementation.
//You can easily switch databases without changing business logic.
//This makes the system flexible, testable, and maintainable.
