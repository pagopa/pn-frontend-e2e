Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneRecapitiVerificaCell_PG
  @addressBook2
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_31_32] Attivazione Recapiti Verifica CEll PG

   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti

#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
#    And Attesa 2 secondi
    And Verifica e Disattiva email
#    And Attesa 2 secondi
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi
###  REWORK_DOMICILIO_DIGITALE_PF_31
    When Click Bottone "Aggiungi un numero di cellulare"
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3409876543" e si clicca sul bottone avvisami via SMS
    And Si clicca sul bottone del pop-up Annulla
    And Verifica Pagina "Numero di cellulare"
    And Verifica Pagina "Recapiti"
###  REWORK_DOMICILIO_DIGITALE_PF_32
   And Aspetta 1 secondi
    When Click Bottone "Aggiungi un numero di cellulare"
    When Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3409876543" e si clicca sul bottone avvisami via SMS
    And Si clicca sul bottone del pop-up ok ho capito
    And Cliccare sul bottone Annulla
    And Verifica Pagina "Indirizzo email aziendale"
    And Verifica Pagina "Indirizzo email"

