Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @TAG_DECEDUTO_AR_QA-5363
  @NRT_Blocco_3_GRUPPO_AWS
  @Sequence_Deceduto

  Scenario: [DECEDUTO_AR_QA-5363] - Il mittente invia una notifica tipo AR a due destinatari, PG deceduti
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
      | oggettoNotifica   | Pagamento rata IMU DECEDUTO_AR_QA-5363      |
      | descrizione       | PAGAMENTO RATA IMU DECEDUTO_AR_QA-5363      |
      | gruppo            | test-TA-FE-TEST         |
      | codiceTassonomico | 100105P                 |
      | modalitaInvio     | A/R                     |
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
      | indirizzo         | via @FAIL_DECEDUTO_AR |
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
    And Aspetta 10 secondi
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Invio in corso"
    And Aspetta 400 secondi
    And Si visualizza correttamente la timeline relativi a tutti i destinatari
      | PF | CSRGGL44L13H501E |
      | PG | 27957814470      |
    And Aspetta 10 secondi
    Then Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //p[contains(text(),'(CSRGGL44L13H501E)')] |
      | vediDettagli | true                                       |