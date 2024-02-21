Feature:Il delegato persona giuridica accetta la delega non assegnandoli un gruppo personaGiuridica

    @TestSuite
    @TA_PGaccettazioneDelegaSenzaGruppo
    @DeleghePG
    @PG

  Scenario: PN-9171 - Il delegato persona giuridica accetta la delega non assegnandoli un gruppo
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa si controlla la presenza di una delega per PG
      | accessoCome    | delegato      |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si accetta la delega "senza" gruppo
    And Si controlla che la delega PG ha lo stato Attiva "Le Epistolae srl"
    And Logout da portale persona giuridica