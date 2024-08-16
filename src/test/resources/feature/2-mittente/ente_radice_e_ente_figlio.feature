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




