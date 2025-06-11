Feature: PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- EN

  @TestSuite
  @TA_multiLinguaInglese_QA5278
  @multiLingua
  # @NRT
  Scenario: PN-QA5278-ML - PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- EN

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Inglese"
  #    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto
    And Aspetta 2 secondi
    And Refresh pagina
    And Aspetta 2 secondi
  ##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Notifications"
    And Aspetta 1 secondi
    And Seleziona voce menu laterale "Company notifications"
    And Verifica traduzione testo "Notifications of"
    And Verifica traduzione testo "You can filter them by IUN Code and send date"
    #  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Delegated notifications"
    And Verifica traduzione testo "Read the notifications delegated"
  ##  Raggingere gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Delegations of authority"
    And Verifica traduzione testo "Here you can manage the company"
    And Verifica traduzione testo "Authorities held by the company"
##    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "Platform status"
    And Verifica traduzione testo "view service disruption history and download the attestations"
    And Verifica traduzione testo "Disruption history"