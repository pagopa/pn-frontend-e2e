Feature: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti


  @TA_visualizzazioneSezioneAltriRecapitiPG
  @PG


  Scenario: PN-9162 - La persona giuridica visualizza tutti gli elementi della sezione altri recapiti
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce l'indirizzo della PEC "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request dell'email "pec@pec.pagopa.it" e viene inserito
    Then Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
    And Nella sezione altri recapiti si inserisce un recapito
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti
    And Logout da portale persona giuridica