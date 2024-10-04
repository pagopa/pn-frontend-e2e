Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @InvioNotificaADueDestinatariEntrambiRaggiungibili
  Scenario: PN-9257 [TA-FE INVIO NOTIFICA A DUE DESTINATARI ENTRAMBI NON RAGGIUNGIBILI] - Il mittente invia una notifica a due destinatari , entrambi non raggiungibili al primo tentativo
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    And Logout da portale persona fisica
    And PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    And Logout da portale persona giuridica

    And PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
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
    And Si aggiungi un domicilio digitale "test@fail.it"
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | @fail-irreperibile_ar |
      | civico    | 20                    |
      | localita  | Milano                |
      | comune    | Milano                |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del destinatario persona giuridica aggiuntiva
      | soggettoGiuridico | PG           |
      | ragioneSociale    | Convivio Spa |
      | codiceFiscale     | 27957814470  |
      | pec               | mail@fail.it     |
      | indirizzo         | Via Roma         |
      | civico            | 20               |
      | localita          | Milano           |
      | comune            | Milano           |
      | provincia         | MI               |
      | cap               | 20147            |
      | stato             | Italia           |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    And Aspetta 10 secondi
    And Cliccare sulla notifica restituita
    And Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli
    And Aspetta 10 secondi
    And Si verifica che entrambi destinatari non raggiungibili al primo tentativo
      | PG | mail@fail.it |
      | PF | test@fail.it |
    And Aspetta 60 secondi
    Then Si verifica che il destinatario è raggiungibile al tentativo successivo "Invio via PEC riuscito"
    And Logout da portale mittente

