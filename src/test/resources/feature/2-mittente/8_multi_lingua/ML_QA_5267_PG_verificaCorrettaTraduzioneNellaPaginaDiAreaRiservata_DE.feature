Feature: PG - Verifica corretta traduzione nella pagina di Area Riservata - DE

  @TestSuite
  @TA_multiLinguaTedesco_QA5267
  @multiLingua
  @NRT_1
  Scenario: PN-QA5267 - PG - Verifica corretta traduzione nella pagina di Area Riservata - DE

    Given Login Page persona giuridica viene visualizzata
    And Cambia lingua footer "Tedesco"
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
      | lingua         | DE        |
#    verificare che la sezione Panoramica sia scritta in lingua Tedesca
    Then Verifica traduzione testo "Übersicht"
    And Verifica traduzione testo "Zeige die Datenübersicht an und lies die Zustellungen von"
    And Verifica traduzione testo "Digitale Zustellungen"

