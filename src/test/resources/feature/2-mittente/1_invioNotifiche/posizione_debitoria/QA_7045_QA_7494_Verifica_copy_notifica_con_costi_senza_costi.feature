Feature: Avviso PagoPa - inviaare una notifica multi destinatario a PF e PG contenente più avvisi PagoPa e più modelli F24

  @TestSuite
  @TA_QA_7045_QA_7494
#  @TA_PosizioneDebitoria_ON
  @NRT
  Scenario: [QA_7045_QA_7494] Verifica_copy_notifica_con_costi_senza_costi
    Given  PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "UNRQ-AEAX-QXLA-202505-K-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 1
    And Cliccare su Come Mai
#    And Verifica Pagina Come Mai
    And Attesa 10 secondi


#    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "JVDU-KLJG-RPZV-202405-X-1"
#        And Cliccare sul bottone Filtra Notifica "filter-notifications-button"




#    And Aspetta 5 secondi
#    And Verifica Esistenza Tabella Notifiche
##    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
#    And Cliccare sul bottone Filtra Notifica "filter-notifications-button"
#    And Cliccare sulla notifica restituita dal filtro
#    And Aspetta 1 secondi
#    And Verifica Sezione Pagamenti

#    Then PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
#    And Verifica Esistenza Tabella Notifiche
##    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
#    And Cliccare sul bottone Filtra Notifica "filter-notifications-button"
#    And Cliccare sulla notifica restituita dal filtro
#    And Aspetta 1 secondi
#    And Verifica Sezione Pagamenti