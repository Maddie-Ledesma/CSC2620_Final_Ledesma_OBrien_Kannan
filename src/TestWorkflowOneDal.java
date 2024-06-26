import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestWorkflowOneDal {
    // Mock the Connection and PreparedStatement
    Connection connection = mock(Connection.class);
    CallableStatement callableStatement = mock(CallableStatement.class);

    // Mock the ResultSet returned by executeQuery
    ResultSet resultSet = mock(ResultSet.class);

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
        when(callableStatement.executeQuery()).thenReturn(resultSet);
        var dal = new WorkflowOneDal(connection);

        when(resultSet.next())
                .thenReturn(true)
                .thenReturn(false);

        when(resultSet.getString("Name")).thenReturn("God of war");
        when(resultSet.getDate("ReleaseDate")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getInt("Quantity")).thenReturn(2);
        when(resultSet.getDouble("Price")).thenReturn(60.00);
        when(resultSet.getString("GenreName")).thenReturn("Action");

        System.setOut(printStream);

        dal.showStock();

        String capturedOutput = outputStream.toString();
        assertTrue(capturedOutput.contains("God of war"));
        assertTrue(capturedOutput.contains("60.00"));
    }

    @Test
    public void testNotResults() throws SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        when(callableStatement.executeQuery()).thenReturn(resultSet);
        var dal = new WorkflowOneDal(connection);

        when(resultSet.next())
                .thenReturn(false);

        System.setOut(printStream);

        dal.showStock();

        String capturedOutput = outputStream.toString();
        assertFalse(capturedOutput.contains("God of war"));
    }

    @Test
    public void testException() throws SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        var message = "store procedure was not found";
        when(connection.prepareCall(anyString())).thenReturn(callableStatement);
        when(callableStatement.executeQuery()).thenThrow(new SQLException(message));
        var dal = new WorkflowOneDal(connection);
        System.setOut(printStream);
        dal.showStock();
        String capturedOutput = outputStream.toString();
        assertTrue(capturedOutput.contains("Failed to show games in stock"));

    }

}
