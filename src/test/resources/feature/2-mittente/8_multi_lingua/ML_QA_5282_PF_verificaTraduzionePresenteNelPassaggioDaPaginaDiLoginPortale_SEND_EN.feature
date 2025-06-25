Feature: PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - EN

  @TestSuite
  @TA_multiLinguaInglese_QA5282
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3_GRUPPO_AWS
  Scenario: PN-QA5282-ML - PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - EN
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Inglese |
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Your addresses"
    And Verifica traduzione testo "Delegates"
    And Verifica traduzione testo "Platform status"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Your notifications"
    And Verifica traduzione testo "Sender"
    And Verifica traduzione testo "Subject"
    And Verifica traduzione testo "Status"
  
#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Your addresses"
    And Verifica traduzione testo "Addresses"
    And Verifica traduzione testo "Here you can manage addresses and receive the notifications sent to you by institutions registered with SEND"
    And Verifica traduzione testo "Legal address"
    And Verifica traduzione testo "Email"

#  Raggiungere la sezione deleghe e verificarne la traduzione

    When Seleziona voce menu laterale "Delegates"
    And Verifica traduzione testo "Here you can manage your delegates and your proxies. The first are the natural or legal"
    And Verifica traduzione testo "Your delegates"
    And Verifica traduzione testo "Your proxies"
    And Verifica traduzione testo "Add a delegate"

  #  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    Then Seleziona voce menu laterale "Platform status"
    And Verifica traduzione testo "Check the operation of SEND, view the history of disruptions and download the related certificates enforceable against third parties"