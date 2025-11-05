Feature: Verifica_copy_notifica_con_costi_senza_costi PF

  @TestSuite
  @TA_QA_7045_QA_7494
  @TA_VerificaCopy
  @NRT_Blocco_1
  Scenario: [QA_7045_QA_7494_PF] Verifica_copy_notifica_con_costi_senza_costi
    Given  PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
##  1 Avviso pago Pa con costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "UNRQ-AEAX-QXLA-202505-K-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 1 IUN "UNRQ-AEAX-QXLA-202505-K-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  1 Modello F24 con costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "YNXM-DLDK-AHLW-202511-X-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 1 IUN "YNXM-DLDK-AHLW-202511-X-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  2 Avviso pago Pa con costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "WPER-NDWL-UQGV-202505-E-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 2 IUN "WPER-NDWL-UQGV-202505-E-1"
    And Verifica testo "In questa notifica ci sono più avvisi di pagamento: seleziona quello che vuoi pagare. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  2 Modello F24 con costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "KEDH-ZLNJ-ZEVQ-202511-G-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 2 IUN "KEDH-ZLNJ-ZEVQ-202511-G-1"
    And Verifica testo "Puoi pagare questa notifica tramite F24. Per vedere i dettagli, apri i modelli. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  Ibrido con costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "UNRY-GHDM-ZMEL-202505-Q-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 1 IUN "UNRY-GHDM-ZMEL-202505-Q-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  1 Avviso pago Pa senza costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "GTZV-GNDM-LAUP-202505-M-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "GTZV-GNDM-LAUP-202505-M-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  1 Modello F24 senza costi (Da rifare)
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "ATMZ-TDMD-QLGH-202511-Q-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "ATMZ-TDMD-QLGH-202511-Q-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi

##  2 Avviso pago Pa senza costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "XZLH-TLXV-VGYD-202505-E-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "XZLH-TLXV-VGYD-202505-E-1"
    And Verifica testo "In questa notifica ci sono più avvisi di pagamento: seleziona quello che vuoi pagare. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  2 Modello F24 senza costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "ZMWA-QWMZ-TGEY-202511-W-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "ZMWA-QWMZ-TGEY-202511-W-1"
    And Verifica testo "Puoi pagare questa notifica tramite F24. Per vedere i dettagli, apri i modelli. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  Ibrido senza costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "EDXU-QTXP-WAVT-202505-V-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "EDXU-QTXP-WAVT-202505-V-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Cliccare su Come Mai
    And Verifica Pagina Come Mai