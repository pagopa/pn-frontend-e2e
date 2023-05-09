Feature: Mittente effetua una ricerca notifiche per Stato

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
    @firstCommitRun
  @test11
  Scenario Outline: Mittente logato effettua una ricerca per stato notifica
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche DEV
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con lo stato della notifica <stato>
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente

    Examples:
      |   stato       |
      |Invio in corso|
    # ci sono altri stati come annullata, invio in corso, consegnata ecc.