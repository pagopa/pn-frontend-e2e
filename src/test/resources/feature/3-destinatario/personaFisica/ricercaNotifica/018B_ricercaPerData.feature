Feature: Ricerca notifica per periodo temporale persona fisica

  @TestSuite
  @TA_PFinserimentoDataErrata
  @PF
  @PFRicercaNotifica

  Scenario: PN-9224-B29 - La persona fisica inserisce una data con formato errato
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche destinatario inserire una data con formato errato
    Then Il rettangolo del campo errato diventa rosso
    And Logout da portale persona fisica