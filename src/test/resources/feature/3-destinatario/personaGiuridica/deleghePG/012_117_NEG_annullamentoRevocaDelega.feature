Feature: La persona giuridica annulla l'operazione di revoca una delega

  @TestSuite
    @TA_PGAnnullaRevocaDelega
    @DeleghePG
    @PG

  Scenario Outline: PN-9169-A115 - La persona giuridica annulla l'operazione di revoca una delega
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si visualizza correttamente la Pagina Notifiche persona giuridica <ragioneSociale>
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega "nuova_delega"
    And Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca
    Then Si clicca sul bottone annulla
    And Logout da portale persona giuridica
    Examples:
      | ragioneSociale |
      | Convivio Spa   |