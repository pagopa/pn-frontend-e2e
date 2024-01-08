Feature: la persona fisica visualizza correttamente la sezione altri recapiti
  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite token exchange "personaFisica"
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @TA_visualizzazioneSezioneAltriRecapitiPF
  @PF
  @recapitiPF

  Scenario: PN-9318-A40 - La persona fisica visualizza correttamente la sezione altri recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And si verifica esistenza due pec
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti
    And Logout da portale persona fisica