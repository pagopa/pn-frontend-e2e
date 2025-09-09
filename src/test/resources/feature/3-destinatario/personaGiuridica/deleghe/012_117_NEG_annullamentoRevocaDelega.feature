Feature: La persona giuridica annulla l'operazione di revoca una delega

  @TestSuite
  @TA_PGAnnullaRevocaDelega
  @DeleghePG
  @PG
  @DeleghePFPG
  @NRT_Blocco_1
  Scenario: PN-9169-A115 - La persona giuridica annulla l'operazione di revoca una delega
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella pagina Deleghe sezione Deleghe dell impresa  si verifica sia presente una delega
    And Attesa 1 secondi
    And Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega "Lucrezia Borgia"
    And Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca
    Then Si clicca sul bottone annulla
