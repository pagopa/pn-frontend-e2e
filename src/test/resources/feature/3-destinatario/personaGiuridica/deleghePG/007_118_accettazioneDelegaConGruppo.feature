Feature:Il delegato persona giuridica accetta la delega assegnandoli un gruppo

  @TestSuite
  @TA_PGaccettaDelegaConGruppo
  @DeleghePG
  @PG

  Scenario Outline: PN-9170 - Il delegato persona giuridica accetta la delega assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa si controlla la presenza di una delega per PG
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si sceglie opzione accetta
    And Si inserisce il codice della delega a carico dell impresa nella modale
    And Nella sezione Deleghe si clicca sul bottone conferma codice
    And Si assegna un gruppo alla delega
    And Si clicca sul bottone conferma gruppo
    And Si controlla che la delega PG ha lo stato Attiva <ragioneSociale>
    And Si ripristina lo stato iniziale delle deleghe a carico dell impresa "personaGiuridica"
    And Logout da portale persona giuridica
    Examples:
      | ragioneSociale |
      | Convivio Spa   |