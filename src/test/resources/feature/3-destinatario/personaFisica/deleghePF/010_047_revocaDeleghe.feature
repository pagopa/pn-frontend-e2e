Feature:La persona fisica revoca una delega

  @TestSuite
  @TA_PFrevocaDelega
  @DeleghePF
  @PF

  Scenario:PN-9403 - La persona fisica revoca una delega
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si annulla azione revoca
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    Then Si controlla che non ci sia più una delega
    And Logout da portale persona fisica









































































































































































































