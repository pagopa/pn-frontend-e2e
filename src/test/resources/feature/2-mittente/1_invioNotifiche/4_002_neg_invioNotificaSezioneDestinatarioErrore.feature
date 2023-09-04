Feature: il mittente inserisce i dati  sbagliati fino alla sezione Destinatario
  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

    @fase2Test2
    @TestSuite

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
    And Nella section cliccare sul tasto torna a informazioni preliminari
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Logout da portale mittente