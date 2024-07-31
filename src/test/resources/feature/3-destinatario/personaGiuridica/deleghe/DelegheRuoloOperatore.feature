Feature:Deleghe lato ruolo operatore

  @TestSuite
  @DeleghePG
  @PG

  @TA_PGRuoloOperatoreControlloAssenzaDeleghe
  Scenario: [TA-FE CONTROLLO DELGHE LAYOUT RUOLO OPERATORE]- Si controlla lato ruolo operatore assenza deleghe
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | GabrieleDAnnunzio |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    Then Home page persona giuridica ruolo operatore viene visualizzata correttamente
      | ragioneSociale | Convivio Spa   |
    And Si controlla che non esista il bottone deleghe nel side menu
    And Logout da portale persona giuridica

  @TA_PGRuoloOperatoreControlloAnnullamentoNotifica
  Scenario: [TA-FE CONTROLLO DELGHE LAYOUT RUOLO OPERATORE]- Si controlla lato ruolo operatore che non sia possibile annullare la notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome | delegante    |
      | fiscalCode  | 27957814470  |
      | companyName | Convivio Spa |
      | displayName | Convivio Spa |
      | person      | false        |
    And Si accetta la delega "con" gruppo "gruppo-ruolo-operatore"
    And Logout da portale persona giuridica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | GabrieleDAnnunzio |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    Then Home page persona giuridica ruolo operatore viene visualizzata correttamente
      | ragioneSociale | Convivio Spa   |
    And Cliccare sulla notifica restituita
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Logout da portale persona giuridica