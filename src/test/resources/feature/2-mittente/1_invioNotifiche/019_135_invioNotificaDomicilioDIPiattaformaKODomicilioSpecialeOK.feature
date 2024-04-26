Feature: Mittente invia una notifica digitale a destinatario, KO invio a domicilio di piattaforma, OK invio a domicilio speciale

  @TestSuite
  @TA_invioNotificaDomicilioDiPiattaformaKODomicilioSpecialeOK
  @mittente
  @invioNotifiche
  @recapitiPF

  Scenario: PN-9249 - Mittente invia una notifica digitale a destinatario, KO invio a domicilio di piattaforma, OK invio a domicilio speciale
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una PEC e si inserisce "prova@fail.it"
    And Logout da portale persona fisica
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
    And Nella section Destinitario si clicca su "Aggiungi un domicilio digitale" e si inseriscono i dati
      | digitalAddress | prova@test.it |
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
    And Aspetta 240 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    And Si verifica che l'invio della notifica sia fallito 1 volte
    And Si verifica l'invio della notifica al domicilio speciale inserito "prova@test.it"
    Then Si verifica che la notifica abbia lo stato "Consegnata"
    And Logout da portale mittente
    And Si accede nuovamente al portale "persona fisica" con token "delegante" per eliminare i recapiti inseriti