Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_attivazioneDomicilioSEND_DoppiaConferma_PG
#  @addressBook2
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_1

  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_7_9] Attivazione Domicilio Digitale SEND PG - Doppia conferma

   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Attiva
    And Attesa 2 secondi
    And Click Non ora

    And Verifica Pagina "Non rischiare di leggere in ritardo le tue notifiche"
    And Verifica Pagina "Senza un indirizzo email o un altro recapito non possiamo informarti quando ricevi una comunicazione"
    And Click Inserisci Email Pop-Up
    When Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Verifica Pagina "Indirizzo email"
    And Click Non ora
    And Click Lo Faro piu tardi
    Then Verifica Pagina "Hai attivato il tuo domicilio digitale"
    And Verifica Pagina "Vai ai tuoi recapiti"
