Feature: la persona fisica visualizza correttamente la sezione altri recapiti

  #Disabled until PN-9318 is not fixed
  #@TestSuite
  #@TA_visualizzazioneSezioneAltriRecapitiPF
  #@PF
  #@recapitiPF

  Scenario: PN-9318-A40 - La persona fisica visualizza correttamente la sezione altri recapiti
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And si verifica esistenza due pec
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti
    And Logout da portale persona fisica