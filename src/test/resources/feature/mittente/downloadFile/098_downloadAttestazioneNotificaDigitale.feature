Feature: il mittente effettua il download attestazione opponibile a terzi notifica digitale

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "QPZN-GMTM-QKAT-202306-W-1" con allegato
    And Cliccare sul bottone Filtra

  @TestSuite
  @test98

  Scenario: il mittente effettua il download attestazione opponibile a terzi notifica digitale
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: notifica digitale", da scaricare
    Then Si controlla il testo all interno del file "Attestazione opponibile a terzi: notifica digitale"
    And Logout da portale mittente