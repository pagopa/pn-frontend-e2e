Feature: persona giuridica scarica attestazione opponibile

  @TestSuite
  @TA_PG_DownloadFileAOTPresaInCarico
  @DownloadFilePG
  @PG

  Scenario: PN-10432 - Persona giuridica scarica Attestazione opponibile a terzi: notifica presa in carico
    Given Login Page mittente "mittente_1" viene visualizzata
    When Login con mittente "mittente_1"
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
    And Aspetta 60 secondi
    And Cliccare sulla notifica restituita
    And Nella sezione Dettaglio Notifiche PG si seleziona il file, "Attestazione opponibile a terzi: notifica presa in carico", da scaricare
    Then Si controlla il testo all interno del file destinatario "Attestazione_opponibile_a_terzi_notifica_presa_in_carico"
    And Logout da portale persona giuridica