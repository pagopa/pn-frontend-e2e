Feature: invio notifica con sequence

  @TestSuite
  @WorkflowNotificaConSequence
  @NotificaConSequenceOKGiacenzaDelegatogt1023L890

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-@OK-GiacenzaDelegato-gt10-23L_890] - Il mittente invia una notifica a destinatario con sequence
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
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
      | modello              | 890    |
      | gruppoTest           | test-TA-FE-TEST       |
      | gruppoDev            | GruppoTest            |
      | codiceTassonometrico | 123456A               |
      | nomeFileYaml         | datiNotifica          |
      | nomePF               | Gaio Giulio           |
      | cognomePF            | Cesare                |
      | codiceFiscalePF      | CSRGGL44L13H501E      |
      | indirizzoPF          | via @OK-GiacenzaDelegato-gt10-23L_890|
      | numeroCivicoPF       | 20   |
      | comunePF             | MILANO  |
      | provinciaPF          | MI   |
      | codicepostalePF      | 20147   |
      | statoPF              | ITALIA   |
      | nomeDocumentoNotifica | RATA SCADUTA IMU |
    And Si verifica che la notifica è stata creata correttamente
    And Si seleziona la notifica
    And Si attende completamento notifica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato  | //p[contains(text(),"La raccomandata 890") and contains(text(),"è stata stampata ed imbustata")]|
      | vediDettagli | true |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato  | //p[contains(text(),"Il tentativo di consegna della raccomandata 890") and contains(text(),"non è andato a buon fine per destinatario assente")]|
      | vediDettagli | false |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato  | //p[contains(text(),"La raccomandata 890") and contains(text(),"è stata ritirata o ha compiuto la sua giacenza")]|
      | vediDettagli | false |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato  | //p[contains(text(),"C'è un nuovo documento allegato")]|
      | vediDettagli | false |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato  | //p[contains(text(),"La raccomandata 890") and contains(text(),"  è stata ritirata da una persona delegata presso il punto di giacenza")]|
      | vediDettagli | false |
    And Logout da portale mittente
