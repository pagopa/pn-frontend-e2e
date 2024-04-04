Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @TA_invioNotificaDueDestinatari
  @mittente
  @invioNotifiche

  Scenario: PN-9255 -  Invio notifica a due destinatari
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
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del destinatari persona giuridicha aggiuntiva
      | soggettoGiuridico | PG           |
      | ragioneSociale    | Convivio Spa |
      | codiceFiscale     | 27957814470  |
      | pecInvalid        | test@fail.it |
      | indirizzo         | Via Roma     |
      | civico            | 20           |
      | localita          | Milano       |
      | comune            | Milano       |
      | provincia         | MI           |
      | cap               | 20147        |
      | stato             | Italia       |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    Then In parallelo si effettua l'accesso al portale destinatario persona fisica e si apre la notifica ricevuta
    And Cliccare sulla notifica restituita
    And Si verifica che la notifica abbia lo stato "Invio in corso"
    Then In parallelo si effettua l'accesso al portale destinatario persona giuridica e si apre la notifica ricevuta
    And Cliccare sulla notifica restituita
    Then Si verifica che la notifica abbia lo stato "Invio in corso"
    And Logout da portale mittente