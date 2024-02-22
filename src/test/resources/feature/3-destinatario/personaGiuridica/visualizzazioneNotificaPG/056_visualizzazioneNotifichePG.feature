Feature: La persona fisica visualizza la sezione notifiche

  @TestSuite
  @TA_PGVisualizzaNotifiche
  @VisualizzazioneNotifichePG
  @PG

  Scenario: PN-9147 - La persona giuridica visualizza la sezione notifiche
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa si controlla la presenza di una delega per PG
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si accetta la delega "senza" gruppo
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And Si visualizza correttamente la Pagina Notifiche persona giuridica sezione notifiche delegate "Convivio Spa"
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca
    Then Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And  Logout da portale persona giuridica