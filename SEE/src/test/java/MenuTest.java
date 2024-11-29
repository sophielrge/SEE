import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.yourcompany.yourproject.Applicant;
import org.yourcompany.yourproject.BDD;
import org.yourcompany.yourproject.Menu;

public class MenuTest {
    private BDD mockBDD;
    private Scanner mockScanner;
    private Menu menu, menuF;

    @BeforeEach
    void setUp() throws SQLException, ParseException {
        // Crée un mock pour BDD
        mockBDD = mock(BDD.class);

        // Crée un Scanner avec un ByteArrayInputStream pour simuler l'entrée
        String simulatedInput = "1\n"; // Simule l'entrée "1" pour le test
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Crée l'objet Menu et injecte le mockBDD
        menu = new Menu();
        menu.setScanner(scanner);  // Injecte le Scanner simulé dans Menu
    }

    @Test
    void testMenuDepart_ReturnsCorrectOption() {
        // Appelle la méthode menu_depart et vérifie le résultat
        int option = menu.menu_depart();

        assertEquals(1, option, "menu_depart() devrait retourner le choix correct");
    }

    @Test
    void testChoixCompte_ReturnsCorrectOption() {

        int option = menu.choix_compte();
        assertEquals(1, option, "choix_compte() devrait retourner le choix correct");
    }

}
