Feature: Il destinatario visualizza le notifiche in elenco

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login portale destinatario tramite request method
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

    @TestSuite
    @test16
    @restLogin


    Scenario: Il destinatario visualizza le notifiche in elenco
      When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
      Then Si visualizzano correttamente le notifiche in elenco paginato
      And Si visualizzano le notifiche dalla piu recente
      And Si aggiorna la paginazione utilizzando le frecce
      And Si visualizza correttamente una pagina diversa dalla precedente
      And Ci si posiziona su una pagina differente attraverso i numeri
      And Si visualizza correttamente una pagina diversa dalla precedente
      And Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default
      And Ci si posiziona su una pagina differente da quella di default e si aumenta il numero di modifiche visualizzate
      And Si visualizza un numero di pagine visualizzate uguale a quello selezionato
      And Logout da portale destinatario

