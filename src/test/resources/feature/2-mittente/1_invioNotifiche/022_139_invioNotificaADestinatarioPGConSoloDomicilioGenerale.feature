Feature: Mittente invia una notifica digitale a destinatario persona giuridica senza domicilio di piattaforma e speciale, solo generale

  @TestSuite
  @TA_invioNotificaADestinatarioPGConSoloDomicilioGenerale
  @mittente
  @invioNotifiche

  Scenario: PN-9253 - Mittente invia una notifica digitale a destinatario persona giuridica senza domicilio di piattaforma e speciale, solo generale
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
      | soggettoGiuridico       | PG               |
      | nomeCognomeDestinatario | Convivio Spa     |
      | codiceFiscale           | 27957814470      |
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
    And Aspetta 180 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    And Si verifica l'invio della notifica al domicilio generale "27957814470@pec.it"
    Then Si verifica che la notifica abbia lo stato "Consegnata"
    And Logout da portale mittente