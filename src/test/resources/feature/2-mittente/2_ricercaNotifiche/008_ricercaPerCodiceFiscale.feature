Feature: Mittente effetua una ricerca notifiche per CF

  @TestSuite
  @TA_MittenteRicercaNotificaPerCF
  @mittente
  @ricercaNatoficheMittente

  Scenario: PN-9217 - Mittente loggato effettua una ricerca per CF
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice fiscale del destinatario "CSRGGL44L13H501E"
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente