Feature:La persona giuridica visualizza il codice di una delega

  @TestSuite
  @TA_PGvisualizzaCodiceDelega
  @DeleghePG
  @PG

  Scenario: PN-9168 - La persona giuridica visualizza il codice di una delega
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella pagina Deleghe sezione Deleghe dell impresa  si verifica sia presente una delega
    And Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega "nuovaDelegaPG"
    And Nella pagina Deleghe sezione Deleghe dell impresa si sceglie l'opzione mostra codice
    Then Si clicca sul bottone chiudi
    And Si ripristina lo stato iniziale delle deleghe dall impresa "nuovaDelegaPG"
    And Logout da portale persona giuridica