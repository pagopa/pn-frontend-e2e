Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @NotificaADueDestinatariConSequenceDecedutoConsegnato890
  @NRT
  @Deceduto_aws

  Scenario: [DECEDUTO_890_QA-5357] - Il mittente invia una notifica a due destinatari, solo uno raggiungibile al primo tentativo
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 100105P            |
      | modalitaInvio     | 890                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Giuseppe Maria Garibaldi      |
      | codiceFiscale           | GRBGPP87L04L741X |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma              |
      | civico    | 20                    |
      | localita  | Milano                |
      | comune    | Milano                |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del secondo destinatario come persona giuridica
      | soggettoGiuridico | PG           |
      | ragioneSociale    | Convivio Spa |
      | codiceFiscale     | 27957814470  |
      | indirizzo         | @FAIL_DECEDUTO_890     |
      | civico            | 20           |
      | localita          | Milano       |
      | comune            | Milano       |
      | provincia         | MI           |
      | cap               | 20147        |
      | stato             | Italia       |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Seleziona Nessun Pagamento 2
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    And Aspetta 10 secondi
    And Cliccare sulla notifica restituita
    And Si attende completamento notifica "Invio in corso"
    And Aspetta 400 secondi
    And Si visualizza correttamente la timeline relativi a tutti i destinatari
      | PF | GRBGPP87L04L741X |
      | PG | 27957814470      |
    And Aspetta 10 secondi
#    And Si visualizza testo nella timeline "è riuscito"
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'è riuscito')] |
      | vediDettagli | true |
    And Aspetta 10 secondi
#    And Si visualizza testo nella timeline "stata consegnata perché il destinatario è deceduto"
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'stata consegnata perché il destinatario è deceduto')] |
      | vediDettagli | true |
    And Aspetta 10 secondi
#    And Si visualizza testo nella timeline "almeno un recapito digitale è valido"
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'almeno un recapito digitale è valido')] |
      | vediDettagli | true |
    #And Logout da portale mittente

