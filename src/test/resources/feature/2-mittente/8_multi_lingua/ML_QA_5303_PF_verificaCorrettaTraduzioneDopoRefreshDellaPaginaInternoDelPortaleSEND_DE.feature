Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - DE

  @TA_multiLinguaTedesca_QA5303
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3
  Scenario: PN-QA5303-ML - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - DE
    Given Login Page persona fisica test viene visualizzata
    #When Login con persona fisica scelta lingua
    And Si clicca bottone accetta cookies
    And Cambia lingua footer "Tedesco"
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      #| lingua       | Tedesco |
    And Attesa 2 secondi
    Then Refresh pagina
    And Attesa 2 secondi
    And Verifica traduzione testo "Zustellungen"
    And Verifica traduzione testo "Deine Adressen"
    And Verifica traduzione testo "Plattformstatus"
    And Chiudi pagina