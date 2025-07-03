Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @NotificaATreDestinatariConSequenceDecedutoConsegnatoIrreperibile890
  @NRT_Blocco_3
  @Sequence_Deceduto

  Scenario: [DECEDUTO_890_QA-5359] - Il mittente invia una notifica a tre destinatari, solo uno raggiungibile al primo tentativo
    # Rimozione preventiva recapiti per permettere la ricezione delle sequence
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Attesa 20 secondi
    And Rimuovi tutti i recapiti se esistono
    And Logout da portale persona fisica
    And Login con persona fisica input
      | user         | garibaldi              |
      | pwd          | password123            |
      | name         | Giuseppe Maria         |
      | familyName   | Garibaldi              |
      | fiscalNumber | TINIT-GRBGPP87L04L741X |
    And Si visualizza correttamente la pagina Piattaforma Notifiche persona fisica
    And Rimuovi tutti i recapiti se esistono
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    And Si clicca su prodotto
    And Si visualizza correttamente la pagina Piattaforma Notifiche persona giuridica
    And Rimuovi tutti i recapiti se esistono
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU DECEDUTO_890_QA-5359     |
      | descrizione       | PAGAMENTO RATA IMU DECEDUTO_890_QA-5359     |
      | gruppo            | test-TA-FE-TEST         |
      | codiceTassonomico | 100105P                 |
      | modalitaInvio     | 890                     |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF                 |
      | nomeCognomeDestinatario | Gaio Giulio Cesare |
      | codiceFiscale           | CSRGGL44L13H501E   |
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20       |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Seleziona radion button Inserimento Manuale se esiste "1"
    And Nella section Destinatario inserire i dati del secondo destinatario come persona fisica
      | soggettoGiuridico | PF                    |
      | nome              | Giuseppe Maria        |
      | cognome           | Garibaldi             |
      | codiceFiscale     | GRBGPP87L04L741X      |
      | indirizzo         | Via @FAIL_DECEDUTO_AR |
      | civico            | 20                    |
      | localita          | Milano                |
      | comune            | Milano                |
      | provincia         | MI                    |
      | cap               | 20147                 |
      | stato             | Italia                |
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Seleziona radion button Inserimento Manuale se esiste "2"
    And Nella section Destinatario inserire i dati del terzo destinatario come persona giuridica
      | soggettoGiuridico | PG                         |
      | ragioneSociale    | Convivio Spa               |
      | codiceFiscale     | 27957814470                |
      | indirizzo         | Via @fail-irreperibile_890 |
      | civico            | 20                         |
      | localita          | Milano                     |
      | comune            | Milano                     |
      | provincia         | MI                         |
      | cap               | 20147                      |
      | stato             | Italia                     |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Seleziona Nessun Pagamento 2
    And Seleziona Nessun Pagamento 3
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
#    And Aspetta 10 secondi
    And Attesa 15 secondi
    And Refresh pagina
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Invio in corso"
#    And Aspetta 400 secondi
    And Attesa 400 secondi
    And Refresh pagina
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'(CSRGGL44L13H501E)')] |
      | vediDettagli | true                                       |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'(GRBGPP87L04L741X)')] |
      | vediDettagli | false                                      |
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'(27957814470)')] |
      | vediDettagli | false                                 |
    And Aspetta 10 secondi
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'stata consegnata perché il destinatario è sconosciuto')] |
      | vediDettagli | true                                                                          |
    And Aspetta 10 secondi
    And In parallelo si effettua l'accesso al portale destinatario persona fisica e si verifica la timeline ""
#    And Aspetta 60 secondi
    And Attesa 60 secondi
    And Refresh pagina
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'risultato irreperibile assoluto')] |
      | vediDettagli | true                                                    |

