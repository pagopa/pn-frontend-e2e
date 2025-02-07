Feature: PF - Verifica corretta traduzione nella pagina di Login - DE

  @TestSuite
  @TA_multiLinguaTedesco_QA5297
  @bilinguismo
  Scenario: PN-QA5297 - PF - Verifica corretta traduzione nella pagina di Login - DE
    Given Login Page persona fisica test viene visualizzata
    Then Cambia lingua footer "Tedesco"
    And Verifica traduzione testo "Wie möchtest du dich anmelden"
    And Verifica traduzione testo "Mit SPID anmelden"
    And Verifica traduzione testo "Mit CIE anmelden"