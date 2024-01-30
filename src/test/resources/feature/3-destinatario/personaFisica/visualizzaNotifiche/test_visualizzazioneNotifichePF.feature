Feature: La persona fisica visualizza la sezione notifiche

  @TestSuite
  @TA_PFvisualizzaNotifiche
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9184 - La persona fisica visualizza la sezione notifiche
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Nella Pagina Notifiche persona fisica si clicca su notifiche dell impresa
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche
    And Nella sezione Dettaglio Notifiche persona fisica si visualizza opzione indietro, sezione dei dati, sezione pagamento
    And Nella sezione Dettaglio Notifiche persona fisica si visualizza banner Recapiti, documenti allegati, altri documenti, stato dello notifiche, attestazioni
    And Nella sezione Dettaglio Notifiche persona fisica si clicca su link di documento allegato per salvare in locale
    And Nella sezione Dettaglio Notifiche persona fisica si clicca su link di attestazione per salvare in locale
    And Nella sezione Dettaglio Notifiche persona fisica si clicca su Vedi piu dettaglio nella parte dello stato della notifica
    And Nella sezione Dettaglio Notifiche persona fisica si clicca su l'opzione Indietro
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaFisica"
    And  Logout da portale persona fisica
