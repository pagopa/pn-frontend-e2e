Feature:La persona giuridica revoca una delega

    @TestSuite
    @TA_PGrevocaNuovaDelega
    @DeleghePG
    @PG

  Scenario:PN-9169 - La persona giuridica revoca una delega
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Le Epistolae srl"
    And Nella sezione Deleghe sezione Deleghe dell'impresa si controlla che non sia più presente la delega "nuovaDelegaPG"
    And Logout da portale persona giuridica
