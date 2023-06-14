Feature: Il delegato visualizza il dettaglio di una notifica

  Background: Login delegato
    Given Login Page delegato "delegato" viene visualizzata
    When Login con delegato "delegato"
    Then Home page delegato viene visualizzata correttamente


  @test51

  Scenario:
    When Nella home page delegato si clicca sul bottone Notifiche
    And Si visualizza correttamente la home page delegato
    And Nella pagina Piattaforma Notifiche inserire il codice IUN delegato da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente il dettaglio della notifica
    And  Logout da portale delegato