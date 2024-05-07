Feature: Ricerca notifica per periodo temporale persona fisica

  @TestSuite
  @TA_PFVisualizzaNotificaApiV1
  @PF
  @PFRicercaNotifica

  Scenario: PN-9441 - Visualizzazione dettaglio notifica inserita con API v.1
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN "JEGU-QMTG-VAVR-202405-K-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "JEGU-QMTG-VAVR-202405-K-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Logout da portale persona fisica


