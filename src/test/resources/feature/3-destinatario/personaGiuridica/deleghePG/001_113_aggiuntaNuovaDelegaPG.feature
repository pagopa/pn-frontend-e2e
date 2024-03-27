Feature: La persona giuridica aggiunge una nuova delega

  @TestSuite
  @TA_PGaggiungiNuovaDelega
  @DeleghePG
  @PG

  Scenario: PN-9165 - La persona giuridica aggiunge una nuova delega
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Si controlla che non sia presente una delega con stesso nome persona giuridica "Le Epistolae srl"
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Le Epistolae srl  |
      | codiceFiscale  | LELPTR04A01C352E  |
      | ente           | Comune di Palermo |
    And Nella sezione Aggiungi Delega persona giuridica verificare che la data sia corretta
    And Nella sezione Aggiungi Delega persona giuridica click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma
    And Nella sezione Deleghe persona giuridica si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    And Logout da portale persona giuridica
