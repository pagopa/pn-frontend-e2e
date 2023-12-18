Feature: La persona giuridica elimina l'indirizzo email

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "personaGiuridica"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TA_eliminaEmailPG
  @PG
  @recapitiPG

  Scenario: La persona giuridica elimina l'indirizzo email
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email
    And Nella pagina I Tuoi Recapiti si clicca sul bottone elimina email e si conferma nel pop up
    Then Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non sia presente
    And Logout da portale persona fisica
