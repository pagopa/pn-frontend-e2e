Feature:Deleghe lato ruolo operatore

  # Eliminated TestSuite tag until fix. Convivio spa is not visible for the user GabrieleDAnnunzio
  @DeleghePG_1
  @PG

  @TA_PGRuoloOperatoreControlloAssenzaDeleghe
  @deleghecambio

  Scenario: [TA-FE CONTROLLO DELGHE LAYOUT RUOLO OPERATORE]- Si controlla lato ruolo operatore assenza deleghe
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | GabrieleDAnnunzio |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl   |
#    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto
    Then Home page persona giuridica ruolo operatore viene visualizzata correttamente
      | ragioneSociale | DivinaCommedia Srl   |

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
    And Si accetta la delega con gruppo "gruppo-ruolo-operatore"
#    And Logout da portale persona giuridica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | GabrieleDAnnunzio |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
#    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto
    Then Home page persona giuridica ruolo operatore viene visualizzata correttamente
      | ragioneSociale | Convivio Spa   |
    And Cliccare sulla notifica restituita
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica