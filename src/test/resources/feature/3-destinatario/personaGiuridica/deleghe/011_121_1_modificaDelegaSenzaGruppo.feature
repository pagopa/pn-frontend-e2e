Feature: Il delegato persona giuridica modifica una delega non assegnandoli un gruppo

  @TestSuite
  @TA_PGmodificaDelegaSenzaGruppo
  @DeleghePG
  @PG

  Scenario: PN-9173 - Il delegato persona giuridica modifica una delega non assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si accetta la delega con gruppo "Test gruppi"
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone modifica
    And Si clicca sul bottone non assegna a un gruppo
    And Si clicca su conferma in assegnazione gruppo
    And Si controlla che la delega non abbia più il gruppo
    And Logout da portale persona giuridica