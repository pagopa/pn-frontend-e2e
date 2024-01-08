Feature:il mittente cambia visualizzazione della pagina

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TestSuite
  @TA_MittentePaginazioneNotifiche
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-9223 - il mittente cambia visualizzazione della pagina
    When Nella pagina Piattaforma Notifiche si visualizzano le notifiche a partire dalla più recente
    And Nella pagina Piattaforma Notifiche si scrolla fino alla fine della pagina
    And Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate dieci notifiche
    And Nella pagina Piattaforma Notifiche si cambia pagina utilizzando una freccetta
    And Nella pagina Piattaforma Notifiche si cambia pagina utilizzando un numero
    Then Nella pagina Piattaforma Notifiche si cambia il numero elementi visualizzati attraverso il filtro
    And Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate tutte notifiche
    And Logout da portale mittente

