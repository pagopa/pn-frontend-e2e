Feature: il mittente inserisce tutti i dati di una notifica
  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

    @fase2Test3neg
    @provaRun

  Scenario: il mittente inserisce tutti i dati di una notifica
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica "personaGiuridca"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona giuridica "personaGiuridica"
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica" con estenzione non valida
    Then Si visualizza correttamente il messaggio di errore estensione file non supportata. Riprovare con un altro file.
    And Logout da portale mittente
