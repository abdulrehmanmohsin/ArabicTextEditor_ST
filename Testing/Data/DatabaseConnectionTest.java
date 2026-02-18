package Data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dal.DatabaseConnection;

public class DatabaseConnectionTest {

    @Test
    void testSingletonInstance() {

        // should always return same instance
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        assertSame(db1, db2);
    }
}