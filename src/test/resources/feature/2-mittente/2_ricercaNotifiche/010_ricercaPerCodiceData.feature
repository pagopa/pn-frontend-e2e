Feature: Mittente effetua una ricerca notifiche per Data

  @TestSuite
  @TA_MittenteRicercaPerData
  @mittente
  @ricercaNotificheMittente

  Scenario: PN-9220 - Mittente loggato effettua una ricerca per periodo temporale
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche inserire un arco temporale
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con la data della notifica compresa tra <da> e <a>
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente