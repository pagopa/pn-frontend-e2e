Feature: Il delegato persona fisica annulLa l'operazione di rifiuto delega

  @TestSuite
  @TA_PFannullaRifiutoDelega
  @DeleghePF
  @PF

  Scenario: PN-9414-NEG - Il delegato persona fisica annulla l'operazione di rifiuto delega
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico
      | nome          | Gaio Giulio |
      | cognome       | Cesare      |
    And Nella pagina Deleghe si clicca sul menu della delega a tuo carico
      | nome          | Gaio Giulio |
      | cognome       | Cesare      |
    And Nella pagina Deleghe si sceglie opzione rifiuta
    And Si clicca sul bottone annulla all'interno del pop-up
    #Then Si controlla che la delega è ancora presente "personaFisica"
    Then Si controlla che la delega è ancora presente
      | nome       | Gaio Giulio |
      | familyName | Cesare      |
    And Logout da portale persona fisica