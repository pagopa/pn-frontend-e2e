Feature: Il delegato persona giuridica modifica una delega assegnandoli un gruppo

  @TestSuite
  @TA_PGmodificaDelegaConGruppo
  @DeleghePG
  @PG

  Scenario: PN-9173 - Il delegato persona giuridica modifica una delega assegnandoli un gruppo
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
    And Si clicca sul bottone conferma gruppo
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone modifica
    And Si clicca sul bottone assegna a un gruppo
    And Si selezione il gruppo della delega
    And Si clicca su conferma in assegnazione gruppo
    And Si controlla che la delega ha cambiato gruppo
    And Si ripristina lo stato iniziale delle deleghe a carico dell impresa "personaGiuridica"
    And Logout da portale persona giuridica