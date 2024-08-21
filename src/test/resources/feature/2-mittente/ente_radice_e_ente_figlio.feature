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
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Cliccare sulla notifica restituita
    And Salva codice IUN
    And Si sceglie ente figlio "EDILIZIA PRIVATA E SUAP"
    And ricerca notifica con IUN salvato
    Then Si verifica che non ci sono notifiche disponibili
    And Si clicca sul bottone esci
    And Si clicca sul bottone esci

  @verificaAssenzaNotificheEnteFiglio
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
    And Logout da portale mittente

  @verificaAssenzaApikeyEnteFiglio
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
    And Logout da portale mittente

  @verificaAssenzaApikeyEnteRadice
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
    And Logout da portale mittente



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
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Aspetta 120 secondi
    And Cliccare sulla notifica restituita
    And Aspetta 5 secondi
    And Verifica nome ente mittente "Comune di Viggiu - EDILIZIA PRIVATA E SUAP"
    And Si clicca sul bottone esci
    And Logout da portale mittente





