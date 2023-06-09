Feature: il mittente invia una notifica con 6 destinatario

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test81


  Scenario Outline: il mittente invia una notifica con 6 destinatario
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da destinatario "destinatario"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati del destinatario "destinatario"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario inserire i dati del destinatari aggiuntivi  per <numero destinatari>
    And Nella section Destinatario si cerca di aggiungere il sesto destinatario
    And Logout da portale mittente
    Examples:
      | numero destinatari |
      |        5           |