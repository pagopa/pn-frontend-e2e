Feature:il mittente cambia visualizzazione della pagina

  @TestSuite
  @TA_MittentePaginazioneNotifiche
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-9223 - il mittente cambia visualizzazione della pagina
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche si visualizzano le notifiche a partire dalla più recente
    And Nella pagina Piattaforma Notifiche si scrolla fino alla fine della pagina
    And Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate dieci notifiche
    And Nella pagina Piattaforma Notifiche si cambia pagina utilizzando una freccetta
    And Nella pagina Piattaforma Notifiche si cambia pagina utilizzando un numero
    Then Nella pagina Piattaforma Notifiche si cambia il numero elementi visualizzati attraverso il filtro
    And Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate tutte notifiche
    And Si clicca sulla pagina numero 3 delle notifiche
    When Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "personaFisica"
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con il codice fiscale del destinatario "personaFisica"
    And Logout da portale mittente

