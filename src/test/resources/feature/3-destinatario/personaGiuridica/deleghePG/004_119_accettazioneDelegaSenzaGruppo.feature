Feature:Il delegato persona giuridica accetta la delega non assegnandoli un gruppo

 @TestSuite
 @TA_PGaccettazioneDelegaSenzaGruppo
 @DeleghePG
 @PG

  Scenario: PN-9171 - Il delegato persona giuridica accetta la delega non assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega per PG "personaGiuridica"
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuovaDelegaPG"
    And Si clicca sul bottone conferma delega
    And Si clicca su conferma in assegnazione gruppo
    And Si controlla che la delega PG a lo stato Attiva "personaGiuridica"
    And Logout da portale persona giuridica