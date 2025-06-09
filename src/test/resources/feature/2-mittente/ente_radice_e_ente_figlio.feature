Feature: Ente figlio e Ente radice

  @EnteRadiceEFiglio
  @verificaAssenzaNotificheEnteRadice
  Scenario: PN-10413 - Ente Figlio - Verifica assenza notifiche ente radice
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Click entra su Send Mittente
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And Mittente ricerca notifica con IUN salvato
    Then Si verifica che non ci sono notifiche disponibili

  @verificaAssenzaNotificheEnteFiglio
  Scenario: PN-10411 - Ente Radice - Verifica assenza notifiche ente figlio
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Si sceglie ente figlio "Comune di Viggiu"
    And Mittente ricerca notifica con IUN salvato
    Then Si verifica che non ci sono notifiche disponibili
    And Si clicca sul bottone esci

  @verificaAssenzaApikeyEnteFiglio
  Scenario: PN-10412 - Ente Radice - Verifica assenza apikey ente figlio
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    And Salva Api key
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Si sceglie ente figlio "Comune di Viggiu"
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    And Si verifica che Api Key sono diversi
    Then Nel pop up visualizza cliccare sul tasto chiudi
    And Si clicca sul bottone esci

  @verificaAssenzaApikeyEnteRadice
  Scenario: PN-10414 - Ente Figlio - Verifica assenza apikey ente radice
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    And Salva Api key
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And Nella pagina Piattaforma Notifiche selezionare la voce Api Key nel menu
    And Si visualizza correttamente la pagina Api Key
    When Nella pagina Api Key si clicca sul bottone menu di una Api Key attiva presente in elenco
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    And Si verifica che Api Key sono diversi
    Then Nel pop up visualizza cliccare sul tasto chiudi
    And Si clicca sul bottone esci


  @EnteRadiceEFiglio
  @verificaPresenzaNotificheFiglioDaDelegato
  Scenario: [PN-10419_QA-132] - Ente Figlio - Verifica presenza notifiche da parte del delegato
    #Creazione delega
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Viggiu    |
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma
#    And Logout da portale persona fisica
    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe
    And Si sceglie opzione accetta
    And  Si clicca sul bottone indietro popup
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
#    And Logout da portale persona fisica
    #Esecuzione scenario
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And Click entra su Send Mittente
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica senza gruppo
      | oggettoNotifica   | Pagamento RATA IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | codiceTassonomico | 100105P            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF                   |
      | nomeCognomeDestinatario | Gaio Giulio Cesare   |
      | codiceFiscale           | CSRGGL44L13H501E     |
    And Nella section Destinitario si clicca su "Aggiungi un domicilio digitale" e si inseriscono i dati
      | digitalAddress | test@pec.com |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20                    |
      | localita  | Milano               |
      | comune    | Milano               |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Aspetta 300 secondi
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Aspetta 10 secondi
    And Verifica nome ente mittente "Comune di Viggiu - EDILIZIA PRIVATA E SUAP"
    # Login come Lucrezia Borgia (deve avere delega di Cesare per ente radice comune di Viggiu)
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di "(gaio giulio cesare)"
    And Entro dentro la prima notifica
    # Download allegati
    And Si attende completamento notifica "Consegnata"
    And Aspetta 20 secondi
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica digitale")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Ricevuta di consegna PEC")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[@id='document-button' and .//div[contains(text(),'Avviso di avvenuta ricezione')]]  |
      | vediDettagli | false                                          |
#    And Logout da portale persona fisica
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Destinatario ricerca notifica con IUN salvato
    And Cliccare sulla notifica restituita
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica digitale")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Ricevuta di consegna PEC")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[@id='document-button' and .//div[contains(text(),'Avviso di avvenuta ricezione')]]  |
      | vediDettagli | false |
    # Rimozione delega
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega

  @EnteRadiceEFiglio
  @verificaPresenzaNotificheRadiceDaDelegato
  Scenario: [QA-1081_QA-133] - Ente Radice - Verifica presenza notifiche da parte del delegato
    #Creazione delega
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Viggiu    |
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma
#    And Logout da portale persona fisica
    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe
    And Si sceglie opzione accetta
    And  Si clicca sul bottone indietro popup
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
#    And Logout da portale persona fisica
    #Esecuzione scenario
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Click entra su Send Mittente
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica senza gruppo
      | oggettoNotifica   | Pagamento RATA IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | codiceTassonomico | 100105P            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF                   |
      | nomeCognomeDestinatario | Gaio Giulio Cesare   |
      | codiceFiscale           | CSRGGL44L13H501E     |
    And Nella section Destinitario si clicca su "Aggiungi un domicilio digitale" e si inseriscono i dati
      | digitalAddress | test@pec.com |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20                    |
      | localita  | Milano               |
      | comune    | Milano               |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Aspetta 300 secondi
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Aspetta 10 secondi
    And Verifica nome ente mittente "Comune di Viggiu"
    # Login come Lucrezia Borgia (deve avere delega di Cesare per ente radice comune di Viggiu)
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di "(gaio giulio cesare)"
    And Entro dentro la prima notifica
    # Download allegati
    And Si attende completamento notifica "Consegnata"
    And Aspetta 20 secondi
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica digitale")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Ricevuta di consegna PEC")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[@id='document-button' and .//div[contains(text(),'Avviso di avvenuta ricezione')]]  |
      | vediDettagli | false                                          |
    And Logout da portale persona fisica
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Destinatario ricerca notifica con IUN salvato
    And Cliccare sulla notifica restituita
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica digitale")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Ricevuta di consegna PEC")] |
      | vediDettagli | false                                          |
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[@id='document-button' and .//div[contains(text(),'Avviso di avvenuta ricezione')]]  |
      | vediDettagli | false |
    # Rimozione delega
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    And Logout da portale persona fisica
