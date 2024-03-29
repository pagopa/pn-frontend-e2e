Feature: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti

  @TestSuite
  @TA_visualizzazioneSezioneAltriRecapitiPG
  @PG
  @recapitiPG

  Scenario: PN-9162 - La persona giuridica visualizza tutti gli elementi della sezione altri recapiti
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    Then Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti della persona giuridica
    And Logout da portale persona giuridica
