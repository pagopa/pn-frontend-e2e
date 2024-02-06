Feature:La persona fisica visualizza la sezione aggiungi una nuova delega

  @TestSuite
  @TA_PFvisualizzaElencoCampiAggiugiDelega
  @DeleghePF
  @PF

  Scenario:PN-9399 - La persona fisica visualizza la sezione aggiungi una nuova delega
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella sezione Deleghe si visualizza il titolo
    And Nella sezione Deleghe si visualizza il sottotitolo
    And Nella sezione Deleghe si visualizza il bottone aggiungi una delega
    Then Nella sezione Deleghe si visualizzano tutti i campi dell'elenco dei delegati
    And Si controlla che non sia presente una delega con stesso nome "nuova_delega"
    And Logout da portale persona fisica

