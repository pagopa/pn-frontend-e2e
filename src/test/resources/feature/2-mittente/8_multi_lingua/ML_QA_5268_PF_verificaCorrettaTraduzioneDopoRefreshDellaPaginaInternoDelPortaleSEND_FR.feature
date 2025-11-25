Feature: PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - FR

  @TA_multiLinguaFrancese_QA5268
  @multiLingua
  @multiLinguaPf
  @NRT_Blocco_3
  Scenario: PN-QA5268-ML - PF - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND - FR

    Given Login Page persona fisica test viene visualizzata
    And Si clicca bottone accetta cookies
    And Cambia lingua footer "Francese"
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    Then Refresh pagina
    And Attesa 2 secondi
    And Clicca tasto Accedi OneTrust PG e PF
    And Verifica traduzione testo "Vos notifications"
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Statut de la plateforme"
    And Chiudi pagina