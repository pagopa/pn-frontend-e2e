Feature: Mittente invia una notifica digitale con domicilio speciale impostato al destinatario con KO e invio raccomandata semplice

  @TestSuite
  @TA_invioNotificaConDomicilioSpecialeEKO
  @mittente
  @invioNotifiche

  Scenario: PN-9248 - Mittente invia una notifica digitale con domicilio speciale impostato al destinatario con KO e invio raccomandata semplice
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
      | digitalAddress | prova@fail.it |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20       |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    And Aspetta 240 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    And Si verifica il tentato invio della notifica al domicilio speciale inserito "prova@fail.it"
    And Si verifica che l'invio della notifica sia fallito 2 volte
    And Si verifica l'invio della raccomandata semplice
    And Logout da portale mittente