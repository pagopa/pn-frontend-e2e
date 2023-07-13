Feature: Il delegato visualizza il dettaglio di una notifica

  Background: Login delegato
    Given Login Page destinatario "delegato" viene visualizzata
    When Login portale destinatario tramite request method
    Then Home page destinatario viene visualizzata correttamente

  @TestSuite
  @test51
  @restLogin


  Scenario:Il delegato visualizza il dettaglio di una notifica
    When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
    And Si seleziona il nome del delegante nell elenco
    And Nella pagina Piattaforma Notifiche  Destinatario inserire il codice IUN da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica destinatario
    And  Logout da portale destinatario