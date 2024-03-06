Feature:Il delegato persona giuridica annulLa l'operazione di rifiuto delega

  @TestSuite
  @TA_PGannullaRifiutoDelega
  @DeleghePG
  @PG

  Scenario: PN-9172-A118 - Il delegato persona giuridica annulla l'operazione di rifiuto delega
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si sceglie opzione accetta
    And Si inserisce il codice della delega a carico dell impresa nella modale
    And Si clicca sul bottone accetta delega dopo aver inserito il codice di verifica
    And Si clicca su conferma in assegnazione gruppo
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone rifiuta
    And Si clicca sul bottone annulla
    And Si controlla che la delega PG ha lo stato Attiva "Convivio Spa"
    And Logout da portale persona giuridica
