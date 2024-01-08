Feature: Il delegato visualizza la notifiche del delegante

  Background: Login delegato
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite token exchange "delegatoPF"
    Then Home page persona fisica viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega accettata

  @TestSuite
  @TA_PFvisualizzaNotificheDelegante
  @DeleghePF
  @PF

  Scenario: PN-9419 - Il delegato visualizza la notifiche del delegante
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Si visualizza l elenco delle notifiche relative al delegante
    And Si seleziona il nome del delegante nell elenco
    Then Si visualizzano tutte le notifiche del delegante selezionato
    And  Logout da portale persona fisica