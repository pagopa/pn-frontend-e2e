Feature:Il delegato persona giuridica accetta la delega non assegnandoli un gruppo personaGiuridica

    @TestSuite
    @TA_PGaccettazioneDelegaSenzaGruppo
    @DeleghePG
    @PG

  Scenario: PN-9171 - Il delegato persona giuridica accetta la delega non assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si inserisce un codice della delega a carico dell impresa errato nella modale
    Then Le textbox che contengono le cifre del codice delega diventano rosse
    And Si accetta la delega senza gruppo
    And Si controlla che la delega PG ha lo stato Attiva "Convivio Spa"
    And Logout da portale persona giuridica