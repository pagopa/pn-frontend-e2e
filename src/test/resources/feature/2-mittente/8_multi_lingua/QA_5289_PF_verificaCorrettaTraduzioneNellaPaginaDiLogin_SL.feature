Feature: PF - Verifica corretta traduzione nella pagina di Login - SL

  @TestSuite
  @TA_multiLinguaSloveno_QA5289
  @bilinguismo

  Scenario: PN-QA5270 - PF - Verifica corretta traduzione nella pagina di Login - SL

    Given Login Page persona fisica test viene visualizzata
    Then Cambia lingua footer "Sloveno"
    And Verifica traduzione testo "Kako se želite prijaviti?"
    And Verifica traduzione testo "Izberite želeni način"
    And Verifica traduzione testo "Vstopite s SPID"
    And Verifica traduzione testo "Vstopite s CIE"