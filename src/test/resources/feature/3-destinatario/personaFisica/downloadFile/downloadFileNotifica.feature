Feature: Mittente invia una notifica digitale che viene annullata e lato destinatario si controlla non sia possibile scaricare alcun file

  @checkDownloadFileNotifica
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
    And Aspetta 180 secondi
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Aspetta 120 secondi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Cliccare sulla notifica restituita
    And Si verifica che gli allegati denominati "PAGAMENTO RATA IMU" non sono scaricabili
    And Si verifica che gli AAR non sono scaricabili
    And Si verifica che le attestazioni opponibili a terzi non siano scaricabili
    And Si verifica che non sia possibile scaricare le ricevute PEC
    And Logout da portale persona fisica

  @checkDownloadFileNotifica
  Scenario: [TA-FE DOWNLOAD PIU' MODELLI F24] - Mittente invia una notifica e la annulla, il destinatario persona fisica non può scaricare i file
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche del destinatario si filtra per codice IUN "RHVZ-HZAH-NDKL-202406-K-1"
    And Cliccare sul bottone Filtra persona fisica
    When La persona fisica clicca sulla notifica restituita
    And Si controlla il dettaglio della notifica
    And Si controlla sia visualizza box allegati modelli F24 destinatario
    And Si clicca il modello F24 destinatario
    And Si clicca il secondo modello F24 destinatario
    And Logout da portale persona fisica