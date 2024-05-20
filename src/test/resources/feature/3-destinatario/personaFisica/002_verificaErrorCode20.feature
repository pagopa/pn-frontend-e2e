Feature: La persona fisica visualizza errore

  @TestSuite
  @TA_VerificaMessaggioErroreErrorCode20
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-10541 - La persona fisica visualizza la sezione notifiche
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Collegarsi a link con codice 20
    Then Si visualizza correttamente il messaggio di errore 20
