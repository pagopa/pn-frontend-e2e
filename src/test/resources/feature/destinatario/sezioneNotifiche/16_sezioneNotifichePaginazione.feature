Feature: Il destinatario visualizza le notifiche in elenco

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

    Scenario: Il destinatario visualizza le notifiche in elenco
      When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
      Then Si visualizzano correttamente le notifiche in elenco paginato
      And Si aggiorna la paginazione utilizzando le frecce
      And Si modifica il numero di notifiche visualizzate scegliendo un valore diverso da quello di default
      And Ci si posiziona su una pagina differente da quella di default e si diminuisce il numero di modifiche visualizzate
      And And Logout da portale destinatario
