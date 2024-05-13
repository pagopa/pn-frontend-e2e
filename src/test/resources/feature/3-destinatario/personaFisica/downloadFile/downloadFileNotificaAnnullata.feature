Feature: Mittente invia una notifica digitale che viene annullata e lato destinatario si controlla non sia possibile scaricare alcun file

  @checkDownloadFileNotificaAnnullata
  @recapitiPF
  Scenario: [TA-FE INVIO DI UNA NOTIFICA, ANNULLAMENTO, CHECK DOWNLOAD FILE PER PF] - Mittente invia una notifica e la annulla, il destinatario persona fisica non può scaricare i file
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una "PEC" e si inserisce "prova@test.it"
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
    And Aspetta 120 secondi
    And Si attende che lo stato della notifica sia "Perfezionata per decorrenza termini"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Aspetta 120 secondi
    Then In parallelo si effettua l'accesso al portale di "persona fisica"
    And Cliccare sulla notifica restituita
    And Si verifica che gli allegati denominati "PAGAMENTO RATA IMU" non sono scaricabili
    And Si verifica che gli AAR non sono scaricabili
    And Si verifica che le attestazioni opponibili a terzi non siano scaricabili
    And Si verifica che non sia possibile scaricare le ricevute PEC
    And Logout da portale mittente
    And Si accede nuovamente al portale "persona fisica" con token "delegante" per eliminare i recapiti inseriti