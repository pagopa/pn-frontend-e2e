Feature: Il delegato visualizza il dettaglio di una notifica

  @TestSuite
  @TA_PFdelegatovisualizzaDettaglioNotifica
  @DeleghePF
  @PF

  Scenario:PN-9417 - Il delegato visualizza il dettaglio di una notifica
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega accettata
    When Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Si seleziona il nome del delegante nell elenco
    And Si visualizza correttamente la Pagina Notifiche persona fisica delegante "personaFisica"
    And Nella pagina Piattaforma Notifiche PF si recupera un codice IUN valido
    And Cliccare sul bottone Filtra del delegato
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And  Logout da portale persona fisica