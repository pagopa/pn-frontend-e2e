Feature: Mittente invia una notifica analogica o digitale che viene annullata

  @annullamentoNotifica
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica e quando passa allo stato depositata viene annullata
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
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Si attende che lo stato della notifica sia "Depositata"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Aspetta 120 secondi
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"
    And Logout da portale mittente

  @annullamentoNotifica
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica e quando passa allo stato invio in corso viene annullata
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
      | nomeCognomeDestinatario | Caio Giulio      |
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
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Si attende che lo stato della notifica sia "Invio in corso"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Aspetta 120 secondi
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"
    And Logout da portale mittente

  @annullamentoNotifica
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica e quando passa allo stato consegnata viene annullata
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
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Aspetta 150 secondi
    And Si attende che lo stato della notifica sia "Consegnata"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Aspetta 120 secondi
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"
    And Logout da portale mittente

  @annullamentoNotifica
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica e quando passa allo stato perfezionata per decorrenza termini viene annullata
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
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Aspetta 150 secondi
    And Si attende che lo stato della notifica sia "Perfezionata per decorrenza termini"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Aspetta 120 secondi
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"
    And Logout da portale mittente

  @annullamentoNotifica
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica e quando passa allo stato destinatario irreperibile viene annullata
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
      | indirizzo | Via @FAIL-IRREPERIBILE_AR |
      | civico    | 20                        |
      | localita  | Milano                    |
      | comune    | Milano                    |
      | provincia | MI                        |
      | cap       | 20147                     |
      | stato     | Italia                    |
    And Cliccare su continua
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Aspetta 180 secondi
    And Si attende che lo stato della notifica sia "Destinatario irreperibile"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Aspetta 120 secondi
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"
    And Logout da portale mittente

  @annullamentoNotifica
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica e quando passa allo stato avvenuto accesso viene annullata
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
    And Si attende che lo stato della notifica sia "Avvenuto accesso"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Aspetta 120 secondi
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"
    And Logout da portale mittente