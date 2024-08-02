Feature: persona fisica scarica attestazioni all'interno di una notifica

  @parallel
  @PF

  @TA_PFDownloadAttestazioneOpponibileEControllaSHA
  Scenario: PN-10391 - persona fisica scarica attestazione opponibile presa in carico
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA           |
      | codicePostale    | 20147              |
      | comune           | Milano             |
      | dettagliComune   | Milano             |
      | provincia        | MI                 |
      | stato            | Italia             |
      | nomeCognome      | Gaio Giulio Cesare |
      | codiceFiscale    | CSRGGL44L13H501E   |
      | tipoDestinatario | PF                 |
      | avvisoPagoPa     | 1                  |
      | F24              | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella sezione Dettaglio Notifiche PG si seleziona il file, "Attestazione opponibile a terzi: notifica presa in carico", da scaricare
    Then Si controlla il SHA all interno del file atteztazione
    And Logout da portale persona fisica


  @TA_PFDownloadAttestazioneOpponibilePresaInCaricoEVerificaEnte
  Scenario: PN-10428 - Persona fisica scarica Attestazione opponibile a terzi: notifica presa in carico
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    Then Home page mittente viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica senza gruppo
      | oggettoNotifica   | Pagamento RATA IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Giulio Cesare    |
      | codiceFiscale           | CSRGGL44L13H501E |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20       |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |
    And Cliccare su continua
    And Si clicca sul bottone esci
    And Si clicca sul bottone esci
    And PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si clicca bottone accetta cookies
    And Aspetta 60 secondi
    And Cliccare sulla notifica restituita
    And Nella sezione Dettaglio Notifiche PG si seleziona il file, "Attestazione opponibile a terzi: notifica presa in carico", da scaricare
    Then Si controlla il testo all interno del file destinatario "Attestazione_opponibile_a_terzi_notifica_presa_in_carico"
    And Logout da portale persona fisica


  @@TA_PFVerificaEnteNelDettaglioNotifica
  Scenario: PN-10427 - Persona fisica verifica concatenazione nome ente Radice e ente Figlio in dettaglio notifica
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    Then Home page mittente viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica senza gruppo
      | oggettoNotifica   | Pagamento RATA IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Giulio Cesare    |
      | codiceFiscale           | CSRGGL44L13H501E |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20       |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |
    And Cliccare su continua
    And Si clicca sul bottone esci
    And Si clicca sul bottone esci
    And PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si clicca bottone accetta cookies
    And Aspetta 60 secondi
    And Cliccare sulla notifica restituita
    And Verifica nome ente mittente "Comune di Viggiu"