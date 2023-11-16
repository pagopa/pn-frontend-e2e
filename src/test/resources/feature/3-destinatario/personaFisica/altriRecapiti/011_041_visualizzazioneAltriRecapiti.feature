Feature: la persona fisica visualizza correttamente la sezione altri recapiti
  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TA_visualizzazioneSezioneAltriRecapitiPF
  @personaFisicaDestinatario
  @recapitiPF

  Scenario: La persona fisica visualizza correttamente la sezione altri recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti
    And Logout da portale persona fisica