Feature: La persona giuridica visualizza la sezione notifiche

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "personaGiuridica"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TestSuite
  @TA_PGVisualizzaNotifiche
  @VisualizzazioneNotifichePG
  @PG

  Scenario: PN-test - La persona giuridica visualizza la sezione notifiche
    When Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella Pagina Notifiche persona giuridica si clicca su notifica
    And Nella sezione Dettaglio Notifiche si visualizza opzione indietro, sezione dei dati, sezione pagamento
    And Nella sezione Dettaglio Notifiche si visualizza banner Recapiti, documenti allegati, altri documenti, stato dello notifiche, attestazioni
    And Nella sezione Dettaglio Notifiche si clicca su link di documento allegato per salvare in locale
    And Nella sezione Dettaglio Notifiche si clicca su link di attestazione per salvare in locale
    And Nella sezione Dettaglio Notifiche si clicca su Vedi piu dettaglio nella parte dello stato della notifica
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"
    And  Logout da portale persona giuridica
