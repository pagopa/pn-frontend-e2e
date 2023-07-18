Feature: Il mittente inserisce i dati non coretti nella sezione informazioni preliminari

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @fase2Test1neg

  Scenario:Il mittente inserisce i dati non corretti nella sezione informazioni preliminari
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari si prova ad cliccare sul tasto continua senza aver inserito nessun dato
    And Nella section Informazioni preliminari inserire i dati della notifica sbagliati "datiNotifica" senza pagamento
    And Nella section Informazioni preliminari cliccare sul tasto Torna alle Notifiche
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Logout da portale mittente