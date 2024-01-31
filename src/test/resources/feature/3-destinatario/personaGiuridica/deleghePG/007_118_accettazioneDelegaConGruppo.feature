Feature:Il delegato persona giuridica accetta la delega assegnandoli un gruppo

  # DISABLED Temporary disabled until the bug PN-9170 is fixed
#  @TestSuite
#  @TA_PGaccettaDelegaConGruppo
#  @DeleghePG
#  @PG

  Scenario: PN-9170 - Il delegato persona giuridica accetta la delega assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
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