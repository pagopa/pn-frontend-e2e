Feature: La persona giuridica aggiunge una nuova delega

  @TA_PF_PN_13213
  @NRT_Blocco_3
  Scenario: [PN_13213_PF] ValidationBug [FE - a11y] Label per le radio button e checkbox
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Nella pagina Piattaforma Notifiche persona fisica si accede alla notifica con codice IUN specifico
      | dev  | KZTL-KLPK-DRZU-202508-N-1 |
      | test | KZTL-KLPK-DRZU-202508-N-1 |
      | uat  | KZTL-KLPK-DRZU-202508-N-1 |
    And Si clicca sui radio button del pagamento
    And Cliccare sul bottone Paga

