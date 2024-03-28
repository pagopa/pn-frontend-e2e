Feature:Il delegato persona giuridica rifiuta la delega

  @TestSuite
  @TA_PGrifiutoDelega
  @DeleghePG
  @PG

  Scenario: PN-9172 - Il delegato persona giuridica rifiuta la delega
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
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone rifiuta
    And Si clicca sul bottone rifiuta delega
    And Si controlla che la delega non si più presente in elenco
    And Logout da portale persona giuridica