Feature: PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - SL

  @TestSuite
  @TA_multiLinguaSloveno_QA5288
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3
  Scenario: PN-QA5288-ML - PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - SL
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Sloveno |
    And Aspetta 2 secondi
    And Refresh pagina
    And Aspetta 2 secondi
    When Seleziona voce menu laterale "Obvestila"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Vaši kontaktni podatki"
    And Verifica traduzione testo "Pooblastila"
    And Verifica traduzione testo "Stanje platforme"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Datum"
    And Verifica traduzione testo "Pošiljatelj"
    And Verifica traduzione testo "Zadeva"
    And Verifica traduzione testo "Država"
#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Vaši kontaktni podatki"
    And Verifica traduzione testo "Kontaktni podatki"
    And Verifica traduzione testo "Tukaj lahko upravljate kontaktne podatke, na katere boste prejemali obvestila"
    And Verifica traduzione testo "Uradna dostava"
    And Verifica traduzione testo "E-pošta"
  #  Raggiungere la sezione deleghe e verificarne la traduzione

    When Seleziona voce menu laterale "Pooblastila"
    And Verifica traduzione testo "Tukaj lahko upravljate svoje pooblaščence in prenose pooblastil na vas"
    And Verifica traduzione testo "Pooblastila"
    And Verifica traduzione testo "Prenosi pooblastil na vas"
    And Verifica traduzione testo "Dodajte prenos pooblastila"

  #  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    Then Seleziona voce menu laterale "Stanje platforme"
    And Verifica traduzione testo "Preverite delovanje SEND, oglejte si zgodovino motenj in prenesite povezana potrdila, ki so izvršljiva proti tretjim osebam. Vsako potrdilo potrjuje motnjo"