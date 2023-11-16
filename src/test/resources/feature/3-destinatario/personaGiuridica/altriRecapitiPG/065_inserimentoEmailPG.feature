Feature: La persona giuridica inserisce l'email

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TA_inserimentoEmailPG
  @personaGiuridicaDestinatario
  @recapitiPG

  Scenario: La persona giuridica inserisce l'email
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Nella pagina I Tuoi Recapiti si inserisce l'email del PF "personaGiuridica" e clicca sul bottone avvisami via email
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email "personaGiuridica"
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia presente
    And Logout da portale persona giuridica
