Feature: Il persona giuridica visualizza il codice di una delega

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica

  @TestSuite
  @fase2Test116
  Scenario: Il persona giuridica visualizza il codice di una delega
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella pagina Deleghe sezione Deleghe dell impresa  si verifica sia presente una delega
    And Nella pagina Deleghe sezione Deleghe dell impresa si clicca sul menu della delega
    And Nella pagina Deleghe sezione Deleghe dell impresa si sceglie l'opzione mostra codice
    Then Si clicca sul bottone chiudi
    And Logout da portale persona giuridica