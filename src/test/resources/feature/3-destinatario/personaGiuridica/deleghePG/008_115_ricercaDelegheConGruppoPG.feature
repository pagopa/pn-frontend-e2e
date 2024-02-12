Feature:La persona giuridica fa una ricerca per gruppo delle deleghe

  @TestSuite
  @TA_PGricercaDeleghePerGruppo
  @DeleghePG
  @PG


  Scenario: PN-9167 - La persona giuridica fa una ricerca per gruppo delle deleghe
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa si controlla la presenza di una delega per PG "personaGiuridica"
    And Accetta la delega a carico dell impresa ed assegna gruppo
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si inserisce il gruppo del delegante
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si clicca su bottone Filtra
    And Nella pagina Deleghe sezione Deleghe a Carico dell impresa si controlla che venga restituita la delega con il codice fiscale inserito "personaGiuridica_1"
    And Si ripristina lo stato iniziale delle deleghe a carico dell impresa "personaGiuridica"
    And Logout da portale persona giuridica