Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_CollegamentoSENDaIOTastoEsci_PF
  @addressBook1
  @TA_UAT_ON
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_77] Attivazione Domicilio Digitale SEND - Collegamento SEND a IO ESCI PF

   #    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone Esci PF
    Then Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Pagina "app IO"
    And Verifica Pagina "Il tuo indirizzo email"
# REWORK_DOMICILIO_DIGITALE_PF_77
    And Verifica e Disattiva domicilio digitale
    And Click Inizia
    And Verifica Pagina "Come funziona"
    And Click Attiva
    And Si clicca su 'Collega SEND su IO'
    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Click Bottone Esci PF
    #And Verifica Pagina "Non rischiare di leggere in ritardo le tue notifiche"
    #And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
## Reset recapiti UAT
    And Verifica e Disattiva domicilio digitale
    And Aspetta 2 secondi
    And Verifica e Disattiva app IO