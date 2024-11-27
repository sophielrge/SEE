import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.yourcompany.yourproject.Applicant;
import org.yourcompany.yourproject.BDD;
import org.yourcompany.yourproject.Menu;

public class MenuTest {

    @Mock
    BDD mockBDD;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    private void simulateUserInput(String input) {
        // Rediriger System.in vers un ByteArrayInputStream avec les données d'entrée simulées
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    void testMenuDepart() throws SQLException, ParseException {
        // Simuler l'entrée utilisateur "1"
        simulateUserInput("1\n");

        Menu menu = new Menu(mockBDD);
        int result = menu.menu_depart();

        Assertions.assertEquals(1, result, "Le menu principal n'a pas retourné la bonne valeur.");
    }

    @Test
    void testCreaDemandeur() throws SQLException, ParseException {
        // Simuler l'entrée utilisateur pour la création d'un demandeur
        simulateUserInput("Alice\n25\n75\npassword123\n");

        when(mockBDD.insertApplicant("Alice", 25, 75, "password123")).thenReturn(1);

        Menu menu = new Menu(mockBDD);
        Applicant applicant = menu.crea_demandeur(mockBDD);

        Assertions.assertEquals("Alice", applicant.getName());
        Assertions.assertEquals(25, applicant.getAge());
        Assertions.assertEquals(75, applicant.getDpt());

        verify(mockBDD, times(1)).insertApplicant("Alice", 25, 75, "password123");
    }

    @Test
    void testAjouterRequete() throws SQLException, ParseException {
        // Simuler l'entrée utilisateur pour ajouter une requête
        simulateUserInput("Help with gardening\n15-12-2024\n");

        Applicant applicant = new Applicant("Bob", 30, 75, "password123");
        when(mockBDD.getID_Applicant(applicant)).thenReturn(1);

        Menu menu = new Menu(mockBDD);
        menu.ajouter_requete(mockBDD, applicant);

        verify(mockBDD, times(1)).insertRequest("Help with gardening", 1, any());
    }

    @Test
    void testMenuBenevole() throws SQLException, ParseException {
        // Simuler l'entrée utilisateur "2"
        simulateUserInput("2\n");

        Menu menu = new Menu(mockBDD);
        int result = menu.menu_benevole();

        Assertions.assertEquals(2, result, "Le menu des bénévoles n'a pas retourné la bonne valeur.");
    }

    @Test
    void testChargeApplicant() throws SQLException, ParseException {
        // Simuler l'entrée utilisateur pour charger un demandeur
        simulateUserInput("1\npassword123\n");

        when(mockBDD.get_psw_Applicant(1)).thenReturn("password123");
        when(mockBDD.getApplicant(1)).thenReturn(new Applicant("Charlie", 35, 69, "password123"));

        Menu menu = new Menu(mockBDD);
        Applicant applicant = menu.charge_applicant(mockBDD);

        Assertions.assertNotNull(applicant, "L'utilisateur chargé ne doit pas être null.");
        Assertions.assertEquals("Charlie", applicant.getName());

        verify(mockBDD, times(1)).get_psw_Applicant(1);
    }
}

