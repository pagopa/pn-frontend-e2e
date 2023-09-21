Feature: Il delegato persona fisica annulLa l'operazione di rifiuto delega

  Background: Login delegato
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login "delegatoPF" portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  Scenario: Il delegato persona fisica annulLa l'operazione di rifiuto delega
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico
    And Nella pagina Deleghe si clicca sul menu della delega
    And Nella pagina Deleghe si sceglie opzione rifiuta
    And Si clicca sul bottone annulla all'interno del pop-up
    And Si controlla che la delega a lo stato Attiva "personaFisica"
    And Logout da portale persona fisica