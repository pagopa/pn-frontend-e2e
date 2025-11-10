Feature: La persona giuridica aggiunge una nuova delega

  @TA_PN_13213_PF
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
      | test | PUGW-RUDZ-VQZR-202508-V-1 |
      | uat  | KZTL-KLPK-DRZU-202508-N-1 |
    #And Si controlla se la sezione pagamento visualizzata correttamente
    And Si clicca sui radio button del pagamento
    And Cliccare sul bottone Paga

  #@TA_PG_PN_13213
  #@NRT_Blocco_3
  #Scenario: [PN_13213_PG] ValidationBug [FE - a11y] Label per le radio button e checkbox
  #  Given PG - Si effettua la login tramite token exchange come 'delegante', e viene visualizzata la dashboard
  #  And Nella pagina Piattaforma Notifiche persona giuridica si accede alla notifica con codice IUN specifico
  #    | dev  | KZJA-GWZK-RVMK-202508-H-1 |
  #    | test | KZTL-KLPK-DRZU-202508-N-1 |
  #    | uat  | KZTL-KLPK-DRZU-202508-N-1 |
  #  And Si clicca sui radio button del pagamento
  #  And Cliccare sul bottone Paga