Feature: Ricerca persona fisica per Codice IUN
  #La persona fisica fa un ricerca per persona fisica

  @TestSuite
  @TA_PFricercaPerIUN
  @PF
  @PFRicercaNotifica

  Scenario:PN-9219 - Ricerca persona fisica per Codice IUN
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Nella pagina Piattaforma Notifiche PF si recupera un codice IUN valido
    And Cliccare sul bottone Filtra persona fisica
    Then Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN della notifica "datiNotifica"
    And  Logout da portale persona fisica