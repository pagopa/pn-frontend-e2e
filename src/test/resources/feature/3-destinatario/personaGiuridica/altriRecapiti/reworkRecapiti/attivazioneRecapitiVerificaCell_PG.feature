Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciEmailCell_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_31_31] Attivazione Domicilio Digitale SEND - Inserimento mail e cellulare PG

   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Attesa 2 secondi
    And Verifica e Disattiva "email"
    And Attesa 2 secondi
    And Verifica e Disattiva "cellulare"


###  REWORK_DOMICILIO_DIGITALE_PF_31
    When Click Bottone "Aggiungi un numero di cellulare"
#
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "+393409876543" e si clicca sul bottone avvisami via SMS



#    And Click Annulla
#    Then Verifica Da Attivare Domicilio digitale
