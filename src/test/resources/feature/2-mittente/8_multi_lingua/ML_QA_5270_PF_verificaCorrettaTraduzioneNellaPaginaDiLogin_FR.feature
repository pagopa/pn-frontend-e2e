Feature: PF - Verifica corretta traduzione nella pagina di Login - FR

  @TestSuite
  @TA_multiLinguaFrancese_QA5270
  @multiLingua
  @NRT
  Scenario: PN-QA5270 - PF - Verifica corretta traduzione nella pagina di Login - FR

    Given Login Page persona fisica test viene visualizzata
    Then Cambia lingua footer "Francese"
    And Verifica traduzione testo "Comment voulez-vous y accéder"
    And Verifica traduzione testo "Sélectionnez le mode que vous préférez"
    And Verifica traduzione testo "Connectez-vous avec SPID"
    And Verifica traduzione testo "Entrez avec CIE"