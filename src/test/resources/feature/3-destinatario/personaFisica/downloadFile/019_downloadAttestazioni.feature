Feature: persona fisica scarica attestazioni all'interno di una notifica

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then pagina Piattaforma  Notifiche persona fisica viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN da dati notifica "datiNotifica"
    And Cliccare sul bottone Filtra persona fisica

  @TestSuite
  @test19
  @

  Scenario: persona fisica scarica attestazione
    When Il persona fisica clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    Then Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona fisica, e si controlla che il download sia avvenuto
    And Si clicca sul opzione Vedi Dettaglio
    And Logout da portale persona fisica