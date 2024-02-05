Feature: Il delegato persona fisica annulLa l'operazione di rifiuto delega

  @TestSuite
  @TA_PFannulaRifiutoDelega
  @DeleghePF
  @PF

  Scenario: Il delegato persona fisica annulla l'operazione di rifiuto delega
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico "personaFisica"
    And Nella pagina Deleghe si clicca sul menu della delega a tuo carico "personaFisica"
    And Nella pagina Deleghe si sceglie opzione rifiuta
    And Si clicca sul bottone annulla all'interno del pop-up
    Then Si controlla che la delega è ancora presente "personaFisica"
    And Logout da portale persona fisica