Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - EN

  @TA_multiLinguaInglese_QA5281
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3
  Scenario: PN-QA5281-ML - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - EN

    Given Login Page persona fisica test viene visualizzata
    #When Login con persona fisica scelta lingua
    And Si clicca bottone accetta cookies
    And Cambia lingua footer "Inglese"
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Attesa 2 secondi
    Then Refresh pagina
    And Attesa 2 secondi
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Your contact"
    And Verifica traduzione testo "Delegates"
    And Verifica traduzione testo "Platform status"
    And Chiudi pagina