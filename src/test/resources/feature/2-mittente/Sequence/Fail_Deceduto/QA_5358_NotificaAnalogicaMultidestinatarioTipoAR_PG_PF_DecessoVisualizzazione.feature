Feature: Notifica analogica multidestinatario tipo AR per PG e PF dove solo per uno di quest'ultimi è stato notificato il decesso mentre per l'altro è avvenuta la visualizzazione: VIEWED come macro stato della notifica

  @TestSuite
  @TA_QA_5358
  @NRT_Blocco_3
  @Sequence_Deceduto

  Scenario: [DECEDUTO_AR_QA-5358] Notifica analogica multidestinatario tipo AR per PG e PF dove solo per uno di quest'ultimi è stato notificato il decesso mentre per l'altro è avvenuta la visualizzazione: VIEWED come macro stato della notifica
    # Rimozione preventiva recapiti per permettere la ricezione delle sequence
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
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
    # Esecuzione scenario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU DECEDUTO_890_QA-5358 |
      | descrizione       | PAGAMENTO RATA IMU DECEDUTO_890_QA-5358 |
      | gruppo            | test-TA-FE         |
      | codiceTassonomico | 100105P            |
      | modalitaInvio     | A/R                |
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
    And Nella section Destinatario inserire i dati del secondo destinatario come persona giuridica
      | soggettoGiuridico | PG                    |
      | ragioneSociale    | Convivio Spa          |
      | codiceFiscale     | 27957814470           |
      | indirizzo         | Via @FAIL_DECEDUTO_AR |
      | civico            | 20                    |
      | localita          | Milano                |
      | comune            | Milano                |
      | provincia         | MI                    |
      | cap               | 20147                 |
      | stato             | Italia                |
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
#    And Aspetta 10 secondi
    And Attesa 10 secondi
    And Refresh pagina
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Invio in corso"
#    And Aspetta 400 secondi
    And Attesa 400 secondi
    And Refresh pagina
    And Si visualizza correttamente la timeline relativi a tutti i destinatari
      | PF | CSRGGL44L13H501E |
      | PG | 27957814470      |
    And Aspetta 10 secondi
    And In parallelo si effettua l'accesso al portale destinatario persona fisica e si verifica la timeline ""
    And Aspetta 60 secondi
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'stata consegnata perché il destinatario è deceduto')] |
      | vediDettagli | true                                                                       |