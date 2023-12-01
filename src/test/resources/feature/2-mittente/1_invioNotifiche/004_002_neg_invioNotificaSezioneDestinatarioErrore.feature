Feature: il mittente inserisce i dati  sbagliati fino alla sezione Destinatario

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TA_inserimentoDatiErratiDestinatario
  @TestSuite
  @mittente
  @invioNotifiche
  Scenario: il mittente inserisce i dati sbagliati fino alla sezione Destinatario
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario selezionare il radio button persona giuridica
    And Nella section si prova ad cliccare sul tasto continua senza aver inserito nessun dato
    And Nella section Destinatario inserire i dati errati dalla persona giuridica "personaGiuridicaErrore"
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica "personaGiuridica"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire ragione sociale e partita IVA dalla persona giuridica "personaGiuridica_1"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario cliccare su Rimuovi destinatario
    And Nella section cliccare sul tasto torna a informazioni preliminari
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section cliccare sul tasto indietro
    And Nella section si visualizza il popup vuoi uscire
    And Nella section cliccare sul tasto esci
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Logout da portale mittente