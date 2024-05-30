Feature: annullamento della notifica

  @annullamentoNotifica
  Scenario: PN-9242 - Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
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
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    Then In parallelo si effettua l'accesso al portale destinatario "persona fisica" e si apre la notifica ricevuta
    And Aspetta 180 secondi
    And Cliccare sulla notifica restituita
    Then Si verifica che la notifica abbia lo stato "Avvenuto accesso"
    And Logout da portale mittente

  @annullamentoNotifica
  Scenario: PN-10394 - Mittente invia una notifica con avviso PagoPa e F24, la annulla e controlla quali file sono scaricabili
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Aspetta 10 secondi
    When Creo in background una notifica con un destinatario e un documento tramite API REST
      | avvisoPagoPa | false |
      | F24          | false |
      | entrambi     | true  |
    Then Attendo 5 minuti e verifico in background che la notifica sia stata creata correttamente
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la sezione Dettaglio Notifica
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    Then Si controlla sia presente il modello F24
    Then Si controlla sia presente l'avviso PagoPa
    Then Si clicca l'avviso PagoPa
    Then Si torna alla pagina precedente
    And Logout da portale mittente