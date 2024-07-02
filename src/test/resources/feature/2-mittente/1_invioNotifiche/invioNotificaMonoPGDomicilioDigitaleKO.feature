Feature: Mittente genera una notifica che non prevede pagamento

  @TestSuite
  @TA_InvioNotificaMonoPGDomiccilioDigitaleKO
  @mittente
  @invioNotifiche

  Scenario: PN-9292 - Mittente genera una notifica mono destinatario a PG con domicilio digitale KO
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
      | soggettoGiuridico       | PG          |
      | nomeCognomeDestinatario | Test SPA    |
      | codiceFiscale           | 00749900049 |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | @FAIL-Irreperibile_AR |
      | civico    | 20                    |
      | localita  | Milano                |
      | comune    | Milano                |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Cliccare su continua
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Aspetta 300 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale mittente