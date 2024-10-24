Feature: La persona giuridica modifica l'indirizzo PEC

  @TestSuite
  @TA_modificaPECPG
  @PG
  @recapitiPG

  Scenario: PN-9153 - La persona giuridica modifica l'indirizzo PEC
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti di PG, si controlla che ci sia già una pec
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Si clicca sul bottone annulla per annullare la modifica della PEC e si verifica che non si possa modificare la PEC
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone salva
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    Then Nella pagina I Tuoi Recapiti si verifica che la pec sia stata modificata "prova@pec.it"
    And Logout da portale persona giuridica

