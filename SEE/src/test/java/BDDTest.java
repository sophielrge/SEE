import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.yourcompany.yourproject.BDD;
import org.yourcompany.yourproject.Validator;

public class BDDTest {

    @InjectMocks
    private BDD bdd ; //= new BDD("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_007","projet_gei_007", "deiD1ou2");

    @Mock
    private Connection connect;
    
    @Mock
    private PreparedStatement pstmt;

    @Mock
    private ResultSet resultSet;    

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws SQLException{
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
        when(connect.prepareStatement(anyString())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(resultSet);
        bdd.setConn(connect);
    }


    @Test
    public void testGetValidator() throws SQLException {
        int validatorId = 30;

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("nom")).thenReturn("Validator Name");
        when(resultSet.getInt("age")).thenReturn(60);
        when(resultSet.getInt("dpt")).thenReturn(69);

        String sql = "SELECT id, nom, age, dpt FROM TValidator WHERE id = ?";
        when(connect.prepareStatement(sql)).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(resultSet);

        Validator val = bdd.getValidator(validatorId);

        assertNotNull(val);
        assertEquals("Validator Name", val.getName());
        assertEquals(60, val.getAge());
        assertEquals(69, val.getDpt());

        verify(pstmt).setInt(1, validatorId);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testInsertRequest() throws SQLException{
        String subj = "Test";
        int app = 1;
        Date date = new Date(System.currentTimeMillis());

        bdd.insertRequest(subj, app, date);

        verify(pstmt).setString(1, subj);
        verify(pstmt).setInt(2, app);
        verify(pstmt).setDate(3, date);
        verify(pstmt).executeUpdate();

    }

    @Test
    void testInsertVolunteer() throws SQLException {
        String nom = "Test";
        int age = 30;
        int dpt = 101;
        String psw = "psw";

        bdd.insertVolunteer(nom, age, dpt, psw);

        verify(pstmt).setString(1, nom);
        verify(pstmt).setInt(2, age);
        verify(pstmt).setInt(3, dpt);
        verify(pstmt).setString(4, psw);
        verify(pstmt).executeUpdate();
    }

    @Test
    void testDeleteVolunteerByName() throws SQLException {
        String nom = "Test";

        bdd.deleteVolunteerByName(nom);

        verify(pstmt).setString(1, nom);
        verify(pstmt).executeUpdate();
    }
    
    @Test
    void testDeleteVolunteerById() throws SQLException {
        int id = 1;

        bdd.deleteVolunteerById(id);

        verify(pstmt).executeUpdate();
    }

    @Test
    void testInsertApplicant() throws SQLException {
        String nom = "Test";
        int age = 25;
        int dpt = 102;
        String psw = "psw";

        bdd.insertApplicant(nom, age, dpt, psw);

        verify(pstmt).setString(1, nom);
        verify(pstmt).setInt(2, age);
        verify(pstmt).setInt(3, dpt);
        verify(pstmt).setString(4, psw);
        verify(pstmt).executeUpdate();
    }

    @Test
    void testInsertValidator() throws SQLException {
        String nom = "Test";
        int age = 40;
        int dpt = 103;
        String orga = "Organization X";
        String psw="psw";

        bdd.insertValidator(nom, age, dpt, orga,psw);

        verify(pstmt).setString(1, nom);
        verify(pstmt).setInt(2, age);
        verify(pstmt).setInt(3, dpt);
        verify(pstmt).setString(4, orga);
        verify(pstmt).setString(5, psw);
        verify(pstmt).executeUpdate();
    }

    @Test
    public void testPrintRequest() throws SQLException {
        
       System.setOut(new PrintStream(outputStreamCaptor));
       when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getDate("date_creation")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString("subj")).thenReturn("Test Subject");
        when(resultSet.getString("statut")).thenReturn("A");
        when(resultSet.getDate("helpday")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString("motif")).thenReturn("No reason");
        when(pstmt.executeQuery()).thenReturn(resultSet);

        bdd.printRequest();

        String output = outputStreamCaptor.toString().trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(new Date(System.currentTimeMillis()));

        String expectedOutput = "-----------------------------------------\n"
                + "Request n°1\n"
                + "Subject: Test Subject\n"
                + "Date: " + formattedDate + "\n"
                + "Status: Approved\n"
                + "-----------------------------------------";

        assertTrue(output.contains(expectedOutput));
    }


    @Test
    public void testPrintRequestPending() throws SQLException {
        System.setOut(new PrintStream(outputStreamCaptor));
        when(resultSet.next()).thenReturn(true, false); 
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getDate("date_creation")).thenReturn(Date.valueOf("2024-11-01"));
        when(resultSet.getString("subj")).thenReturn("Test Subject");
        when(resultSet.getString("statut")).thenReturn("P");
        when(resultSet.getDate("helpday")).thenReturn(Date.valueOf("2024-11-05"));
        when(resultSet.getString("motif")).thenReturn("Test Motif");
        when(pstmt.executeQuery()).thenReturn(resultSet);

        bdd.printRequestPending();

        String output = outputStreamCaptor.toString().trim();

        String expectedOutput = "-----------------------------------------\n" +
                                "Request n°1\n" +
                                "Subject: Test Subject\n" +
                                "Date: 2024-11-05\n" +
                                "Status: Pending\n" +
                                "-----------------------------------------";

        assertTrue(output.contains(expectedOutput));
    }
}
