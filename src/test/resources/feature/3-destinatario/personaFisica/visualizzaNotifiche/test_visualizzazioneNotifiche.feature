Feature: La persona fisica visualizza la sezione notifiche

  @TestSuite
  @TA_PFvisualizzaNotifiche
  @PFvisualizzaNotifiche
  @PF

  Scenario:PN-9184 - La persona fisica visualizza la sezione notifiche
    When Nella Pagina Notifiche persona fisica si clicca su notifiche dell impresa
    And Nella Pagina Notifiche persona giuridica si clicca su notifica
    And Nella sezione Dettaglio Notifiche si visualizza opzione indietro, sezione dei dati, sezione pagamento
    And Nella sezione Dettaglio Notifiche si visualizza banner Recapiti, documenti allegati, altri documenti, stato dello notifiche, attestazioni
    And Nella sezione Dettaglio Notifiche si clicca su link di documento allegato per salvare in locale
    And Nella sezione Dettaglio Notifiche si clicca su link di attestazione per salvare in locale
    And Nella sezione Dettaglio Notifiche si clicca su Vedi piu dettaglio nella parte dello stato della notifica
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate "personaFisica"
    And  Logout da portale persona giuridica
