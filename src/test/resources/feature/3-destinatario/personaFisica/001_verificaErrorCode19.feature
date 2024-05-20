Feature: La persona fisica visualizza la sezione notifiche

  @TestSuite
  @TA_VerificaMessaggioErroreErrorCode19
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-10537 - La persona fisica visualizza la sezione notifiche
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Collegarsi a link con codice 19
    Then Si visualizza correttamente il messaggio di errore 19
