Feature: Visualizzazione dettaglio notifica mittente

  @TA_PAVisualizzaNotificaNonEsistente
  @GestioneErrori
  @NRT_Blocco_2
  @NRT_Blocco_2_visualizzazioneNotifiche
  Scenario: [PN-14926-PN_DELIVERY_NOTIFICATIONNOTFOUND_PA] - Errore per notifiche non accessibili all’utente
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche pubblica amministrazione si accede alla notifica con codice IUN "XVRJ-HRGW-AQZN-202905-N-1"
    And Attesa 1 secondi
    And Verifica Pop-up toast di errore "errore"
    And Verifica Messaggio toast di errore "informazioni errore"
    And Verifica Codice toast di errore "PN_DELIVERY_NOTIFICATIONNOTFOUND"
    And Copia TraceID toast di errore
    And Si chiude toast di errore
    And Refresh pagina
    And Attesa 1 secondi
    And Verifica Codice toast di errore "PN_DELIVERY_NOTIFICATIONNOTFOUND"
