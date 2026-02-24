Feature: persona giuridica scarica attestazione opponibile

  @TA_PG_DownloadFileAOTPresaInCarico
  @DownloadFilePG
  @PG
  @deleghe1
  @NRT_Blocco_3
  @DownloadFile
  Scenario: PN-10432 - Persona giuridica scarica Attestazione opponibile a terzi: notifica presa in carico
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test     |
      | comune | Verona   |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    Then Home page mittente viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento RATA IMU PN-10432 |
      | descrizione       | PAGAMENTO RATA IMU PN-10432 |
      | codiceTassonomico | 100105P                     |
      | modalitaInvio     | A/R                         |
      | gruppo            | test-TA-FE-TEST             |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PG           |
      | nomeCognomeDestinatario | Convivio Spa |
      | codiceFiscale           | 27957814470  |
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20       |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente

    And PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si clicca bottone accetta cookies
    And Aspetta 60 secondi
    And Cliccare sulla notifica restituita
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                                                                  |