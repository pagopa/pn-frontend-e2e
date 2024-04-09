Feature: Invio notifica digitale a destinatario con indirizzo mail di cortesia impostato

  @TA_invioNotificaConMailDiCortesiaImpostata
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario: PN-9244 - Invio notifica digitale a destinatario con indirizzo mail di cortesia impostato
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email o si inserisce "prova@test.it"
    And Logout da portale persona giuridica
    And Aspetta 15 secondi
    #Then In parallelo si effettua l'accesso al portale di "pubblica amministrazione"
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento RATA IMU |
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
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    And Aspetta 20 secondi
    And Cliccare sulla notifica restituita
    And Si clicca sul opzione Vedi Dettaglio
    And Nella timeline della notifica si visualizza l'invio del messaggio di cortesia
    And Logout da portale mittente