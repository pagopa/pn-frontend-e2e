Feature: persona fisica scarica attestazioni all'interno di una notifica

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then pagina Piattaforma  Notifiche persona fisica viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche si recupera un codice IUN valido
    And Cliccare sul bottone Filtra persona fisica

  @TestSuite
  @test19
  @TA_PFDownloadAttestazioni
  @PF

  Scenario: persona fisica scarica attestazione
    WhenLa persona fisica clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    Then Si selezionano i file attestazioni opponibili da scaricare, all'interno della notifica persona fisica, e si controlla che il download sia avvenuto "datiNotifica"
    And Si clicca sul opzione Vedi Dettaglio
    And Logout da portale persona fisica