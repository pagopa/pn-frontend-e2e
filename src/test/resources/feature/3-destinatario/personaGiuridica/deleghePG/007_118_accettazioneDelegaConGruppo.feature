Feature:Il delegato persona giuridica accetta la delega assegnandoli un gruppo
  Background: Login delegato persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "delegatoPG"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "delegatoPG"

  @TestSuite
  @TA_PGaccettaDelegaConGruppo
  @DeleghePG
  @PG

  Scenario: Il delegato persona giuridica accetta la delega assegnandoli un gruppo
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega
    And si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuovaDelegaPG"
    And Nella sezione Deleghe si clicca sul bottone Accetta
    And Si assegna un gruppo alla delega
    And Si clicca sul bottone conferma
    And Si controlla che la delega PG a lo stato Attiva "personaGiuridica_1"
    And Logout da portale persona giuridica