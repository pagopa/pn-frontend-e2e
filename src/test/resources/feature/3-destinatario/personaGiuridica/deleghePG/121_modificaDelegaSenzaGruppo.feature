Feature: Il delegato persona giuridica modifica una delega assegnandoli un gruppo
  Background: Login delegato persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login "delegatoPG" portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica

  Scenario: Il delegato persona giuridica modifica una delega assegnandoli un gruppo
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone modifica
    And Si clicca sul bottone assegna a un gruppo
    And Si selezione il gruppo della delega
    And Si clicca su conferma
    And Si controlla che la delega a lo stato Attiva "personaGiuridica"
    And Logout da portale persona giuridica