Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - FR

  @TestSuite
  @TA_multiLinguaFrancese_QA5268
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3
  Scenario: PN-QA5268-ML - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - FR

    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Francese |
    Then Refresh pagina
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    And Verifica traduzione testo "Vos notifications"
    And Verifica traduzione testo "Vos adresses"
    And Verifica traduzione testo "Procurations"
    And Chiudi pagina