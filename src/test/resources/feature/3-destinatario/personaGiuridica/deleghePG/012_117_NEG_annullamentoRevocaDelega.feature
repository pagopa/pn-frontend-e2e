Feature: La persona giuridica annulla l'operazione di revoca una delega

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login "personaGiuridica_1" portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica_1"


  @TestSuite
  @TA_PGAnnulaRevocaDelega
  @DeleghePG
  @PG
  Scenario: La persona giuridica annulla l'operazione di revoca una delega
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega "nuovaDelegaPG"
    And Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca
    Then Si clicca sul bottone annulla
    And Logout da portale persona giuridica