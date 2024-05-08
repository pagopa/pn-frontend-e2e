Feature: La persona fisica visualizza errore

  @TestSuite
  @TA_VerificaMessaggioErroreErrorCode25
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-10540 - La persona fisica visualizza la sezione notifiche
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Collegarsi a link "https://cittadini.test.notifichedigitali.it/auth/login/error?errorCode=25"
    Then Si visualizza correttamente il messaggio di errore 25
