Feature: Mittente genera una notifica che non prevede pagamento

  @TestSuite
  @TA_InvioNotificaPFCoincidente
  @mittente
  @invioNotifiche

  Scenario: PN-9295 - Mittente genera una notifica mono destinatario a PF - Normalizzazione KO (indirizzo non trovato)
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
      | soggettoGiuridico       | PF                   |
      | nomeCognomeDestinatario | Giuseppe Coincidente |
      | codiceFiscale           | CNCGPP80A01H501J     |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | @FAIL-Irreperibile_AR |
      | civico    | 16                    |
      | localita  | Bologna               |
      | comune    | Bologna               |
      | provincia | BO                    |
      | cap       | 40121                 |
      | stato     | Italia                |
    And Cliccare su continua
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Cliccare sulla notifica restituita
    And Aspetta 600 secondi
    And Si clicca sul opzione Vedi Dettaglio
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale mittente
    And Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | giuseppe               |
      | pwd          | password123            |
      | name         | Giuseppe               |
      | familyName   | Coincidente            |
      | fiscalNumber | TINIT-CNCGPP80A01H501J |
    And Cliccare sulla notifica restituita
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale persona fisica

