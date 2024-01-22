Feature: Mittente effetua una ricerca notifiche per codice IUN

  @TestSuite
  @TA_MittenteRicercaPerCodiceIUN
  @mittente
  @ricercaNatoficheMittente

  Scenario: PN-9218 - Mittente loggato effettua una ricerca per codice IUN
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche si recupera un codice IUN valido
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice IUN della notifica
    And Logout da portale mittente