Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_TastoEsci_PF
  @addressBook1
  @TA_NRT_UAT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_8_77_78] Attivazione Domicilio Digitale SEND - ESCI  PF

   #    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica input
      | user         | pluto-ta               |
      | pwd          | password123            |
      | name         | Rossi                  |
      | familyName   | Pluto                  |
      | fiscalNumber | TINIT-AAAAAA00A00A000B |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva "domicilio digitale"
    And Aspetta 2 secondi
    And Verifica e Disattiva "app IO"
    And Verifica e Disattiva "email"
    And Verifica e Disattiva "cellulare"

    When Click Inizia
    And Click Bottone "Esci"
    Then Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Pagina "app IO"
    And Verifica Pagina "Il tuo indirizzo email"
# REWORK_DOMICILIO_DIGITALE_PF_77-78
    And Verifica e Disattiva "domicilio digitale"
    And Click Inizia
    And Verifica Pagina "Come funziona"
    And Click Attiva
    And Si clicca su 'Collega SEND su IO'
    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Click Bottone "Esci"
    #And Verifica Pagina "Non rischiare di leggere in ritardo le tue notifiche"
    #And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
## Reset recapiti UAT
    And Verifica e Disattiva "domicilio digitale"
    And Aspetta 2 secondi
    And Verifica e Disattiva "app IO"