Feature:Il delegato persona giuridica accetta la delega non assegnandoli un gruppo
  Background: Login delegato persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "delegatoPG"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "delegatoPG"

  @TestSuite
  @TA_PGaccettazioneDelegaSenzaGruppo
  @DeleghePG
  @PG

  Scenario: PN-9171 - Il delegato persona giuridica accetta la delega non assegnandoli un gruppo
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega per PG "personaGiuridica"
    And si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuovaDelegaPG"
    And Nella sezione Deleghe si clicca sul bottone Accetta
    And Non si assegna un gruppo alla delega
    And Si clicca sul bottone conferma
    And Si controlla che la delega PG a lo stato Attiva "personaGiuridica"
    And Logout da portale persona giuridica