Feature: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti


#  @TA_altriRecapitiPGVisualizzaCorrettamente
# NON replicabile perche il test prevede eliminazione del cell, ma per poter eliminareil cell dovremmo prima inserirlo ad oggi  il test non è implementabile perchè non abbiamo la possibilita di recupero OTP
  Scenario: PN-9161 -A1 La persona giuridica visualizza tutti gli elementi della sezione altri recapiti
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti di PG, si controlla che ci sia già una pec
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti persona giuridica "pec"
    And Nella sezione altri recapiti PG si seleziona l'ente "Agenzia delle Entrate"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo PG scegliendo "pec"
    Then Nella sezione altri recapiti si inserisce la PEC aggiuntiva "prova@pec.it"