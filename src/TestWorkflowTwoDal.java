import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TestWorkflowTwoDal 
{
    Connection connection = mock(Connection.class);
    CallableStatement callableStatement = mock(CallableStatement.class);
    PreparedStatement salesPstm = mock(PreparedStatement.class);
    PreparedStatement salesDetailsPstm = mock(PreparedStatement.class);

    // Mock the ResultSet returned by executeQuery
    ResultSet salesRS = mock(ResultSet.class);
    ResultSet salesDetailsRS = mock(ResultSet.class);

    ByteArrayOutputStream outputStream;
    PrintStream originalOut;

    @BeforeEach
    public void beforeEachTest() {
        originalOut = System.out;
    }

    @AfterEach
    public void afterEachTest() {
        System.out.flush();
        System.setOut(originalOut);
    }

    @Test
    public void testSuccess() throws SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        when(callableStatement.execute()).thenReturn(false);
        WorkflowTwoDal dal = spy(new WorkflowTwoDal(connection));

        final String gameName = "God of war";
        final int quantity = 2;
        doReturn(1).when(dal).getCustomerId();
        doReturn(gameName).when(dal).getGameName(any());
        doReturn(quantity).when(dal).getQuantity(any());

        //  Sales created
        when(callableStatement.getInt(4)).thenReturn(1);

        // Returning head information
        when(connection.prepareStatement(contains("Customer"))).thenReturn(salesPstm);
        when(salesPstm.executeQuery()).thenReturn(salesRS);
        when(salesRS.next())
                .thenReturn(true)
                .thenReturn(false);

                
        when(salesRS.getDate("SalesDate")).thenReturn(new Date(System.currentTimeMillis()));
        when(salesRS.getString("FirstName")).thenReturn("John");
        when(salesRS.getString("LastName")).thenReturn("Doe");

        // Return Sales Details
        when(connection.prepareStatement(contains("Game"))).thenReturn(salesDetailsPstm);
        when(salesDetailsPstm.executeQuery()).thenReturn(salesDetailsRS);

        when(salesDetailsRS.next())
                .thenReturn(true)
                .thenReturn(false);

        when(salesDetailsRS.getInt("Quantity")).thenReturn(quantity);
        when(salesDetailsRS.getDouble("Price")).thenReturn(60.00);
        when(salesDetailsRS.getString("Name")).thenReturn(gameName);

        System.setOut(printStream);

        dal.purchaseGame();

        String capturedOutput = outputStream.toString();
        assertTrue(capturedOutput.contains(gameName));
        assertTrue(capturedOutput.contains("60.00"));
    }
    @Test
    public void testCustomerNotFound() throws SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        when(callableStatement.execute()).thenReturn(false);
        WorkflowTwoDal dal = spy(new WorkflowTwoDal(connection));

        final String gameName = "God of war";
        final int quantity = 2;
        doReturn(1).when(dal).getCustomerId();
        doReturn(gameName).when(dal).getGameName(any());
        doReturn(quantity).when(dal).getQuantity(any());

        //  Sales created
        when(callableStatement.getInt(4)).thenReturn(-1);

        System.setOut(printStream);

        dal.purchaseGame();

        String capturedOutput = outputStream.toString();
        assertTrue(capturedOutput.contains("Failed to find or create the given customer name."));
    }

    @Test
    public void testGameNotFound() throws SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        when(callableStatement.execute()).thenReturn(false);
        WorkflowTwoDal dal = spy(new WorkflowTwoDal(connection));

        final String gameName = "God of war";
        final int quantity = 2;
        doReturn(1).when(dal).getCustomerId();
        doReturn(gameName).when(dal).getGameName(any());
        doReturn(quantity).when(dal).getQuantity(any());

        //  Sales created
        when(callableStatement.getInt(4)).thenReturn(-2);

        System.setOut(printStream);

        dal.purchaseGame();

        String capturedOutput = outputStream.toString();
        assertTrue(capturedOutput.contains("either we don't have enough or ran out of stock."));
    }

}
