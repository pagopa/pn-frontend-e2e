Feature: PG - Cambio Lingua da SEND a portale Area Riservata - FR

  @TestSuite
  @TA_multiLinguaFrancese_QA5271
  @multiLingua
  # @NRT
  Scenario: PN-QA5271-ML - PG - Cambio Lingua da SEND a portale Area Riservata - FR

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
 #    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto
#   Cambio lingua
    And Cambia lingua footer "Francese"
    Then Seleziona servizio Notifiche Digitale
    And Click La tua Impresa "Votre entreprise"
    And Verifica traduzione testo "Consulter le résumé des données et lire les notifications de Convivio Spa"
    And Verifica traduzione testo "Notifications numériques"
    And Verifica traduzione testo "Utilisateurs"






