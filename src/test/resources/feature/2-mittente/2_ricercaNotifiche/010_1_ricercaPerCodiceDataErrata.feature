Feature: Mittente effetua una ricerca notifiche per Data errata

  @TestSuite
    @TA_MittenteRicercaPerDataErrata
    @mittente
    @ricercaNotificheMittente

  Scenario Outline: PN-9323 - Mittente loggato effettua una ricerca per periodo temporale errato
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche inserire una data da <inizioArcoTemporale> a <fineArcoTemporale>
    And Si verifica che i campi della ricerca delle date siano errate
    And Si verifica che il bottone Filtra sia disabilitato
    And Logout da portale mittente

    Examples:
      | inizioArcoTemporale | fineArcoTemporale |
      | 01/03/2024          | 01/02/2024        |