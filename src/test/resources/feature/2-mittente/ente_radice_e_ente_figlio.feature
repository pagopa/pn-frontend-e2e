Feature: Ente figlio e Ente radice

  @EnteRadiceEFiglio


  @test1
  Scenario: PN-10413 - Ente Figlio - Verifica assenza notifiche ente radice
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And ricerca notifica con IUN salvato
    Then Si verifica che non ci sono notifiche disponibili
    And Si clicca sul bottone esci
    And Si clicca sul bottone esci

  @test2
  Scenario: PN-10411 - Ente Radice - Verifica assenza notifiche ente figlio
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Si sceglie ente figlio "Comune di Viggiu"
    And ricerca notifica con IUN salvato
    Then Si verifica che non ci sono notifiche disponibili
    And Si clicca sul bottone esci
    And Si clicca sul bottone esci

  @test3
  Scenario: PN-10412 - Ente Radice - Verifica assenza apikey ente figlio
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
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
    And Si clicca sul bottone esci

  @test4
  Scenario: PN-10414 - Ente Figlio - Verifica assenza apikey ente radice
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
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
    And Si clicca sul bottone esci


  @test5
  Scenario: PN-10419 - Ente Figlio - Verifica presenza notifiche da parte del delegato
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
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
      | soggettoGiuridico       | PF                   |
      | nomeCognomeDestinatario | Gaio Giulio |
      | codiceFiscale           | CSRGGL44L13H501E     |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20                    |
      | localita  | Milano               |
      | comune    | Milano               |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Cliccare su continua
    And Si finalizza l'invio della notifica e si controlla che venga creata correttamente
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Si clicca sul bottone esci
    And Si clicca sul bottone esci
    Then PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza correttamente la pagina nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Viggiu   |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "nuova_delega"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up OTP "corretto"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName   | Gaio Giulio       |
      | lastName    | Cesare            |
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And ricerca notifica con IUN salvato
    And Si seleziona la notifica
    And Logout da portale persona fisica


