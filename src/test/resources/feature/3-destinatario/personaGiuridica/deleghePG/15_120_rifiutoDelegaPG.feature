Feature:Il delegato persona giuridica rifiuta la delega
  Background: Login delegato persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login "delegatoPG" portale persona fisica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica

  @TestSuite
  @fase2Test120

  Scenario: Il delegato persona giuridica rifiuta la delega
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone rifiuta
    And Si clicca sul bottone rifiuta delega
    And Si controlla che la delega non si più presente in elenco
    And Logout da portale persona giuridica