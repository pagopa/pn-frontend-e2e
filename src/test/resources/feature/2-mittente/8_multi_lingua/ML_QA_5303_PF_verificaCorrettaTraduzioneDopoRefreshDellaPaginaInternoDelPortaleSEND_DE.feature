Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - DE

  @TestSuite
  @TA_multiLinguaTedesca_QA5303
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3_GRUPPO_AWS
  Scenario: PN-QA5303-ML - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - DE
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Tedesco |
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    Then Refresh pagina
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    And Verifica traduzione testo "Zustellungen"
    And Verifica traduzione testo "Deine Adressen"
    And Verifica traduzione testo "Plattformstatus"