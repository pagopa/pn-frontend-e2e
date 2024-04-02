Feature:La persona giuridica visualizza le deleghe

  @TestSuite
  @TA_PGricercaDelegheSenzaGruppo
  @DeleghePG
  @PG

  Scenario: PN-9166-B112 - La persona giuridica fa una ricerca delle deleghe
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome | delegante    |
      | fiscalCode  | 27957814470  |
      | companyName | Convivio Spa |
      | displayName | Convivio Spa |
      | person      | false        |
    And Si accetta la delega "senza" gruppo
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il codice fiscale del delegante "27957814470"
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito "Convivio Spa"
    And Logout da portale persona giuridica