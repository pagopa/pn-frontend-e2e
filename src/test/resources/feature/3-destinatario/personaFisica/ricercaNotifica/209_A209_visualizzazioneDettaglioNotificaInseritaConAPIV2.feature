Feature: Ricerca notifica per periodo temporale persona fisica

  @TA_PFVisualizzaNotificaApiV2
  @NRT_Blocco_2
  Scenario: PN-9441 - Visualizzazione dettaglio notifica inserita con API v.2
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica inserire il codice IUN "KNZV-ADYN-XTJR-202405-N-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "KNZV-ADYN-XTJR-202405-N-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica



