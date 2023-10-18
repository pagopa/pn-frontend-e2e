Feature: Il delegato persona fisica annulLa l'operazione di rifiuto delega

  Background: Login delegato
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login "delegatoPF" portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente
  @TestSuite
  @fase2Test47_NEG
  @new
  Scenario: Il delegato persona fisica annulLa l'operazione di rifiuto delega
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico "personaFisica"
    And Nella pagina Deleghe si clicca sul menu della delega a tuo carico "personaFisica"
    And Nella pagina Deleghe si sceglie opzione rifiuta
    And Si clicca sul bottone annulla all'interno del pop-up
    Then Si controlla che la delega a lo stato Attiva "personaFisica"
    And Logout da portale persona fisica