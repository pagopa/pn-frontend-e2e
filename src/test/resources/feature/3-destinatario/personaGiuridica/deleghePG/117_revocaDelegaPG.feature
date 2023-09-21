Feature: La persona giuridica revoca una delega

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente


  Scenario: La persona giuridica revoca una delega
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella sezione Deleghe si clicca sul menu della delega
    And Nella sezione Deleghe si sceglie l'opzione revoca
    Then Si clicca sul bottone revoca
    And Logout da portale persona giuridica