Feature:Il delegato persona giuridica accetta la delega non assegnandoli un gruppo

  # DISABLED Temporary disabled until the bug PN-9171 is fixed
#  @TestSuite
#  @TA_PGaccettazioneDelegaSenzaGruppo
#  @DeleghePG
#  @PG

  Scenario: PN-9171 - Il delegato persona giuridica accetta la delega non assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
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