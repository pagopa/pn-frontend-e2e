Feature: La persona giuridica revoca una delega

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login "personaGiuridica_1" portale persona giuridica tramite request method
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica_1"
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa

  @TestSuite
  @TA_PGrevocaNuovaDelega
  @DeleghePG
  @PG
  Scenario: La persona giuridica revoca una delega
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    When Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega "nuovaDelegaPG"
    And Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca
    Then Si clicca sul bottone revoca
    And Nella sezione Deleghe sezione Deleghe dell'impresa si controlla che non sia più presente la delega "nuovaDelegaPG"
    And Logout da portale persona giuridica