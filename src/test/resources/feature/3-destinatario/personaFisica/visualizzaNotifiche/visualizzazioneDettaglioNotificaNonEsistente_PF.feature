Feature: Visualizzazione dettaglio notifica persona fisica

  @TestSuite
  @TA_PFVisualizzaNotificaNonEsistente
  @GestioneErrori
  Scenario: [PN-14926-PN_DELIVERY_NOTIFICATIONNOTFOUND_PF] - Errore per notifiche non accessibili all’utente
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica si accede alla notifica con codice IUN "XVRJ-HRGW-AQZN-202905-N-1"
    And Verifica Pop-up toast di errore "Errore"
    And Verifica Messaggio toast di errore "Errore non previsto"
    And Verifica Codice toast di errore "PN_DELIVERY_NOTIFICATIONNOTFOUND"
    And Copia TraceID toast di errore
    And Si chiude toast di errore
    And Refresh pagina
    And Verifica Codice toast di errore "PN_DELIVERY_NOTIFICATIONNOTFOUND"