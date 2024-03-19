Feature: La persona giuridica visualizza gli utenti

  @TestSuite
  @TA_PGUtenti
  @VisualizzaUtentiPG
  @PG

  Scenario: PN-9178 - La persona giuridica visualizza la sezione utenti
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella Pagina Notifiche persona giuridica si clicca su utenti
    And Nella pagina utenti si effettua la login tramite credenziali
      | user | DanteAlighieri |
      | pwd  | test           |
    And Si visualizza correttamente la pagina utenti
    And Si clicca sul bottone aggiungi utente
    And Si visualizza correttamente la pagina aggiungi nuovo utente
    And Si inserisce i dati personali
      | name          | Ada              |
      | familyName    | Lovelace         |
      | codiceFiscale | LVLDAA85T50G702B |
      | email         | ada@test.it      |
    And Si clicca su seleziona il prodotto "SEND - Notifiche Digitali CERT"
    And Si clicca su opzione amministratore e clicca bottone continua
    And Si clicca sul bottone annulla nel popup
    And Si visualizza correttamente la pagina aggiungi nuovo utente
    And Si clicca sul bottone continua e assegna e si visualizza messaggio di successo
    And Si visualizza correttamente la pagina riepilogativa
    And Nella Pagina riepilogativa si clicca su utenti
    And Si apre dettaglio dell utente appena creato "Ada"
    And Si visualizza correttamente la pagina riepilogativa
    And Nella pagina riepilogativa utenti si clicca sul bottone modifica
    And Si modifica il campo email e conferma email "adanew@test.it"
    And Nella pagina riepilogativa utenti si clicca sul bottone conferma
    And Si visualizza correttamente la pagina riepilogativa
    And Si visualizza correttamente nuovo email "adanew@test.it"
    And Nella pagina riepilogativa utenti si clicca sul bottone rimuovi
    And Nella pagina riepilogativa utenti si clicca sul bottone annula
    And Si visualizza correttamente la pagina riepilogativa
    And Nella pagina riepilogativa utenti si clicca sul bottone rimuovi
    Then Si clicca sul bottone rimuovi dell popup
    And  Logout da portale persona giuridica