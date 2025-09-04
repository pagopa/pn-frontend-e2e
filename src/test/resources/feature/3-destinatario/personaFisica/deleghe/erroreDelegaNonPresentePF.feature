Feature: Il delegato visualizza la notifiche del delegante
  
  @TestSuite
  @TA_PFErroreDelegaNonPresente
  @DeleghePF
  @deleghe1
  @PF
  @DeleghePFPG
  @DeleghePFPG1
  @GestioneErrori
     @NRT_Blocco_3
  Scenario: [PN-14926-MANDATE_NOTFOUND_PF] - Errore per operazioni su una delega che non esiste
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega PF
    And Si visualizza correttamente la pagina nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia          |
      | cognome       | Borgia            |
      | codiceFiscale | BRGLRZ80D58H501Q  |
      | ente          | Comune di Verona |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "PF"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe

    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si sceglie opzione accetta delega a tuo carico da "Gaio Giulio Cesare"
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName | Gaio Giulio |
      | lastName  | Cesare      |

    And In parallelo si effettua l'accesso al portale destinatario persona fisica come delegante
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    Then Si controlla che non ci sia più una delega e si chiude la pagina in parallelo

    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Gaio Giulio |
      | cognome | Cesare   |
    And Nella pagina Deleghe si sceglie opzione rifiuta
    And Si clicca sul bottone rifiuta all'interno del pop-up
    And Verifica Messaggio toast di errore "Delega non trovata"
    And Refresh pagina
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Gaio Giulio            |
      | cognome       | Cesare                 |