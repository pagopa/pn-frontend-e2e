Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_attivazioneDomicilioSEND_DoppiaConferma_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_7_9] Attivazione Domicilio Digitale SEND PF - Doppia conferma

   #    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Attesa 1 secondi
    And Verifica testo nella pagina "Non rischiare di leggere in ritardo le tue notifiche"
    And Verifica testo nella pagina "Senza un indirizzo email o un altro recapito non possiamo informarti quando ricevi una comunicazione"
    And Click Inserisci Email Pop-Up
    And Verifica testo nella pagina "La tua mail per ricevere aggiornamenti"
    And Verifica testo nella pagina "Indirizzo email"
    And Click Non ora
    And Click Lo Faro piu tardi
    Then Verifica testo nella pagina "Hai attivato il tuo domicilio digitale"
    And Verifica testo nella pagina "Vai ai tuoi recapiti"
