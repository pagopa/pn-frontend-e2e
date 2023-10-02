Feature: Mittente effetua una ricerca notifiche per Stato

  Background: login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test11


  Scenario Outline: Mittente logato effettua una ricerca per stato notifica
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    #And Nella pagina Piattaforma Notifiche accetta i Cookies
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    Then Nella pagina Piattaforma Notifiche vengo restituite tutte le notifiche con lo stato della notifica <stato>
    And Nella pagina Piattaforma Notifiche i risultati sono contenuti in una o più pagine
    And Logout da portale mittente

    Examples:
      |   stato        |
      |Avvenuto accesso|
    # ci sono altri stati come annullata, invio in corso, consegnata ecc.