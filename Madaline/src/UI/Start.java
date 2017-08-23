package UI;

import Core.Controlador;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Start extends javax.swing.JFrame {

    Controlador aplicacion;
    Wallpaper wall, fondo;

    public static AudioClip click, puntero, instrumental;

    public Start() {
        initComponents();
        setLocationRelativeTo(null);
        maximizar();
        
        CargarSonidos();

        setWallpaper();
        setfondo();
        setOpaque();
        setwhitepanel();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Splash s = new Splash();
                aplicacion = new Controlador();

                s.dispose();
                setVisible(true);
                cargarTutor();

            }
        }).start();

    }
   
    private void maximizar() {
        if (System.getProperty("os.name").equals("Linux")) { 
            // Maximizar ventana en linux
            Toolkit tk = Toolkit.getDefaultToolkit();  
            int xSize = ((int) tk.getScreenSize().getWidth());  
            int ySize = ((int) tk.getScreenSize().getHeight());  
            setSize(xSize,ySize);            
        }
        else {
            setExtendedState(MAXIMIZED_BOTH);
        }
    }
    
    private void cargarTutor() {
        ImageIcon img1 = new ImageIcon(getClass().getResource("/Iconos/tutor_saludo.gif"));

        Icon tutor_Start = new ImageIcon(img1.getImage().getScaledInstance(txt_tutor.getHeight(), txt_tutor.getHeight(), Image.SCALE_DEFAULT));
        txt_tutor.setIcon(tutor_Start);

    }

    private void CargarSonidos() {
        click = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/Click.wav"));
        puntero = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/Rollover.wav"));
        instrumental = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/Background.wav"));

    }

    private void setOpaque() {

        for (JButton boton : new JButton[]{btn_salir,btn_practicar,btn_reto,btn_acerca}) {

            boton.setOpaque(false);
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
        }

        btn_sonido.setOpaque(false);
        btn_sonido.setContentAreaFilled(false);
        btn_sonido.setBorderPainted(false);

    }

    private void setwhitepanel() {

        for (JPanel panel : new JPanel[]{pnl_control, pnl_botones, pnl_tutor,pnl_fondo}) {

            panel.setOpaque(false);
        }
    }

    private void setWallpaper() {
        wall = new Wallpaper("/Iconos/wallpaper_start.gif", pnl_wallpaper);

    }
    
     private void setfondo() {
        fondo = new Wallpaper("/Iconos/fondo_logo.png", pnl_fondo);

    }

    private void AjustarWallpaper() {
        wall.setSize(pnl_wallpaper.getSize());
        fondo.setSize(pnl_fondo.getSize());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_wallpaper = new javax.swing.JPanel();
        pnl_fondo = new javax.swing.JPanel();
        pnl_botones = new javax.swing.JPanel();
        btn_reto = new javax.swing.JButton();
        btn_practicar = new javax.swing.JButton();
        btn_acerca = new javax.swing.JButton();
        pnl_tutor = new javax.swing.JPanel();
        txt_tutor = new javax.swing.JLabel();
        pnl_control = new javax.swing.JPanel();
        btn_salir = new javax.swing.JButton();
        btn_sonido = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setExtendedState(6);
        setUndecorated(true);
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        pnl_fondo.setBackground(new java.awt.Color(39, 168, 243));
        pnl_fondo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnl_fondoComponentResized(evt);
            }
        });

        btn_reto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/jugar_2.png"))); // NOI18N
        btn_reto.setToolTipText("Jugar");
        btn_reto.setAlignmentY(0.0F);
        btn_reto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_reto.setMargin(new java.awt.Insets(-3, -6, -12, -2));
        btn_reto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/jugar_1.png"))); // NOI18N
        btn_reto.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/jugar_3.png"))); // NOI18N
        btn_reto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_reto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_reto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_retoMouseEntered(evt);
            }
        });
        btn_reto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_retoActionPerformed(evt);
            }
        });

        btn_practicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ajustes_2.png"))); // NOI18N
        btn_practicar.setToolTipText("Practicar");
        btn_practicar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_practicar.setMargin(new java.awt.Insets(-3, -9, -12, 0));
        btn_practicar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ajustes_1.png"))); // NOI18N
        btn_practicar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ajustes_3.png"))); // NOI18N
        btn_practicar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_practicar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_practicar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_practicarMouseEntered(evt);
            }
        });
        btn_practicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_practicarActionPerformed(evt);
            }
        });

        btn_acerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/acerca_2.png"))); // NOI18N
        btn_acerca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_acerca.setMargin(new java.awt.Insets(0, -10, 0, 0));
        btn_acerca.setName(""); // NOI18N
        btn_acerca.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/acerca_3.png"))); // NOI18N
        btn_acerca.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/acerca_1.png"))); // NOI18N
        btn_acerca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_acercaMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnl_botonesLayout = new javax.swing.GroupLayout(pnl_botones);
        pnl_botones.setLayout(pnl_botonesLayout);
        pnl_botonesLayout.setHorizontalGroup(
            pnl_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(btn_reto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btn_practicar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(btn_acerca, javax.swing.GroupLayout.PREFERRED_SIZE, 203, Short.MAX_VALUE)
        );
        pnl_botonesLayout.setVerticalGroup(
            pnl_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_botonesLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(btn_reto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_practicar)
                .addGap(18, 18, 18)
                .addComponent(btn_acerca, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        txt_tutor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pnl_tutorLayout = new javax.swing.GroupLayout(pnl_tutor);
        pnl_tutor.setLayout(pnl_tutorLayout);
        pnl_tutorLayout.setHorizontalGroup(
            pnl_tutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tutorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl_tutorLayout.setVerticalGroup(
            pnl_tutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_tutorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/x_1.png"))); // NOI18N
        btn_salir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/x_2.png"))); // NOI18N
        btn_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_salirMouseEntered(evt);
            }
        });
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_sonido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/sound_1.png"))); // NOI18N
        btn_sonido.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/sound_2.png"))); // NOI18N
        btn_sonido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sonidoMouseEntered(evt);
            }
        });
        btn_sonido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sonidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_controlLayout = new javax.swing.GroupLayout(pnl_control);
        pnl_control.setLayout(pnl_controlLayout);
        pnl_controlLayout.setHorizontalGroup(
            pnl_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_controlLayout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(btn_sonido, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_controlLayout.setVerticalGroup(
            pnl_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_controlLayout.createSequentialGroup()
                .addGroup(pnl_controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_salir)
                    .addComponent(btn_sonido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_fondoLayout = new javax.swing.GroupLayout(pnl_fondo);
        pnl_fondo.setLayout(pnl_fondoLayout);
        pnl_fondoLayout.setHorizontalGroup(
            pnl_fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_fondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnl_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnl_control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnl_fondoLayout.setVerticalGroup(
            pnl_fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_fondoLayout.createSequentialGroup()
                .addGroup(pnl_fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_fondoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(pnl_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnl_fondoLayout.createSequentialGroup()
                        .addComponent(pnl_control, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(pnl_botones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout pnl_wallpaperLayout = new javax.swing.GroupLayout(pnl_wallpaper);
        pnl_wallpaper.setLayout(pnl_wallpaperLayout);
        pnl_wallpaperLayout.setHorizontalGroup(
            pnl_wallpaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_wallpaperLayout.setVerticalGroup(
            pnl_wallpaperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_wallpaperLayout.createSequentialGroup()
                .addComponent(pnl_fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_wallpaper, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_wallpaper, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_salirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salirMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_salirMouseEntered

    private void btn_sonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sonidoActionPerformed

        if (btn_sonido.isSelected()) {
            instrumental.stop();
        } else {
            instrumental.loop();
        }

    }//GEN-LAST:event_btn_sonidoActionPerformed

    private void btn_sonidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_sonidoMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_sonidoMouseEntered

    private void pnl_fondoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnl_fondoComponentResized
        AjustarWallpaper();

    }//GEN-LAST:event_pnl_fondoComponentResized

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        AjustarWallpaper();

    }//GEN-LAST:event_formComponentResized

    private void btn_retoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_retoMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_retoMouseEntered

    private void btn_retoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retoActionPerformed
        click.play();
        setVisible(false);
        new ElegirJuego(this, aplicacion).setVisible(true);
    }//GEN-LAST:event_btn_retoActionPerformed

    private void btn_practicarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_practicarMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_practicarMouseEntered

    private void btn_practicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_practicarActionPerformed
        click.play();
    }//GEN-LAST:event_btn_practicarActionPerformed

    private void btn_acercaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_acercaMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_acercaMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_acerca;
    private javax.swing.JButton btn_practicar;
    private javax.swing.JButton btn_reto;
    private javax.swing.JButton btn_salir;
    private javax.swing.JToggleButton btn_sonido;
    private javax.swing.JPanel pnl_botones;
    private javax.swing.JPanel pnl_control;
    private javax.swing.JPanel pnl_fondo;
    private javax.swing.JPanel pnl_tutor;
    private javax.swing.JPanel pnl_wallpaper;
    private javax.swing.JLabel txt_tutor;
    // End of variables declaration//GEN-END:variables
}
