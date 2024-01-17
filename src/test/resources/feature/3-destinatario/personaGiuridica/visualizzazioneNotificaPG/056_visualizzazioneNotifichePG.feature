Feature: La persona fisica visualizza la sezione notifiche

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "delegatoPG"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "delegatoPG"

  @TestSuite
  @TA_PGVisualizzaNotifiche
  @VisualizzazioneNotifichePG
  @PG

  Scenario: PN-9147 - La persona giuridica visualizza la sezione notifiche
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega accettata per PG "personaGiuridica"
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate "delegatoPG"
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca
    Then Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And  Logout da portale persona giuridica