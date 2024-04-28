Feature: Ricerca notifica per periodo temporale persona fisica

  @TestSuite
  @TA_PFVisualizzaNotificaApiV2
  @PF
  @PFRicercaNotifica

  Scenario: PN-9441 - Visualizzazione dettaglio notifica inserita con API v.2
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cristoforocolombo |
      | pwd          | password123       |
      | name         | Cristoforo        |
      | familyName   | Colombo           |
      | fiscalNumber | TINIT-CLMCST42R12D969Z  |
    And Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN "MXVM-JQTG-MQZW-202312-W-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "MXVM-JQTG-MQZW-202312-W-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Logout da portale persona fisica



