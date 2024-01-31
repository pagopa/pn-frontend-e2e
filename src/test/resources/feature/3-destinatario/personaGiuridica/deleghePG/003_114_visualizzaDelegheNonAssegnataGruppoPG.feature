Feature: La persona giuridica visualizza le deleghe

  # DISABLED Temporary disabled until the bug PN-9166 is fixed
#  @TestSuite
#  @TA_PGVisualizzaDelegheSenzaGruppo
#  @DeleghePG
#  @PG
  Scenario: PN-9166 - La persona giuridica visualizza le deleghe
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Si vede correttamente l'elenco delle deleghe
    And Logout da portale persona giuridica