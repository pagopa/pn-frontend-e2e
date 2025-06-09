Feature: PG - Verifica traduzione presente nel passaggio da Area Riservata a portale SEND - EN

  @TestSuite
  @TA_multiLinguaInglese_QA5273
  @multiLingua
  @NRT
  Scenario: PN-QA5273-ML - PG - Verifica traduzione presente nel passaggio da Area Riservata a portale SEND - EN

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Inglese"
  #    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto

    When Seleziona voce menu laterale "Notifications"
    And Verifica traduzione testo "Delegations of authority"
    And Verifica traduzione testo "Contact details"
##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Notifications"
    And Seleziona voce menu laterale "Company notifications"
    And Verifica traduzione testo "Notifications of"
    And Verifica traduzione testo "You can filter them by IUN Code and send date"
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Sender"
    And Verifica traduzione testo "Recipient"
    And Verifica traduzione testo "Date sent"
    #  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Delegated notifications"
    And Verifica traduzione testo "Read the notifications delegated"
  ##  Raggingere gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Delegations of authority"
    And Verifica traduzione testo "Here you can manage the company"
    And Verifica traduzione testo "Authorities held by the company"

    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Verifica traduzione testo "Enter the details of the person or entity you wish to authorise to read your notifications"
    And Verifica traduzione testo "Natural person"
    And Verifica traduzione testo "Legal person"
    And Verifica traduzione testo "Share this code with the authorised representative"
##    Selezionare Stato della Piattaforma
    Then Seleziona voce menu laterale "Platform status"
    And Verifica traduzione testo "view service disruption history and download the attestations"
    And Verifica traduzione testo "Disruption history"




