Feature: Il delegato persona giuridica modifica una delega assegnandoli un gruppo

  # DISABLED Temporary disabled until the bug PN-9173 is fixed
#  @TestSuite
#  @TA_PGmodificaDelegaConGruppo
#  @DeleghePG
#  @PG

  Scenario: PN-9173 - Il delegato persona giuridica modifica una delega assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella sezione Deleghe si verifica sia presente una delega accettata per PG "personaGiuridica"
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone modifica
    And Si clicca sul bottone assegna a un gruppo
    And Si selezione il gruppo della delega
    And Si clicca su conferma in assegnazione gruppo
    And Si controlla che la delega a cambiato gruppo
    And Logout da portale persona giuridica