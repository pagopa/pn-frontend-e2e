Feature: il mittente inserisce tutti i dati di una notifica
  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

    @fase2Test3
      @TestSuite
      @pg
  Scenario Outline: il mittente inserisce tutti i dati di una notifica
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario selezionare il radio button persona giuridica
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del destinatari persone giuridiche aggiuntivi per <numero destinatari>
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaGiuridica"
    And Nella pagina Piattaforma Notifiche inserire la data invio notifica
    And Nella pagina piattaforma Notifiche selezionare lo stato notifica Depositata
    And Cliccare sul bottone Filtra
    And Verifica dello stato della notifica persona giuridica come depositata "Depositata"
    And Logout da portale mittente
    Examples:
      | numero destinatari |
      |         2          |