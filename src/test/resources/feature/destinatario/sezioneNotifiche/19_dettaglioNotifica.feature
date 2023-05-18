Feature: Il destinatario visualizza una delle notifiche in elenco

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

Scenario:
  When Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
  And Nella pagina Piattaforma Notifiche si visualizzano correttamente le notifiche in elenco paginato
  And Nella pagina Piattaforma Notifiche si seleziona una delle notifiche presenti in elenco
  And Nella pagina dettaglio notifica cliccare sul link dei documenti per salvarli in locale
  And Nella pagina dettaglio notifica cliccare sull opzione vedi dettaglio
  Then Nella pagina dettaglio notifica si visualizza correttamente l elenco degli stati che la notifica ha percorso
  And Nella pagina dettaglio notifica cliccare l opzione indietro
  And Si visualizza correttamente la pagina Piattaforma Notifiche Destinatario
  And And Logout da portale destinatario