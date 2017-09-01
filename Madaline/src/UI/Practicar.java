package UI;

import Core.Controlador;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
import static UI.Start.puntero;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Practicar extends Principal {
    PanelLetra panelLetra;
    
    
    AnimatedWallpaper wall;
    
    int t_feliz=1;
    int t_animo=1;
    
    Icon tutor_feliz[],tutor_animo[],tutor_reposo;
    int n_apoyo = 4, n_exito = 4;
    
    AudioClip correct, fail, succes, apoyo[], exito[], numeros[], nada;
    AudioClip ltr_a,ltr_e,ltr_i,ltr_o,ltr_u;
    
    public Practicar(Controlador aplicacion, javax.swing.JFrame padre) {

        super(aplicacion, padre);
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);

        pnlContenedorLienzo.add(lienzo);
        panelLetra = new PanelLetra();
        pnl_ayuda.add(panelLetra);

        setVisible(true);

        SetOpacidadPaneles();
        
        SetOpacidadBotones();
        CargarSonidos();
        CargarTutor();
        setTutorReposo();
        cargarFondoLienzo();
        wall=new AnimatedWallpaper(pnl_main);
        
        new Dificultad(this, true).setVisible(true);

        aplicacion.setModoReconocimiento(modo_juego);

    }
  
    private void CargarTutor() {
        ImageIcon image = new ImageIcon(getClass().getResource("/Iconos/tutor_reposo.gif"));
        tutor_reposo = new ImageIcon(image.getImage().getScaledInstance(txt_tutor.getWidth(), txt_tutor.getHeight(), Image.SCALE_DEFAULT));
        
        tutor_feliz = new Icon[t_feliz];
        tutor_animo = new Icon[t_animo];

        for (int i = 0; i < t_feliz; i++) {
            image = new ImageIcon(getClass().getResource("/Iconos/tutor_feliz" + (i + 1) + ".gif"));
            tutor_feliz[i] = new ImageIcon(image.getImage().getScaledInstance(txt_tutor.getWidth(), txt_tutor.getHeight(), Image.SCALE_DEFAULT));
        }

        for (int i = 0; i < t_animo; i++) {
            image = new ImageIcon(getClass().getResource("/Iconos/tutor_animo" + (i + 1) + ".gif"));
            tutor_animo[i] = new ImageIcon(image.getImage().getScaledInstance(txt_tutor.getWidth(), txt_tutor.getHeight(), Image.SCALE_DEFAULT));
        }
        
    }
    
    
    
    private void setTutorReposo(){
        txt_tutor.setIcon(tutor_reposo);
    }
    
    private void setTutorFeliz(){
        txt_tutor.setIcon(tutor_feliz[0]);
        timer.start();
    }
    
    private void setTutorAnimo(){
        txt_tutor.setIcon(tutor_animo[0]);
        timer.start();
    }
    
    Timer timer = new Timer(2000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            setTutorReposo();
            timer.stop();
        }
    });
    
    Timer timer2 = new Timer(500, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
       
           
        }
    });
    private void CargarSonidos() {
        correct = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/Correct.wav"));
        fail = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/Fail.wav"));
        succes = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/Succes.wav"));
        nada = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/nada.wav"));
        
        ltr_a = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/letra_a.wav"));
        ltr_e = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/letra_e.wav"));
        ltr_i = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/letra_i.wav"));
        ltr_o = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/letra_o.wav"));
        ltr_u = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/letra_u.wav"));

        apoyo = new AudioClip[n_apoyo];
        exito = new AudioClip[n_exito];

        for (int i = 0; i < n_apoyo; i++) {
            apoyo[i] = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/apoyo_" + (i + 1) + ".wav"));
        }

        for (int i = 0; i < n_exito; i++) {
            apoyo[i] = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/exito_" + (i + 1) + ".wav"));
        }
        
        numeros = new AudioClip[10];
        for (int i = 0; i < 10; i++) {
            numeros[i] = java.applet.Applet.newAudioClip(getClass().getResource("/Audio/numero_" + i + ".wav"));
        }

    }

    private void PlayReconocido(String a) {
        boolean vacio = false;
        if (isNumero(a)) {
            
            numeros[Integer.parseInt(a)].play();
        } else {
            switch (a.toLowerCase()) {
                case "a":
                    ltr_a.play();
                    break;
                case "e":
                    ltr_e.play();
                    break;
                case "i":
                    ltr_i.play();
                    break;
                case "o":
                    ltr_o.play();
                    break;
                case "u":
                    ltr_u.play();
                    break;
                default:
                    nada.play();
                    vacio = true;
                    break;
            }
        }
        
        if (vacio) {
            setTutorAnimo();
        } else {
            setTutorFeliz();
        }

    }

    private boolean isNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void SetOpacidadBotones() {

        for (JButton boton : new JButton[]{btn_pulsa, btn_salir, btn_clean, btn_rojo,
            btn_verde, btn_lila, btn_marron, btn_mas, btn_menos, btn_naranja, btn_negro, 
            btn_deshacer }) {

            boton.setOpaque(false);
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
        }

        tbtCuadricula.setOpaque(false);
        tbtCuadricula.setContentAreaFilled(false);
        tbtCuadricula.setBorderPainted(false);
    }

    private void SetOpacidadPaneles() {

        for (JPanel panel : new JPanel[]{pnl_opciones, pnl_botones, pnl_resultados, pnl_funciones, pnl_colores,pnl_tutor}) {

            panel.setOpaque(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_main = new javax.swing.JPanel();
        pnl_opciones = new javax.swing.JPanel();
        pnl_colores = new javax.swing.JPanel();
        btn_lila = new javax.swing.JButton();
        btn_marron = new javax.swing.JButton();
        btn_naranja = new javax.swing.JButton();
        btn_verde = new javax.swing.JButton();
        btn_rojo = new javax.swing.JButton();
        btn_negro = new javax.swing.JButton();
        pnl_funciones = new javax.swing.JPanel();
        btn_clean = new javax.swing.JButton();
        btn_menos = new javax.swing.JButton();
        btn_mas = new javax.swing.JButton();
        tbtCuadricula = new javax.swing.JToggleButton();
        btn_deshacer = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        pnl_ayuda = new javax.swing.JPanel();
        pnlContenedorLienzo = new javax.swing.JPanel();
        pnl_botones = new javax.swing.JPanel();
        btn_pulsa = new javax.swing.JButton();
        pnl_tutor = new javax.swing.JPanel();
        txt_tutor = new javax.swing.JLabel();
        pnl_resultados = new javax.swing.JPanel();
        txt_modolibre = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mitAbrir = new javax.swing.JMenuItem();
        mitGuardar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mitMantenimiento = new javax.swing.JMenuItem();
        mitEntrenar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(132, 236, 159));
        setUndecorated(true);

        pnl_main.setBackground(new java.awt.Color(255, 102, 102));
        pnl_main.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnl_main.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnl_mainComponentResized(evt);
            }
        });

        pnl_opciones.setBackground(new java.awt.Color(132, 236, 159));

        pnl_colores.setBackground(new java.awt.Color(132, 236, 159));

        btn_lila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/7d028c_2.png"))); // NOI18N
        btn_lila.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/7d028c_1.png"))); // NOI18N
        btn_lila.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/7d028c_3.png"))); // NOI18N
        btn_lila.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_lilaMouseEntered(evt);
            }
        });
        btn_lila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lilaActionPerformed(evt);
            }
        });

        btn_marron.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/3d2601_2.png"))); // NOI18N
        btn_marron.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/3d2601_1.png"))); // NOI18N
        btn_marron.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/3d2601_3.png"))); // NOI18N
        btn_marron.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_marronMouseEntered(evt);
            }
        });
        btn_marron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_marronActionPerformed(evt);
            }
        });

        btn_naranja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/e79e00_2.png"))); // NOI18N
        btn_naranja.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/e79e00_1.png"))); // NOI18N
        btn_naranja.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/e79e00_3.png"))); // NOI18N
        btn_naranja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_naranjaMouseEntered(evt);
            }
        });
        btn_naranja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_naranjaActionPerformed(evt);
            }
        });

        btn_verde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/63b82a_2.png"))); // NOI18N
        btn_verde.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/63b82a_1.png"))); // NOI18N
        btn_verde.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/63b82a_3.png"))); // NOI18N
        btn_verde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_verdeMouseEntered(evt);
            }
        });
        btn_verde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verdeActionPerformed(evt);
            }
        });

        btn_rojo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ff1800_2.png"))); // NOI18N
        btn_rojo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ff1800.png"))); // NOI18N
        btn_rojo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ff1800_3.png"))); // NOI18N
        btn_rojo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_rojoMouseEntered(evt);
            }
        });
        btn_rojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rojoActionPerformed(evt);
            }
        });

        btn_negro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ff1800_2.png"))); // NOI18N
        btn_negro.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ff1800.png"))); // NOI18N
        btn_negro.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ff1800_3.png"))); // NOI18N
        btn_negro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_negroMouseEntered(evt);
            }
        });
        btn_negro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_negroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_coloresLayout = new javax.swing.GroupLayout(pnl_colores);
        pnl_colores.setLayout(pnl_coloresLayout);
        pnl_coloresLayout.setHorizontalGroup(
            pnl_coloresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_coloresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_coloresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_rojo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_negro, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_coloresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_coloresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_lila, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_marron, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_naranja, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_verde, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnl_coloresLayout.setVerticalGroup(
            pnl_coloresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_coloresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_negro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_rojo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_verde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_naranja)
                .addGap(18, 18, 18)
                .addComponent(btn_marron, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_lila, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnl_funciones.setBackground(new java.awt.Color(132, 236, 159));

        btn_clean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clean_2.png"))); // NOI18N
        btn_clean.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clean_1.png"))); // NOI18N
        btn_clean.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clean_3.png"))); // NOI18N
        btn_clean.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cleanMouseEntered(evt);
            }
        });
        btn_clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cleanActionPerformed(evt);
            }
        });

        btn_menos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/menos_2.png"))); // NOI18N
        btn_menos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/menos_1.png"))); // NOI18N
        btn_menos.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/menos_3.png"))); // NOI18N
        btn_menos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_menosMouseEntered(evt);
            }
        });
        btn_menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menosActionPerformed(evt);
            }
        });

        btn_mas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mas_2.png"))); // NOI18N
        btn_mas.setBorderPainted(false);
        btn_mas.setContentAreaFilled(false);
        btn_mas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mas_1.png"))); // NOI18N
        btn_mas.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mas_3.png"))); // NOI18N
        btn_mas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_masMouseEntered(evt);
            }
        });
        btn_mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_masActionPerformed(evt);
            }
        });

        tbtCuadricula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/regilla_2.png"))); // NOI18N
        tbtCuadricula.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/regilla_1.png"))); // NOI18N
        tbtCuadricula.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/regilla_3.png"))); // NOI18N
        tbtCuadricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbtCuadriculaActionPerformed(evt);
            }
        });

        btn_deshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/menos_2.png"))); // NOI18N
        btn_deshacer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/menos_1.png"))); // NOI18N
        btn_deshacer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/menos_3.png"))); // NOI18N
        btn_deshacer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_deshacerMouseEntered(evt);
            }
        });
        btn_deshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deshacerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_funcionesLayout = new javax.swing.GroupLayout(pnl_funciones);
        pnl_funciones.setLayout(pnl_funcionesLayout);
        pnl_funcionesLayout.setHorizontalGroup(
            pnl_funcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_funcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_funcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_menos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clean, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbtCuadricula, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_deshacer, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_funcionesLayout.setVerticalGroup(
            pnl_funcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_funcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_mas)
                .addGap(18, 18, 18)
                .addComponent(btn_menos)
                .addGap(18, 18, 18)
                .addComponent(btn_deshacer)
                .addGap(18, 18, 18)
                .addComponent(btn_clean)
                .addGap(18, 18, 18)
                .addComponent(tbtCuadricula, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/salir_2.png"))); // NOI18N
        btn_salir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/salir_1.png"))); // NOI18N
        btn_salir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/salir_3.png"))); // NOI18N
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

        javax.swing.GroupLayout pnl_opcionesLayout = new javax.swing.GroupLayout(pnl_opciones);
        pnl_opciones.setLayout(pnl_opcionesLayout);
        pnl_opcionesLayout.setHorizontalGroup(
            pnl_opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_opcionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_opcionesLayout.createSequentialGroup()
                        .addComponent(pnl_colores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_funciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_opcionesLayout.createSequentialGroup()
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
        );
        pnl_opcionesLayout.setVerticalGroup(
            pnl_opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_opcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_opcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnl_colores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_funciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btn_salir)
                .addGap(19, 19, 19))
        );

        pnl_ayuda.setBackground(new java.awt.Color(255, 255, 255));
        pnl_ayuda.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnl_ayudaComponentResized(evt);
            }
        });

        javax.swing.GroupLayout pnl_ayudaLayout = new javax.swing.GroupLayout(pnl_ayuda);
        pnl_ayuda.setLayout(pnl_ayudaLayout);
        pnl_ayudaLayout.setHorizontalGroup(
            pnl_ayudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );
        pnl_ayudaLayout.setVerticalGroup(
            pnl_ayudaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 143, Short.MAX_VALUE)
        );

        pnlContenedorLienzo.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedorLienzo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlContenedorLienzoComponentResized(evt);
            }
        });

        javax.swing.GroupLayout pnlContenedorLienzoLayout = new javax.swing.GroupLayout(pnlContenedorLienzo);
        pnlContenedorLienzo.setLayout(pnlContenedorLienzoLayout);
        pnlContenedorLienzoLayout.setHorizontalGroup(
            pnlContenedorLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlContenedorLienzoLayout.setVerticalGroup(
            pnlContenedorLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnl_botones.setBackground(new java.awt.Color(132, 236, 159));

        btn_pulsa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pulsa_2.png"))); // NOI18N
        btn_pulsa.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btn_pulsa.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pulsa_1.png"))); // NOI18N
        btn_pulsa.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pulsa_3.png"))); // NOI18N
        btn_pulsa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pulsaMouseEntered(evt);
            }
        });
        btn_pulsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pulsaActionPerformed(evt);
            }
        });

        pnl_tutor.setBackground(new java.awt.Color(41, 185, 79));

        javax.swing.GroupLayout pnl_tutorLayout = new javax.swing.GroupLayout(pnl_tutor);
        pnl_tutor.setLayout(pnl_tutorLayout);
        pnl_tutorLayout.setHorizontalGroup(
            pnl_tutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_tutorLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(txt_tutor, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnl_tutorLayout.setVerticalGroup(
            pnl_tutorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnl_botonesLayout = new javax.swing.GroupLayout(pnl_botones);
        pnl_botones.setLayout(pnl_botonesLayout);
        pnl_botonesLayout.setHorizontalGroup(
            pnl_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_botonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_pulsa, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
            .addComponent(pnl_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnl_botonesLayout.setVerticalGroup(
            pnl_botonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_botonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_pulsa, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(pnl_tutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnl_resultados.setBackground(new java.awt.Color(132, 236, 159));

        txt_modolibre.setFont(new java.awt.Font("Dizzy Edge DEMO", 0, 36)); // NOI18N
        txt_modolibre.setForeground(new java.awt.Color(255, 96, 5));
        txt_modolibre.setText("MODO LIBRE");

        javax.swing.GroupLayout pnl_resultadosLayout = new javax.swing.GroupLayout(pnl_resultados);
        pnl_resultados.setLayout(pnl_resultadosLayout);
        pnl_resultadosLayout.setHorizontalGroup(
            pnl_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_resultadosLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(txt_modolibre)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        pnl_resultadosLayout.setVerticalGroup(
            pnl_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_resultadosLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(txt_modolibre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_mainLayout = new javax.swing.GroupLayout(pnl_main);
        pnl_main.setLayout(pnl_mainLayout);
        pnl_mainLayout.setHorizontalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_mainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnl_opciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_mainLayout.createSequentialGroup()
                        .addComponent(pnlContenedorLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_mainLayout.createSequentialGroup()
                        .addComponent(pnl_ayuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_resultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        pnl_mainLayout.setVerticalGroup(
            pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_mainLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_mainLayout.createSequentialGroup()
                        .addGroup(pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnl_resultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl_ayuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlContenedorLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl_botones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(pnl_opciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        jMenu1.setText("Opciones");

        mitAbrir.setText("Abrir imagen");
        mitAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(mitAbrir);

        mitGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        mitGuardar.setText("Guardar imagen");
        mitGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(mitGuardar);
        jMenu1.add(jSeparator1);

        mitMantenimiento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        mitMantenimiento.setText("Mantenimiento");
        mitMantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitMantenimientoActionPerformed(evt);
            }
        });
        jMenu1.add(mitMantenimiento);

        mitEntrenar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mitEntrenar.setText("Entrenar");
        mitEntrenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mitEntrenarActionPerformed(evt);
            }
        });
        jMenu1.add(mitEntrenar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        salir();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cleanActionPerformed
        lienzo.limpiarContenido();
    }//GEN-LAST:event_btn_cleanActionPerformed

    private void btn_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_masActionPerformed
        lienzo.aumentarGrosor();
    }//GEN-LAST:event_btn_masActionPerformed

    private void btn_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menosActionPerformed
        lienzo.disminuirGrosor();
    }//GEN-LAST:event_btn_menosActionPerformed

    private void pnlContenedorLienzoComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlContenedorLienzoComponentResized
        lienzo.setSize(pnlContenedorLienzo.getSize());
    }//GEN-LAST:event_pnlContenedorLienzoComponentResized

    private void btn_rojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rojoActionPerformed
        lienzo.setColor(new Color(255, 24, 0));
    }//GEN-LAST:event_btn_rojoActionPerformed

    private void btn_verdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verdeActionPerformed
        lienzo.setColor(new Color(99, 184, 42));
    }//GEN-LAST:event_btn_verdeActionPerformed

    private void btn_naranjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_naranjaActionPerformed
        lienzo.setColor(new Color(231, 158, 5));
    }//GEN-LAST:event_btn_naranjaActionPerformed

    private void btn_marronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_marronActionPerformed
        lienzo.setColor(new Color(61, 38, 1));
    }//GEN-LAST:event_btn_marronActionPerformed

    private void btn_lilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lilaActionPerformed
        lienzo.setColor(new Color(125, 2, 140));
    }//GEN-LAST:event_btn_lilaActionPerformed

    private void btn_pulsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pulsaActionPerformed
        BufferedImage imagen = lienzo.getImagen();
        String caracter = aplicacion.reconocerImagen(imagen);
        PlayReconocido(caracter);
        panelLetra.dibujarLetra(caracter);
        wall.CambiarWallpaper();
    }//GEN-LAST:event_btn_pulsaActionPerformed

    private void pnl_ayudaComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnl_ayudaComponentResized
        panelLetra.setSize(pnl_ayuda.getSize());
    }//GEN-LAST:event_pnl_ayudaComponentResized

    private void mitGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitGuardarActionPerformed
        lienzo.guardarImagen();
    }//GEN-LAST:event_mitGuardarActionPerformed

    private void mitMantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitMantenimientoActionPerformed
        new Mantenimiento(aplicacion, null).setVisible(true);
    }//GEN-LAST:event_mitMantenimientoActionPerformed

    private void mitAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitAbrirActionPerformed
        lienzo.abrirImagen();
    }//GEN-LAST:event_mitAbrirActionPerformed

    private void pnl_mainComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnl_mainComponentResized
        //wall.setSize(pnl_main.getSize());
    }//GEN-LAST:event_pnl_mainComponentResized

    private void tbtCuadriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbtCuadriculaActionPerformed
        lienzo.activarFondo(tbtCuadricula.isSelected());
    }//GEN-LAST:event_tbtCuadriculaActionPerformed
    private void btn_rojoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rojoMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_rojoMouseEntered

    private void btn_verdeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_verdeMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_verdeMouseEntered

    private void btn_naranjaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_naranjaMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_naranjaMouseEntered

    private void btn_marronMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_marronMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_marronMouseEntered

    private void btn_lilaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_lilaMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_lilaMouseEntered

    private void btn_masMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_masMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_masMouseEntered

    private void btn_menosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_menosMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_menosMouseEntered

    private void btn_cleanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cleanMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_cleanMouseEntered

    private void btn_guiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guiaMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_guiaMouseEntered

    private void btn_pulsaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pulsaMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_pulsaMouseEntered

    private void btn_salirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_salirMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_salirMouseEntered

    private void mitEntrenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mitEntrenarActionPerformed
        String[] caracteres = aplicacion.getCaracteres_actual();
        Arrays.sort(caracteres);
        int op = JOptionPane.showOptionDialog(this, "Seleccione", "Caracteres",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                caracteres, "a");
        if (op < 0 || op >= caracteres.length)
            return;
        super.entrenar(caracteres[op]);
    }//GEN-LAST:event_mitEntrenarActionPerformed

    private void btn_negroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_negroMouseEntered
        puntero.play();
        
    }//GEN-LAST:event_btn_negroMouseEntered

    private void btn_negroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_negroActionPerformed
        lienzo.setColor(Color.black);
    }//GEN-LAST:event_btn_negroActionPerformed

    private void btn_deshacerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deshacerMouseEntered
        puntero.play();
    }//GEN-LAST:event_btn_deshacerMouseEntered

    private void btn_deshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deshacerActionPerformed
        lienzo.deshacerTrazo();
    }//GEN-LAST:event_btn_deshacerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clean;
    private javax.swing.JButton btn_deshacer;
    private javax.swing.JButton btn_lila;
    private javax.swing.JButton btn_marron;
    private javax.swing.JButton btn_mas;
    private javax.swing.JButton btn_menos;
    private javax.swing.JButton btn_naranja;
    private javax.swing.JButton btn_negro;
    private javax.swing.JButton btn_pulsa;
    private javax.swing.JButton btn_rojo;
    private javax.swing.JButton btn_salir;
    private javax.swing.JButton btn_verde;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mitAbrir;
    private javax.swing.JMenuItem mitEntrenar;
    private javax.swing.JMenuItem mitGuardar;
    private javax.swing.JMenuItem mitMantenimiento;
    private javax.swing.JPanel pnlContenedorLienzo;
    private javax.swing.JPanel pnl_ayuda;
    private javax.swing.JPanel pnl_botones;
    private javax.swing.JPanel pnl_colores;
    private javax.swing.JPanel pnl_funciones;
    private javax.swing.JPanel pnl_main;
    private javax.swing.JPanel pnl_opciones;
    private javax.swing.JPanel pnl_resultados;
    private javax.swing.JPanel pnl_tutor;
    private javax.swing.JToggleButton tbtCuadricula;
    private javax.swing.JLabel txt_modolibre;
    private javax.swing.JLabel txt_tutor;
    // End of variables declaration//GEN-END:variables

}
