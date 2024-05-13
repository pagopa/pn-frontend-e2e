Feature: Mittente invia una notifica analogica o digitale con delegato e la annulla, si verifica la notifica sia presente lato delegato

  @annullamentoNotificaDelegato
  @deleghePF
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica con delegato e la annulla, si verifica che sia visibile anche lato delegato
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    When Creo in background una delega per persona fisica
      | accessoCome | delegante        |
      | fiscalCode  | BRGLRZ80D58H501Q |
      | person      | true             |
      | displayName | Lucrezia Borgia  |
      | firstName   | Lucrezia         |
      | lastName    | Borgia           |
    And Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up OTP "corretto"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName | Gaio Giulio |
      | lastName  | Cesare      |
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
    And Aspetta 150 secondi
    And Si attende che lo stato della notifica sia "Consegnata"
    And Cliccare sulla notifica restituita
    And Si annulla la notifica
    Then Si controlla la comparsa del pop up di conferma annullamento
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Si clicca il bottone indietro nella descrizione della notifica
    And Aspetta 120 secondi
    
    #login nel portale persona fisica delegato e verifica della presenza della notifica mandata al delegante con lo IUN possibilmente
    #non mi serve mantenere la scheda del mittente aperta, perciò posso pensare di fare direttamente il login dal delegato e controllare la presenza della notifica
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    
    And Logout da portale mittente