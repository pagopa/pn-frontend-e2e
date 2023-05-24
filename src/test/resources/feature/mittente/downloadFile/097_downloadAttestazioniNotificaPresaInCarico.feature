Feature: il mittente download attestazione notifica presa in carico

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra

  @test97

  Scenario: il mittente scarica il file Attestazione opponibile a terzi: notifica presa in carico
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: notifica presa in carico", da scaricare
    Then Si controlla il testo all interno del file "Attestazione opponibile a terzi: notifica presa in carico"
    And Logout da portale mittente
