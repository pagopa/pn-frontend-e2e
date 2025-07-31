Feature: PG -  PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- SL

  @TestSuite
  @TA_multiLinguaSloveno_QA5293
  @multiLingua
  @multiLinguaPg
  @NRT_Blocco_3
  Scenario: PN-QA5293-ML - PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- SL

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Sloveno"
    And Si clicca su prodotto
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    And Refresh pagina
#    And Aspetta 3 secondi
    And Attesa 3 secondi
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
    And Chiudi pagina