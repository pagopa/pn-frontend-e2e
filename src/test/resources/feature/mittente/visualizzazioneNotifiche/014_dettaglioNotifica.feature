Feature: Mittente visualizza il dettaglio di una notifica

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra

  @test14

  Scenario: Mittente visualizza dettaglio notifica
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli
    And Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso
    Then Si clicca sul bottone indietro
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Logout da portale mittente
