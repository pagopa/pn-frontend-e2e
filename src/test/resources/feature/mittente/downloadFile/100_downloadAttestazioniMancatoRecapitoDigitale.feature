Feature: il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "WGYN-HUXM-EADA-202306-X-1" con allegato
    And Cliccare sul bottone Filtra

  @test100

  Scenario:il mittente effettua il download attestazione opponibile a terzi mancato recapito digitale
    When Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche si seleziona il file, "Attestazione opponibile a terzi: mancato recapito digitale", da scaricare
    Then Si controlla il testo all interno del file "Attestazione opponibile a terzi: mancato recapito digitale"
    And Logout da portale mittente