Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - SL

  @TestSuite
  @TA_multiLinguaSloveno_QA5295
  @multiLingua
  @multiLinguaPf
   @NRT_Blocco_3
  Scenario: PN-QA5295-ML - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - SL

    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Sloveno |
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    Then Refresh pagina
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    And Verifica traduzione testo "Vaši kontaktni podatki"
    And Verifica traduzione testo "Pooblastila"
    And Verifica traduzione testo "Stanje platforme"