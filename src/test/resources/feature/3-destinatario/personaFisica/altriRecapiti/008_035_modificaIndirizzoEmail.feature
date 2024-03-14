Feature: la persona fisica modifica l'indirizzo Email

  @TestSuite
  @TA_modificaEmailPF
  @PF
  @recapitiPF

  Scenario: PN-9309 - la persona fisica modifica l'indirizzo Email
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Nella pagina I Tuoi Recapiti si inserisce l'email errata "provà&@gmail.com"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Nella pagina I Tuoi Recapiti si inserisce un email maggiore di 255 caratteri
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Nella pagina I Tuoi Recapiti si inserisce la nuova Email del PF "personaFisica" e clicca su salva
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della nuova Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email "personaFisica"
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata
    And Logout da portale persona fisica