Feature:Il delegato persona giuridica annulLa l'operazione di rifiuto delega

  @TestSuite
  @TA_PGannullaRifiutoDelega
  @DeleghePG
  @PG

  Scenario Outline: PN-9172-A118 - Il delegato persona giuridica annulLa l'operazione di rifiuto delega
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella sezione Deleghe si verifica sia presente una delega accettata per PG "personaGiuridica"
    And Nella pagina Deleghe sezione Deleghe a carico dell'impresa clicca sul menu della delega "personaGiuridica"
    And Nella sezione Deleghe si clicca sul bottone rifiuta
    And Si clicca sul bottone annulla
    And Si controlla che la delega PG ha lo stato Attiva <ragioneSociale>
    And Logout da portale persona giuridica
    Examples:
      | ragioneSociale |
      | Convivio Spa   |