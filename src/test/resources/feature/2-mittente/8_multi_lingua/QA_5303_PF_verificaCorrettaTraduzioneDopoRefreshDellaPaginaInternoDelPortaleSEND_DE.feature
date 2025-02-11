Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - DE

  @TestSuite
  @TA_multiLinguaTedesca_QA5303
  @multiLingua
  Scenario: PN-QA55303 - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - DE
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Tedesco |
    Then Refresh pagina
    And Verifica traduzione testo "Zustellungen"
    And Verifica traduzione testo "Deine Adressen"
    And Verifica traduzione testo "Plattformstatus"