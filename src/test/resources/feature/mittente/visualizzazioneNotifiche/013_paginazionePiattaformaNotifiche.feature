Feature:il mittente cambia visualizzazione della pagina3

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test13
  Scenario Outline:
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche si visualizzano le notifiche a partire dalla più recente
    And Nella pagina Piattaforma Notifiche si scrolla fino alla fine della pagina
    And Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate 10 notifiche
    And Nella pagina Piattaforma Notifiche si cambia pagina utilizzando una freccetta
    And Nella pagina Piattaforma Notifiche si cambia pagina utilizzando un numero
    Then Nella pagina Piattaforma Notifiche  si cambia il numero elementi visualizzati attraverso il filtro <numero notifiche>
    And Nella pagina Piattaforma Notifiche si controlla che vengano visualizzate tutte notifiche <numero notifiche>
    And Logout da portale mittente
    Examples:
      |numero notifiche  |
      |     20           |
