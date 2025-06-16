Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - EN

  @TestSuite
  @TA_multiLinguaInglese_QA5281
  @multiLingua
  @NRT_Blocco_3
  Scenario: PN-QA5281-ML - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - EN

    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Inglese |
    And Aspetta 2 secondi
    Then Refresh pagina
    And Aspetta 2 secondi
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Your addresses"
    And Verifica traduzione testo "Delegates"
    And Verifica traduzione testo "Platform status"