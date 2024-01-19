Feature: Il delegato persona giuridica modifica una delega non assegnandoli un gruppo
  Background: Login delegato persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "delegatoPG"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "delegatoPG"

  @TestSuite
  @TA_PGmodificaDelegaSenzaGruppo
  @DeleghePG
  @PG

  Scenario: PN-9173 - Il delegato persona giuridica modifica una delega non assegnandoli un gruppo
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone modifica
    And Si clicca sul bottone non assegna a un gruppo
    And Si clicca su conferma
    And Si controlla che la delega non abbia più il gruppo
    And Logout da portale persona giuridica