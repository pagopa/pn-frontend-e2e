Feature:La persona giuridica fa una ricerca per gruppo delle deleghe

  @TestSuite
  @TA_PGricercaDeleghePerGruppo
  @DeleghePG
  @PG


  Scenario: PN-9167 - La persona giuridica fa una ricerca per gruppo delle deleghe
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome | delegante        |
      | fiscalCode  | LELPTR04A01C352E |
      | companyName | Le Epistolae srl |
      | displayName | Le Epistolae srl |
      | person      | false            |
    And Si controlla la tabella delegati dall impresa
    And Nella sezione Delegati dall impresa si visualizza correttamente una delega in stato di attesa di conferma "Le Epistolae srl"
    And Si revoca delega come delegante con api
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome | delegante    |
      | fiscalCode  | 27957814470  |
      | companyName | Convivio Spa |
      | displayName | Convivio Spa |
      | person      | false        |
    And Si controlla la tabella deleghe a carico dell impresa
    And Si accetta la delega "con" gruppo
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il gruppo del delegante
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che ci sia una delega con la ragione sociale inserita "Convivio Spa"
    And Logout da portale persona giuridica