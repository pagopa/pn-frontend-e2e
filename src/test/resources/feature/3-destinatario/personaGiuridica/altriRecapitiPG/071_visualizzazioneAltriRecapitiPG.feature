Feature: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti
  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "personaGiuridica"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TA_visualizzazioneSezioneAltriRecapitiPG
  @PG
  @recapitiPG

  Scenario: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti della persona giuridica
    And Logout da portale persona giuridica
