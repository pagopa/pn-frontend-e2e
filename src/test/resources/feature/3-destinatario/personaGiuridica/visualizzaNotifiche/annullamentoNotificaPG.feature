Feature: Il mittente invia una notifica analogica o digitale a destinatario persona fisica e viene annullata

  @annullamentoNotificaPG
  Scenario: [TA-FE INVIO DI UNA NOTIFICA A PERSONA FISICA E ANNULLAMENTO] - Mittente invia una notifica e la annulla, anche la persona fisica vede la notifica annullata
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
      | soggettoGiuridico       | PG           |
      | nomeCognomeDestinatario | Convivio Spa |
      | codiceFiscale           | 27957814470  |
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
    And Aspetta 30 secondi
    And Si seleziona la notifica
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Aspetta 120 secondi
    Then In parallelo si effettua l'accesso al portale di "persona giuridica"
    And Si controlla che nel portale del destinatario la notifica sia "Annullata" e si chiude la scheda
    And Logout da portale mittente