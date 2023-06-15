Feature: Destinatario scarica attestazioni all'interno di una notifica

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche  Destinatario inserire il codice IUN da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra Destinatario

  @TestSuite
  @test19
  @quintaConsegna

  Scenario: Destinatario scarica attestazione
    When Il destinatario clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica destinatario
    Then Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica destinatario, e si controlla che il download sia avvenuto
    And Si clicca sul opzione Vedi Dettaglio
    And Logout da portale destinatario