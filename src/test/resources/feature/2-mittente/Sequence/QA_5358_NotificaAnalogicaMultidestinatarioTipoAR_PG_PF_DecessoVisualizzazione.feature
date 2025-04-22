Feature: Notifica analogica multidestinatario tipo AR per PG e PF dove solo per uno di quest'ultimi è stato notificato il decesso mentre per l'altro è avvenuta la visualizzazione: VIEWED come macro stato della notifica

  #@TestSuite
  @TA_QA_5358

  Scenario: QA-5358 Notifica analogica multidestinatario tipo AR per PG e PF dove solo per uno di quest'ultimi è stato notificato il decesso mentre per l'altro è avvenuta la visualizzazione: VIEWED come macro stato della notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | descrizione       | PAGAMENTO RATA IMU |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 100105P            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio Cesare      |
      | codiceFiscale           | CSRGGL44L13H501E |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20                    |
      | localita  | Milano                |
      | comune    | Milano                |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del destinatario persona giuridica aggiuntiva
      | soggettoGiuridico | PG           |
      | ragioneSociale    | Test Spa |
      | codiceFiscale     | 00749900049  |
      | indirizzo         | Via @FAIL_DECEDUTO_AR   |
      | civico            | 20           |
      | localita          | Milano       |
      | comune            | Milano       |
      | provincia         | MI           |
      | cap               | 20147        |
      | stato             | Italia       |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche

## ----------------------------------------------------------------------------
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    And Aspetta 10 secondi
    And Cliccare sulla notifica restituita



    And Si attende completamento notifica "Invio in corso"
    And Aspetta 400 secondi
    And Si visualizza correttamente la timeline relativi a tutti i destinatari
      | PF | CSRGGL44L13H501E |
    And Aspetta 10 secondi

## ----------------------------------------------------------------------------

    And In parallelo si effettua l'accesso al portale destinatario persona fisica e si verifica la timeline ""
    And Aspetta 10 secondi
##    parte finale
    Then Si visualizza testo nella timeline "Almeno un destinatario ha letto la notifica"
    And Si visualizza testo nella timeline "stata consegnata perché il destinatario è deceduto"

    And In parallelo si effettua l'accesso al portale destinatario persona fisica e si verifica la timeline "Almeno un destinatario ha letto la notifica"