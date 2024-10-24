Feature: Mittente genera una notifica che non prevede pagamento

  @Parallel
  @TA_InvioNotificaMonoPFNormalizzazioneKOLovelace
  @mittente
  @invioNotifiche

  Scenario: PN-9294 - Mittente genera una notifica mono destinatario a PF - Normalizzazione KO (indirizzo non trovato)
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
      | nomeCognomeDestinatario | Ada Lovelace     |
      | codiceFiscale           | LVLDAA85T50G702B |
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
    And Cliccare sulla notifica restituita
    And Aspetta 600 secondi
    And Si clicca sul opzione Vedi Dettaglio
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale mittente
    And Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | ada                    |
      | pwd          | password123            |
      | name         | Ada                    |
      | familyName   | Lovelace               |
      | fiscalNumber | TINIT-LVLDAA85T50G702B |
    And Cliccare sulla notifica restituita
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale persona fisica

  @TestSuite
  @TA_InvioNotificaMonoPFNormalizzazioneKOLovelaceBis
  Scenario: PN-9294-bis - Mittente genera una notifica mono destinatario a PF - Normalizzazione KO (indirizzo non trovato)
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Mittente ricerca notifica con IUN salvato "RZVD-VGVL-XZLK-202410-X-1"
    And Si clicca la notifica ricercata
    And Si clicca sul opzione Vedi Dettaglio
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale mittente
    And Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | ada                    |
      | pwd          | password123            |
      | name         | Ada                    |
      | familyName   | Lovelace               |
      | fiscalNumber | TINIT-LVLDAA85T50G702B |
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "RZVD-VGVL-XZLK-202410-X-1"
    And Si clicca la notifica ricercata
    Then Si verifica che la notifica abbia lo stato "Destinatario irreperibile"
    And Logout da portale persona fisica

