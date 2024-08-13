Feature: persona giuridica scarica attestazione opponibile

  @TestSuite
  @TA_PG_DownloadFileAOTPresaInCarico
  @DownloadFilePG
  @PG

  Scenario: PN-10432 - Persona giuridica scarica Attestazione opponibile a terzi: notifica presa in carico
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    Then Home page mittente viene visualizzata correttamente
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica senza gruppo
      | oggettoNotifica   | Pagamento RATA IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PG           |
      | nomeCognomeDestinatario | Convivio Spa |
      | codiceFiscale           | 27957814470  |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20       |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |
    And Cliccare su continua
    And Si clicca sul bottone esci
    And Si clicca sul bottone esci
    And PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si clicca bottone accetta cookies
    And Aspetta 120 secondi
    And Cliccare sulla notifica restituita
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                          |
    And Logout da portale persona giuridica