Feature:La persona fisica visualizza il codice  di una delega

  @TestSuite
  @TA_PFvisualizzaCodiceDelega
  @DeleghePF
  @PF

  Scenario:PN-9402 - La persona fisica visualizza il codice  di una delega
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si clicca sul menu della delega "nuova_delega"
    And Nella sezione Deleghe si sceglie l'opzione mostra codice
    And Si visualizza correttamente la modale mostra codice
    Then Si clicca sul bottone chiudi
    And Logout da portale persona fisica
