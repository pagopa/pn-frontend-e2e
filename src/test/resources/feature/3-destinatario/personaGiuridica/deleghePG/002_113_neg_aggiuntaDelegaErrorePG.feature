Feature:La persona giuridica aggiunge una nuova delga inserendo una data errata

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "personaGiuridica"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TestSuite
  @TA_PGNuovaDelegaDataErrata
  @DeleghePG
  @PG
  Scenario: Il persona giuridica aggiunge una nuova delga inserendo una data errata
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Si visualizza la sezione Aggiungi Delega persona giuridica
    And Nella sezione Le Tue Deleghe inserire una data con formato errato e andecedente alla data
    And Nella sezione Le Tue Deleghe si visualizza il messaggio di errore data errata
    And Logout da portale persona giuridica