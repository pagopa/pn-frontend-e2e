Feature: invio notifica con sequence

  @TestSuite
  @WorkflowNotificaConSequence
  @NotificaConSequenceFailRetryRS

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-@FAIL_RS] - Il mittente invia una notifica a destinatario con sequence
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si verifica siano presenti recapiti digitali
      | email | provaemail@test.it |
    And Logout da portale persona fisica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Creazione notifica completa
      | oggettoDellaNotifica | Pagamento rata IMU    |
      | descrizione          | PAGAMENTO RATA IMU    |
      | modello              | AR    |
      | gruppoTest           | test-TA-FE-TEST       |
      | gruppoDev            | GruppoTest            |
      | codiceTassonometrico | 123456A               |
      | nomeFileYaml         | datiNotifica          |
      | nomePF               | Gaio Giulio           |
      | cognomePF            | Cesare                |
      | codiceFiscalePF      | CSRGGL44L13H501E      |
      | pec                  | test@fail.it         |
      | indirizzoPF          | via @FAIL_RS|
      | numeroCivicoPF       | 20   |
      | comunePF             | MILANO  |
      | provinciaPF          | MI   |
      | codicepostalePF      | 20147   |
      | statoPF              | ITALIA   |
      | nomeDocumentoNotifica | RATA SCADUTA IMU |
    And Si verifica che la notifica è stata creata correttamente
    #inserire filtro sulla notifica restituita in modo da averla sicuramente
    #sostiture controllo con pec
    And Si seleziona la notifica
    And Si attende completamento notifica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato  | //p[contains(text(),"La raccomandata") and contains(text(),"è stata stampata ed imbustata")]|
      | vediDettagli | true |
    And Si controlla allegato in timeline
      | xpathAllegato  | //button[@data-testid="download-legalfact" and contains(text(),"Scansione del plico")]|
    And Logout da portale mittente
