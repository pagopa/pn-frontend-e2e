Feature: il delegato accetta la delega

  Background: Login destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login "delegato" portale destinatario tramite request method
    Then pagina Piattaforma  Notifiche Destinatario viene visualizzata correttamente

  @TestSuite
  @test48
  @restLogin

  Scenario: il delegato accetta la delega
    When Nella pagina Piattaforma Notifiche Destinatario click sul bottone Deleghe
    And Si verifica sia presente una delega nella sezione Deleghe a Tuo Carico
    And si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega a lo stato Attiva
    And Logout da portale destinatario