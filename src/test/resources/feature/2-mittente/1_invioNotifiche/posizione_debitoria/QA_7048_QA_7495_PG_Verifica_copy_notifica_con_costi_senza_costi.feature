Feature: Verifica_copy_notifica_con_costi_senza_costi PG

  @TestSuite
  @TA_QA_7048_QA_7495
  @TA_VerificaCopy
  @NRT_Blocco_1
  Scenario: [QA_7048_QA_7495_PG] Verifica_copy_notifica_con_costi_senza_costi
    Given  PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
##  1 Avviso pago Pa con costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "AWUX-LUYZ-HJPD-202505-Q-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 1 IUN "AWUX-LUYZ-HJPD-202505-Q-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  1 Modello F24 con costi PAGATO
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "QPUP-YJRD-GEYQ-202505-D-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 1 IUN "QPUP-YJRD-GEYQ-202505-D-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  2 Avviso pago Pa con costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "XWJP-HJEL-WVHR-202505-Z-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 2 IUN "XWJP-HJEL-WVHR-202505-Z-1"
    And Verifica testo "In questa notifica ci sono più avvisi di pagamento: seleziona quello che vuoi pagare. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  2 Modello F24 con costi Pagato
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "EZKR-XMHE-YDAL-202505-E-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 2 IUN "EZKR-XMHE-YDAL-202505-E-1"
    And Verifica testo "Puoi pagare questa notifica tramite F24. Per vedere i dettagli, apri i modelli. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  Ibrido con costi ??
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "VDUE-NKNT-KGZL-202505-P-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 2 IUN "VDUE-NKNT-KGZL-202505-P-1"
    And Verifica testo "In questa notifica ci sono più possibilità di pagamento: seleziona o scarica ciò che vuoi pagare. Alcuni importi includono i costi di notifica"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  1 Avviso pago Pa senza costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "NPLP-RQLK-QDJH-202505-Z-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "NPLP-RQLK-QDJH-202505-Z-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  1 Modello F24 senza costi pAGATO
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "MPHJ-HLKQ-PTRT-202505-N-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "MPHJ-HLKQ-PTRT-202505-N-1"
    And Verifica testo "L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi

##  2 Avviso pago Pa senza costi
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "JRPH-MRMQ-LWMQ-202505-Y-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "JRPH-MRMQ-LWMQ-202505-Y-1"
    And Verifica testo "In questa notifica ci sono più avvisi di pagamento: seleziona quello che vuoi pagare. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  2 Modello F24 senza costi PAGATO
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "DMEM-YPKM-ADVL-202505-P-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "DMEM-YPKM-ADVL-202505-P-1"
    And Verifica testo "Puoi pagare questa notifica tramite F24. Per vedere i dettagli, apri i modelli. L'importo finale potrebbe essere diverso da quello indicato"
    And Nella sezione Dettaglio Notifiche si clicca su l'opzione Indietro
    And  Aspetta 0 secondi
##  Ibrido senza costi ??
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "THWP-VKMU-WDKJ-202505-A-1"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Neumro Copy Costi di notifica Inclusi 0 IUN "THWP-VKMU-WDKJ-202505-A-1"
    And Verifica testo "In questa notifica ci sono più possibilità di pagamento: seleziona o scarica ciò che vuoi pagare. Alcuni importi includono i costi di notifica"
    And Cliccare su Come Mai
    And Verifica Pagina Come Mai