Feature: Mittente visualizza il dettaglio di una notifica

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche si recupera un codice IUN valido
    And Cliccare sul bottone Filtra

  @TestSuite
  @TA_MittenteDettaglioNotifiche
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-9225 - Mittente visualizza dettaglio notifica
    When Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Nella pagina dettaglio notifica cliccare sull'opzione vedi più dettagli
    And Si visualizza correttamente l elenco completo degli stati che la notifica ha percorso
    Then Si clicca sul bottone indietro
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Logout da portale mittente
