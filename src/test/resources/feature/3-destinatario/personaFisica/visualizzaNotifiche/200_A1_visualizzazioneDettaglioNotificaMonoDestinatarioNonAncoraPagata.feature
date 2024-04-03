Feature:La persona fisica visualizza le notifiche in elenco

  @TestSuite
  @TA_PFVisualizzazioneNotificaNonAncoraPagata
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9436 - Visualizzazione dettaglio di una notifica mono destinatario non ancora pagata, con successivo pagamento dell’avviso.
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Creo in background una notifica non pagata con un destinatario e un documento tramite API REST
    And Si visualizzano le notifiche dalla piu recente
    And Cliccare sulla notifica restituita
    And Si controlla se la sezione pagamento visualizzata correttamente
    And Si controlla che costi di notifica inclusi non presente
    Then Cliccare sul bottone Paga
    And Si verifica che visualizzato visualizzato lo stato Pagato
    And Logout da portale persona fisica
