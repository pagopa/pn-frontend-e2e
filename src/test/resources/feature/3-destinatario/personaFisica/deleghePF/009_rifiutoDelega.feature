Feature: Il delgato persona fisica rifiuta la delega che gli è stata inviata

  Background: Login delegato
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login "delegatoPF" portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @@TA_PFrifiutoDelega
  @DeleghePF
  @PF

  Scenario: Il delegato persona fisica rifiuta la delega che gli è stata inviata
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica sia presente una delega da rifiutare nella sezione Deleghe a Tuo Carico "personaFisica"
    And Nella pagina Deleghe si clicca sul menu della delega a tuo carico "personaFisica"
    And Nella pagina Deleghe si sceglie opzione rifiuta
    And Si clicca sul bottone rifiuta all'interno del pop-up
    And Si controlla che la delega non sia più presente nella lista "personaFisica"
    And Logout da portale persona fisica