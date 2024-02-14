Feature: La persona fisica visualizza la sezione notifiche

  @TestSuite
  @TA_PGVisualizzaNotifiche
  @VisualizzazioneNotifichePG
  @PG

  Scenario: PN-9147 - La persona giuridica visualizza la sezione notifiche
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa si controlla la presenza di una delega per PG "personaGiuridica"
    And Si sceglie opzione accetta
    And Si inserisce il codice della delega a carico dell impresa nella modale
    And Si clicca sul bottone conferma delega
    And Si clicca su conferma in assegnazione gruppo
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate "Convivio Spa"
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca
    Then Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si ripristina lo stato iniziale delle deleghe a carico dell impresa "personaGiuridica"
    And  Logout da portale persona giuridica