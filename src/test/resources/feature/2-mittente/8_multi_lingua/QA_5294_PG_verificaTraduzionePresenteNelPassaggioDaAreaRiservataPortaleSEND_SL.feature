Feature: PG - Verifica traduzione presente nel passaggio da Area Riservata a portale SEND - SL

  @TestSuite
  @TA_multiLinguaSloveno_QA5294
  @multiLingua

  Scenario: PN-QA5294 - PG - Verifica traduzione presente nel passaggio da Area Riservata a portale SEND - SL

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Sloveno"
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Attendi secondi "3"
    And Seleziona voce menu laterale "Obvestila"
    And Verifica traduzione testo "Prenosi pooblastil"
    And Verifica traduzione testo "Kontaktni podatki"
    And Verifica traduzione testo "Stanje platforme"
##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Obvestila"
    And Seleziona voce menu laterale "Obvestila podjetja"
    And Verifica traduzione testo "Preberite obvestila za Convivio Spa"
    When Seleziona voce menu laterale "Delegirana obvestila"
    And Verifica traduzione testo "Preberite obvestila, delegirana na Convivio Spa"
##  Aggiungere e gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Prenosi pooblastil"
    And Verifica traduzione testo "Tukaj lahko upravljate pooblaščence podjetja in prenose pooblastil na podjetje"
    And Verifica traduzione testo "Prenos pooblastil na podjetje"
    #    Selezionare Stato della Piattaforma
    Then Seleziona voce menu laterale "Stanje platforme"
    And Verifica traduzione testo "Preverite delovanje SEND, oglejte si zgodovino motenj in prenesite povezana potrdila"
    And Verifica traduzione testo "Zgodovina motenj"




