Feature: Utente helpdesk dopo il login effettua il logout

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test75


  Scenario: PN-9603 - Visualizzazione corretta pagina login dopo logout
    And Nella Home di helpdesk utente clicca su logout
    Then visualizzazione corretta pagina di login

