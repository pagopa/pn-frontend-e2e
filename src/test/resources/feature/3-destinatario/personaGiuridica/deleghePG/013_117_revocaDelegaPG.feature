Feature: La persona giuridica revoca una delega

  @TestSuite
  @TA_PGrevocaNuovaDelega
  @DeleghePG
  @PG
    
  Scenario: PN-9169 - La persona giuridica revoca una delega
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica_1"
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa si controlla la presenza di una delega per PG "nuovaDelegaPG"
    When Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega "nuovaDelegaPG"
    And Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca
    Then Si clicca sul bottone revoca
    And Nella sezione Deleghe sezione Deleghe dell'impresa si controlla che non sia più presente la delega "nuovaDelegaPG"
    And Logout da portale persona giuridica