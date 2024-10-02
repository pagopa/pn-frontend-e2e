Feature: La persona giuridica aggiunge una nuova delga inserendo una data errata

  @TestSuite
  @TA_PGNuovaDelegaDataErrata
  @DeleghePG
  @PG

  Scenario: PN-9165-A111 - La persona giuridica aggiunge una nuova delega inserendo una data errata
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Le Tue Deleghe inserire una data con formato errato e antecedente alla data
    And Verifica che non è possibile selezionare una data Fine antecedente ad oggi
   # And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore data errata
    And Logout da portale persona giuridica