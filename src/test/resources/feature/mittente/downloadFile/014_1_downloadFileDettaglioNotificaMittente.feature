Feature: Mittente scarica tutti i file all'interno di una notifica


  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
   # And Nella pagina Piattaforma Notifiche accetta i Cookies
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
    And Cliccare sul bottone Filtra

  @TestSuite
  @test14_1

  Scenario: Mittente scarica attestazioni
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la sezione Dettaglio Notifica
    Then Nella sezione Dettaglio Notifica si scaricano tutti i file presenti
    And Logout da portale mittente