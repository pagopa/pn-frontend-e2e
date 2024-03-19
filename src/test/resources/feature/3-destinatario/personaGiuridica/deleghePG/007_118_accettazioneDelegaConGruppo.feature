Feature:Il delegato persona giuridica accetta la delega assegnandoli un gruppo

  @TestSuite
  @TA_PGaccettaDelegaConGruppo
  @DeleghePG
  @PG

  Scenario: PN-9170-A116 - Il delegato persona giuridica accetta la delega assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome | delegante    |
      | fiscalCode  | 27957814470  |
      | companyName | Convivio Spa |
      | displayName | Convivio Spa |
      | person      | false        |
    And Si sceglie opzione accetta
    And Si accetta la delega "con" gruppo
    And Si controlla che la delega PG ha lo stato Attiva "Convivio Spa"
    And Logout da portale persona giuridica