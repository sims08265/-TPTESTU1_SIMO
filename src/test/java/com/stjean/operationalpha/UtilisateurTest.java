package com.stjean.operationalpha;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilisateurTest {

    @BeforeEach
    void setup() {
        Utilisateur.users.clear();
    }

    @Test
    void testAjouterUtilisateurValide() throws EmailInvalidException {
        Utilisateur u = new Utilisateur(1, "Alice", 25, "alice@gmail.com", "699123456", "Yaoundé", 50000);
        Utilisateur.ajouter(u);
        assertEquals(1, Utilisateur.users.size());
        assertEquals("Alice", Utilisateur.users.get(0).getNom());
    }

    @Test
    void testAjouterUtilisateurEmailInvalide() {
        Utilisateur u = new Utilisateur(2, "Bob", 30, "bob#email.com", "678123456", "Douala", 70000);
        assertThrows(EmailInvalidException.class, () -> Utilisateur.ajouter(u));
        assertEquals(0, Utilisateur.users.size());
    }

    @Test
    void testSupprimerUtilisateurExistant() throws EmailInvalidException, SuppressionInvalidException {
        Utilisateur u = new Utilisateur(1, "Alice", 25, "alice@gmail.com", "699123456", "Yaoundé", 50000);
        Utilisateur.ajouter(u);

        Utilisateur.supprimer(1);
        assertEquals(0, Utilisateur.users.size());
    }

    @Test
    void testSupprimerUtilisateurInexistant() {
        assertThrows(SuppressionInvalidException.class, () -> Utilisateur.supprimer(99));
    }

    
    @Test
    void testListerEtAfficher() throws EmailInvalidException {
        Utilisateur u1 = new Utilisateur(1, "Alice", 25, "alice@gmail.com", "699123456", "Yaoundé", 50000);
        Utilisateur u2 = new Utilisateur(2, "Bob", 30, "bob@gmail.com", "678123456", "Douala", 70000);

        Utilisateur.ajouter(u1);
        Utilisateur.ajouter(u2);

        assertDoesNotThrow(Utilisateur::lister);
        assertDoesNotThrow(() -> Utilisateur.afficher(1));
        assertDoesNotThrow(() -> Utilisateur.afficher(2));
        assertDoesNotThrow(() -> Utilisateur.afficher(99));
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> fournirEmails() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("test@gmail.com", true),
                org.junit.jupiter.params.provider.Arguments.of("user@yahoo.com", true),
                org.junit.jupiter.params.provider.Arguments.of("invalid-email", false),
                org.junit.jupiter.params.provider.Arguments.of("bob#email.com", false)
        );
    }

    @ParameterizedTest
    @MethodSource("fournirEmails")
    void testValidationEmailParametres(String email, boolean valide) {
        boolean resultat = email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$");
        assertEquals(valide, resultat);
    }
    
    @Test
    void testSoldeGeneralPositif() throws Exception {
        Utilisateur.ajouter(new Utilisateur(1, "Alice", 25, "alice@gmail.com", "699123456", "Yaoundé", 5000));
        Utilisateur.ajouter(new Utilisateur(2, "Bob", 30, "bob@gmail.com", "678123456", "Douala", 7000));
        Utilisateur.ajouter(new Utilisateur(3, "Charlie", 28, "charlie@gmail.com", "677123456", "Bafoussam", -2000));

        double soldeGeneral = Utilisateur.analyseSoldeGeneral();
        assertEquals(10000, soldeGeneral, 0.001);
    }
    
    @Test
    void testSoldeGeneralNegatif() throws Exception {
        Utilisateur.ajouter(new Utilisateur(1, "Alice", 25, "alice@gmail.com", "699123456", "Yaoundé", -5000));
        Utilisateur.ajouter(new Utilisateur(2, "Bob", 30, "bob@gmail.com", "678123456", "Douala", -3000));

        assertThrows(NegativeGeneralBalanceException.class, Utilisateur::analyseSoldeGeneral);
    }
    
    @Test
    void testSoldeGeneralVide() throws Exception {
        double solde = Utilisateur.analyseSoldeGeneral();
        assertEquals(0, solde, 0.001);
    }
    
    @Test
    void testUtilisateurPlusRiche() throws Exception {
        Utilisateur.ajouter(new Utilisateur(1, "Alice", 25, "alice@gmail.com", "699123456", "Yaoundé", 5000));
        Utilisateur.ajouter(new Utilisateur(2, "Bob", 30, "bob@gmail.com", "678123456", "Douala", 7000));
        Utilisateur.ajouter(new Utilisateur(3, "Charlie", 28, "charlie@gmail.com", "677123456", "Bafoussam", 6000));

        Utilisateur riche = Utilisateur.utilisateurPlusRiche();
        assertNotNull(riche);
        assertEquals("Bob", riche.getNom());
        assertEquals(7000, riche.getSoldePersonnel(), 0.001);
    }

    @Test
    void testUtilisateurPlusRicheListeVide() {
        Utilisateur riche = Utilisateur.utilisateurPlusRiche();
        assertNull(riche);
    }

}
