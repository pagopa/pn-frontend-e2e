Feature: Rework della pagina dei contatti

#  @TestSuite
  @TA_attivazioneDomicilioSEND_TastoEsci_PG
  @addressBook2
  @TA_REWORK_RECAPITI_ON

  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_8_78] Attivazione Domicilio Digitale SEND - ESCI  PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone Esci PG
    Then Verifica Pagina "Da attivare"
    And Verifica Pagina "Indirizzo email aziendale"

#REWORK_DOMICILIO_DIGITALE_PG_78
    And Verifica e Disattiva domicilio digitale
    When Click Inizia
    And Verifica Pagina "Come funziona"
    And Click Attiva
    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Click Bottone Esci PG
    And Verifica Pagina "Non rischiare di leggere in ritardo le tue notifiche"
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti