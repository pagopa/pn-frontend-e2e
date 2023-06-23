Feature: il mittente effettua il download attestazione opponibile a terzi avvenuto successo

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina piattaforma Notifiche selezionare lo stato notifica "Avvenuta ricezione"
    And Cliccare sul bottone Filtra

  @TestSuite
  @test99

  Scenario: il mittente effettua il download attestazione opponibile a terzi avvenuto successo
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: avvenuto successo", da scaricare
    Then Si controlla il testo all interno del file "Attestazione opponibile a terzi: avvenuto successo"
    And Logout da portale mittente