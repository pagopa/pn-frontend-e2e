  Feature: La persona giuridica modifica l'email di cortesia

#  @TestSuite_VECCHI
#  @TA_modificaEmailPG
#  @PG
#  @recapitiPG
#  @mittente_x1
#  @addressBook2
#  @recapitiPFPG
    #  TEST PREVISTI in rework-sezione-recapiti-fase-2 -> PN-9157 - La persona giuridica elimina l'indirizzo email
  Scenario: PN - 9156 la persona giuridica modifica l'email di cortesia
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una "email di cortesia" e si inserisce "prova@test.it"
    And Nella pagina I Tuoi Recapiti si preme sul bottone "Modifica" dell'email di cortesia
    Then Si visualizza il campo email modificabile
    And Nella pagina I Tuoi Recapiti si preme sul bottone "Annulla" dell'email di cortesia
    And Si controlla presenza email precedentemente salvata "prova@test.it"
    And Nella pagina I Tuoi Recapiti si preme sul bottone "Modifica" dell'email di cortesia
    And Nella pagina I Tuoi Recapiti si inserisce l'email errata "provà&@gmail.com"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Nella pagina I Tuoi Recapiti si inserisce un email maggiore di 255 caratteri
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Nella pagina I Tuoi Recapiti si inserisce la nuova email "provaemail@test.it"
    And Nella pagina I Tuoi Recapiti si preme sul bottone "Salva" dell'email di cortesia
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request dell'email "provaemail@test.it" e viene inserito
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata