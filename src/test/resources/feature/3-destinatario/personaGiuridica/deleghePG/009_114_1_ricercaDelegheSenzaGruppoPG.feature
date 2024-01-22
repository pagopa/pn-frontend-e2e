Feature:La persona giuridica visualizza le deleghe

  @TestSuite
  @TA_PGricercaDelegheSenzaGruppo
  @DeleghePG
  @PG
    
  Scenario: PN-9166 - La persona giuridica fa una ricerca delle deleghe
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega accettata per PG "personaGiuridica"
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il codice fiscale del delegante "personaGiuridica"
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito "personaGiuridica"
    And Logout da portale persona giuridica