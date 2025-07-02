package com.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {



	private static Connection connection;
    private static Object customerDAO; // Using Object to store CustomerDAO instance

    @BeforeAll
    public static void setUp() throws Exception {
        // Initialize the database connection and customerDAO instance
        Class<?> jdbcUtilsClass = Class.forName("com.example.util.DBConnectionUtil");
        connection = (Connection) jdbcUtilsClass.getMethod("getConnection").invoke(null);
        
        Class<?> daoClass = Class.forName("com.example.service.CustomerServiceImpl");
        customerDAO = daoClass.getDeclaredConstructor().newInstance();
    }

    // @AfterAll
    // public static void tearDown() throws SQLException {
    //     clearDatabase();
    //     System.out.println("Database cleared after all tests.");
    // }

	// private static void clearDatabase() throws SQLException {
    //     try (Statement stmt = connection.createStatement()) {
    //         stmt.executeUpdate("DELETE FROM customer");
    //     }
    // }


    // Method to get row count of customers from the database
    private int getCustomerRowCount() throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM customer");
                ResultSet rs = pstmt.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

   


    @Test
	@Order(1)
	public void testEntityFolder() {
		String directoryPath = "src/main/java/com/example/entity";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}

    @Test
	@Order(2)
	public void testExceptionFolder() {
		String directoryPath = "src/main/java/com/example/exception";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}


    
    @Test
	@Order(3)
	public void testServiceFolder() {
		String directoryPath = "src/main/java/com/example/service";
		File directory = new File(directoryPath);
		assertTrue(directory.exists() && directory.isDirectory());
	}


    @Test
	@Order(4)
    void testCustomerInsertQueryExists() throws IOException {
        // Read the service implementation file as a string
        String filePath = "src/main/java/com/example/service/CustomerServiceImpl.java";  // Update the path if needed
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        // Define the expected query
        String expectedQuery = "INSERT INTO customer (name, email, phoneNumber, password) VALUES (?, ?, ?, ?)";

        // Assert that the query exists in the file
        assertTrue(fileContent.contains(expectedQuery), "Expected SQL insert query is missing in CustomerServiceImpl");
    }


    @Test
	@Order(5)
    void testRestaurantInsertQueryExists() throws IOException {
        // Read the service implementation file as a string
        String filePath = "src/main/java/com/example/service/RestaurantServiceImpl.java";  // Update the path if needed
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        // Define the expected query
        String expectedQuery = "INSERT INTO restaurant (name, address, cuisineType, contactNumber) VALUES (?, ?, ?, ?)";

        // Assert that the query exists in the file
        assertTrue(fileContent.contains(expectedQuery), "Expected SQL insert query is missing in RestaurantServiceImpl");
    }



    @Test
	@Order(6)
    void testMenuInsertQueryExists() throws IOException {
        // Read the service implementation file as a string
        String filePath = "src/main/java/com/example/service/MenuServiceImpl.java";  // Update the path if needed
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        // Define the expected query
        String expectedQuery = "INSERT INTO menuItem (restaurantId, name, price, description, availableQuantity) VALUES (?, ?, ?, ?, ?)";

        // Assert that the query exists in the file
        assertTrue(fileContent.contains(expectedQuery), "Expected SQL insert query is missing in RestaurantServiceImpl");
    }


    @Test
	@Order(7)
    void testOrderInsertQueryExists() throws IOException {
        // Read the service implementation file as a string
        String filePath = "src/main/java/com/example/service/OrderServiceImpl.java";  // Update the path if needed
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

        // Define the expected query
        String expectedQuery = "INSERT INTO orderItem (orderId, itemId, quantity) VALUES (?, ?, ?)";

        // Assert that the query exists in the file
        assertTrue(fileContent.contains(expectedQuery), "Expected SQL insert query is missing in OrderServiceImpl");
    }



    
    @Test
	@Order(8)
    public void testCustomerFileExists() {
		String filePath = "src/main/java/com/example/entity/Customer.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(9)
    public void testMenuFileExists() {
		String filePath = "src/main/java/com/example/entity/MenuItem.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}


    @Test
	@Order(10)
    public void testOrderFileExists() {
		String filePath = "src/main/java/com/example/entity/Order.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}


    
    @Test
	@Order(11)
    public void testOrderItemFileExists() {
		String filePath = "src/main/java/com/example/entity/OrderItem.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(12)
    public void testPaymentFileExists() {
		String filePath = "src/main/java/com/example/entity/Payment.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(13)
    public void testRestaurantFileExists() {
		String filePath = "src/main/java/com/example/entity/Restaurant.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(14)
    public void testEmaliAlreadyRegisteredExceptionFile() {
		String filePath = "src/main/java/com/example/exception/EmailAlreadyRegisteredException.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(15)
    public void testRestaurantNotFoundExceptionFile() {
		String filePath = "src/main/java/com/example/exception/RestaurantNotFoundException.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
    @Test
	@Order(16)
	public void testCustomerServiceFile() {
		String filePath = "src/main/java/com/example/service/CustomerService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}


    @Test
	@Order(17)
	public void testCustomerServiceImplFile() {
		String filePath = "src/main/java/com/example/service/CustomerServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}



	@Test
	@Order(18)
	public void testRestaurantServiceFile() {
		String filePath = "src/main/java/com/example/service/RestaurantService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(19)
    public void testRestaurantServiceImplFile() {
		String filePath = "src/main/java/com/example/service/RestaurantServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(20)
    public void testMenuServiceFile() {
		String filePath = "src/main/java/com/example/service/MenuService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(21) 
	public void testMenuServiceImplFile() {
		String filePath = "src/main/java/com/example/service/MenuServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}

    @Test
	@Order(22)
    public void testOrderServiceFile() {
		String filePath = "src/main/java/com/example/service/OrderService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}


    @Test
	@Order(23)
    public void testOrderServiceImplFile() {
		String filePath = "src/main/java/com/example/service/OrderServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}


    @Test
	@Order(24)
    public void testPaymentServiceFile() {
		String filePath = "src/main/java/com/example/service/PaymentService.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}
    
    @Test
	@Order(25)
    public void testPaymentServiceImplFile() {
		String filePath = "src/main/java/com/example/service/PaymentServiceImpl.java";
		File file = new File(filePath);
		assertTrue(file.exists() && file.isFile());
	}


	@Test
	@Order(26)
	public void testCustomerRegistration() throws Exception {
	// ✅ Step 1: Remove existing test email if present
		deleteCustomerByEmail("john.doe@example.com");

	// ✅ Step 2: Count rows before insertion
	int rowCountBefore = getCustomerRowCount();
	System.out.println("Row count before: " + rowCountBefore);

	// ✅ Step 3: Create Customer instance via reflection
	Class<?> customerClass = Class.forName("com.example.entity.Customer");
	Constructor<?> constructor = customerClass.getConstructor(int.class, String.class, String.class, String.class, String.class);
	Object customer = constructor.newInstance(1, "John Doe", "john.doe@example.com", "12345", "67585");

	// ✅ Step 4: Invoke createCustomer() method
	Method createCustomerMethod = customerDAO.getClass().getMethod("createCustomer", customerClass);
	createCustomerMethod.invoke(customerDAO, customer);

	// ✅ Step 5: Count rows after insertion
	int rowCountAfter = getCustomerRowCount();
	System.out.println("Row count after: " + rowCountAfter);

	// ✅ Step 6: Assert that row count increased by 1
	assertEquals(rowCountBefore + 1, rowCountAfter, "One customer should be added.");
}

// Utility method to delete a customer by email for test cleanup
private void deleteCustomerByEmail(String email) throws SQLException {
	String sql = "DELETE FROM customer WHERE email = ?";
	try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
		pstmt.setString(1, email);
		pstmt.executeUpdate();
	}
}



}
