Feature: il mittente effettua il download attestazione opponibile a terzi avvenuto successo

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice IUN da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra

  @test99

  Scenario:
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si selezionano il file, "Attestazione opponibile a terzi: avvenuto successo", da scaricare
    Then Si controlla il testo all interno del file "Attestazione opponibile a terzi: avvenuto successo"
    And Logout da portale mittente