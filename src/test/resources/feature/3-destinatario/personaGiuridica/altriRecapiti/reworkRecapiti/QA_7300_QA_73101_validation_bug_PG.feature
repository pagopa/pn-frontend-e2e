Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_QA_7300_QA_73101_validation_bug_PG
#  @addressBook2
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_1

  Scenario:[QA_7300_QA_73101_validation_bug_PG]

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Click Bottone Inizia nel Banner
    And Click Annulla
    And Attesa 1 secondi
    And Seleziona Numero di pagine
    And La persona giuridica clicca sulla prima notifica restituita
    And Click Bottone Inizia nel Banner
    And Click Attiva
    And Attesa 2 secondi
    And Click Non ora
    And Attesa 1 secondi
    And Click Lo Faro piu tardi
    And Attesa 1 secondi
    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale



