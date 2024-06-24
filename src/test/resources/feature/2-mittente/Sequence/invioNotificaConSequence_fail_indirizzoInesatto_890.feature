Feature: invio notifica con sequence

  @Parallel
  @WorkflowNotificaConSequence
  @NotificaConSequenceFailIndirizzoInesatto890

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-@FAIL_IndirizzoInesatto890] - Il mittente invia una notifica a destinatario con sequence
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si verifica siano presenti recapiti digitali
      | email | provaemail@test.it |
    And Logout da portale persona fisica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Creazione notifica completa
      | oggettoDellaNotifica  | Pagamento rata IMU             |
      | descrizione           | PAGAMENTO RATA IMU             |
      | modello               | 890                            |
      | gruppoTest            | test-TA-FE-TEST                |
      | gruppoDev             | GruppoTest                     |
      | codiceTassonometrico  | 123456A                        |
      | nomeFileYaml          | datiNotifica                   |
      | nome                  | Gaio Giulio                    |
      | destinatario          | PF                             |

      | cognome               | Cesare                         |
      | codiceFiscale         | CSRGGL44L13H501E               |
      | indirizzo             | via @FAIL_IndirizzoInesatto890 |
      | numeroCivico          | 20                             |
      | comune                | MILANO                         |
      | provincia             | MI                             |
      | codicepostale         | 20147                          |
      | stato                 | ITALIA                         |
      | nomeDocumentoNotifica | RATA SCADUTA IMU               |
    And Si verifica che la notifica è stata creata correttamente
    And Si seleziona la notifica
    And Si attende completamento notifica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"è stata stampata ed imbustata")] |
      | vediDettagli | true                                                                                             |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"C'è un nuovo documento allegato")] |
      | vediDettagli | false                                                   |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),"La raccomandata 890") and contains(text(),"non è stata consegnata perché l’indirizzo è inesatto")] |
      | vediDettagli | false                                                                                                                   |
    And Logout da portale mittente