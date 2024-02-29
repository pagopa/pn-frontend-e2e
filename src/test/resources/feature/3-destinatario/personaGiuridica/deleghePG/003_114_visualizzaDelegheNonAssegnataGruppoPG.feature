Feature: La persona giuridica visualizza le deleghe

  @TestSuite
  @TA_PGVisualizzaDelegheSenzaGruppo
  @DeleghePG
  @PG

  Scenario: PN-9166-A112 - La persona giuridica visualizza le deleghe
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Logout da portale persona giuridica