Feature: La persona fisica visualizza la sezione notifiche

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite token exchange "personaFisica"
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @TA_PFvisualizzaNotifiche
  @PFvisualizzaNotifiche
  @PF

  Scenario:La persona fisica visualizza la sezione notifiche
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche
    And Si visualizza correttamente la Pagina Notifiche persona fisica
    And Nella Pagina Notifiche persona fisica si visualizzano correttamente i filtri di ricerca
    And Nella Pagina Notifiche persona fisica si visualizza correttamente l elenco delle notifiche
    And  Logout da portale persona fisica