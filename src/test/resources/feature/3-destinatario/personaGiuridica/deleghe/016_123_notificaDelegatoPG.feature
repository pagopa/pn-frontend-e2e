Feature:Il delegato persona giuridica accede ad una delega

  @TestSuite
  @TA_PGdelegatoAccedeNotifica
  @DeleghePG
  @PG

  Scenario: PN-9177 - Il delegato persona giuridica accede ad una notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Creo in background una notifica per destinatario tramite API REST
      | destinatario      | PG    |
      | documenti         | 1     |
      | multidestinatario | false |
      | avvisoPagoPa      | 1     |
      | F24               | 1     |
      | costiNotifica     | true  |
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega accettata per PG
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And Si visualizza l elenco delle notifiche relative al delegante
    And La persona giuridica clicca sulla prima notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona giuridica delegato
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And Si visualizza l elenco delle notifiche dell impresa
    And Logout da portale persona giuridica