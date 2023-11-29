Feature: Il mittente deve creare una nuova notifica con eventualmente dei dati inseriti,
  per poi tornare indietro alla lista delle notifiche e cliccare nuovamente su "Crea notifica".

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
      When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TA_inserimentoDatiInfoPreliminari
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario:Il mittente inserisce i dati nella sezione informazioni preliminari
      When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Nella section cliccare sul tasto indietro
    And Nella section si visualizza il popup vuoi uscire
    And Nella section cliccare sul tasto esci
      Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari