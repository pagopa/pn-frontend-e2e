Feature: La persona giuridica elimina il numero di cellulare di cortesia

  @TA_eliminaNumeroDiTelefonoPG
  @PG
  @recapitiPG

  Scenario: PN - 9160 la persona giuridica elimina il numero di telefono
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica

    #Step per eliminazione di un numero di cellulare già inserito

    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3334545899" e si clicca sul bottone avvisami via SMS
    And Si visualizza correttamente il pop-up e si clicca su conferma

    #In questo step viene fatta una chiamata per OTP delle mail, da cambiare quando avremo la chiamata per il cellulare
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    # manca la chiamata per prendere l'OTP per inserimento del cellulare
    #And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    #And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"

    And Nella pagina I Tuoi Recapiti si visualizza correttamente il numero di cellulare "3334545899"
    And Nella pagina I Tuoi Recapiti si preme sul bottone "Elimina" del numero di cellulare di cortesia
    And Si annulla eliminazione numero di cellulare
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il numero di cellulare "3334545899"
    And Nella pagina I Tuoi Recapiti si preme sul bottone "Elimina" del numero di cellulare di cortesia
    And Si conferma "Rimuovi cellulare" nel pop up
    Then Nella pagina I Tuoi Recapiti si controlla che il numero di cellulare non sia presente
    And Logout da portale persona giuridica
