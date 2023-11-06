Feature: utente helpdesk dopo il login effettua il logout

  Background: Login utente in helpdesk
    Given Login helpdesk con utente test "testHelpdesk"
    And Si visualizza correttamente home Helpdesk

  @TestSuite
  @test75


  Scenario: visualizzazione corretta pagina login dopo logout
    When Nella Home di helpdesk utente clicca su logout
    And visualizzazione corretta pagina di login

