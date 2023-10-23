Feature: Il delegato visualizza il dettaglio di una notifica

  Background: Login delegato
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login "delegatoPF" portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega accettata

  @TestSuite
  @test51

  Scenario:Il delegato visualizza il dettaglio di una notifica
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Si seleziona il nome del delegante nell elenco
    And Si visualizza correttamente la Pagina Notifiche persona fisica delegante "personaFisica"
    And Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And  Logout da portale persona fisica