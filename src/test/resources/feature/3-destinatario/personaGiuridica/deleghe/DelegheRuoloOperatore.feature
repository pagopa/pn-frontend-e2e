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