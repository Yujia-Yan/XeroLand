/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nightcode.demo.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import org.nightcode.demo.SkyX.Engine.Clock;
import org.nightcode.demo.SkyX.Engine.Scheduler;
import org.nightcode.demo.SkyX.Engine.TaskManager;
import org.nightcode.demo.SkyX.Engine.TimeBasedTask;
import org.nightcode.demo.SkyX.Framework.ITask;
import org.nightcode.demo.SkyX.Framework.SkyXObject;
import org.nightcode.demo.SkyX.Utility.Rnd;
import org.nightcode.demo.neuralNetwork.NeuralNetwork;

/**
 *
 * @author dell
 */
public class UserInterface extends javax.swing.JFrame {

    //////////////////////////////////////////////////////////////
    ////render
    //////////////////////////////////////////////////////////////
    transient KeyBoardBrain keyBrain = new KeyBoardBrain();
    transient public  ArrayList<org.nightcode.demo.game.Worker> workerPool = new ArrayList<Worker>();
    transient public  ArrayList<org.nightcode.demo.game.Food> foodPool = new ArrayList<Food>();
    public static evoltor evol;

    private class plainCanvas extends Canvas {

        @Override
        public void paint(Graphics g) {
        }

        @Override
        public void update(Graphics g) {
            paint(g);
        }
    }
    private boolean needRepaint = true;
    private Thread painting;

    private class paintingThread extends Thread {

        @Override
        public void run() {
            canvas1.createBufferStrategy(2);
            int k = 4;

            while (true) {
                System.out.println(TaskManager.getInstance().getSize());
                if (needRepaint) {
                    //System.out.println("drawStart"+Long.toString(System.currentTimeMillis()));
                    BufferStrategy bs = canvas1.getBufferStrategy();
                    Graphics gs = bs.getDrawGraphics();
                    gs.setColor(Color.white);
                    gs.fillRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
                    Graphics2D gg = (Graphics2D) gs;
                    gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                    //System.out.println("drawWorker"+Long.toString(System.currentTimeMillis()));
                    {
                        for (int i=0;i< workerPool.size();i++) {
                            {
                                {
                                    workerPool.get(i).draw(gg);
                                }
                            }
                            //workerPool.get(0).drawBest(gg);
                        }
                    }

                    //System.out.println("drawFood"+Long.toString(System.currentTimeMillis()));
                   {
                        for (int i=0;i<foodPool.size();i++) {

                            {
                            	foodPool.get(i).draw(gg);
                            }
                        }
                    }

                    //System.out.println("drawEnd"+Long.toString(System.currentTimeMillis()));
                    gs.dispose();
                    bs.show();
                    //canvas1.repaint();
                    try {
                        sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }

    /**
     * Creates new form UserInterface
     */
    public UserInterface() {
        initComponents();
        instance = this;
        Param.get().width = canvas1.getWidth();
        Param.get().height = canvas1.getHeight();
        System.setProperty("sun.java2d.opengl", "True");
        System.setProperty("sun.java2d.translaccel", "True");
        /*
         * intialize the engine
         */
        Scheduler s = Scheduler.getInstance();
        //System.out.println("asd");
        TaskManager task = TaskManager.getInstance();

        //System.out.println("s");



        evol = new evoltor();
        evol.init();


        Thread evolution = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    //jLabel1.setText("hello");
                    boolean k = true;
                    //synchronized(workerPool)
                    {
                        for (int i=0;i<workerPool.size();i++) {

                            if (workerPool.get(i).hp > 0) {
                                k = false;
                            }
                        }
                    }
                    //Clock.getInstance().stop();
                    if (k) {
                        evol.evolve();
                    }

                    //Clock.getInstance().start();
                    try {

                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        painting = new paintingThread();
        painting.start();
        evolution.start();
        s.start(4);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ElapseSlider = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jSlider1 = new javax.swing.JSlider();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        worstFit = new javax.swing.JLabel();
        avgFit = new javax.swing.JLabel();
        maxW = new javax.swing.JLabel();
        bestFit = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        genCount = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        minW = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        canvas1 = new plainCanvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("XeroLand");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("mainFrame");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel1.setLabelFor(jPanel2);
        jLabel1.setText("Hello!");

        jButton1.setText("reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("save");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("load");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("Rate");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel10.setText("food");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("mutation");

        ElapseSlider.setMaximum(1000);
        ElapseSlider.setOrientation(javax.swing.JSlider.VERTICAL);
        ElapseSlider.setPaintLabels(true);
        ElapseSlider.setValue(0);
        ElapseSlider.setDoubleBuffered(true);
        ElapseSlider.setFocusable(false);
        ElapseSlider.setName("ElapseSlider");
        ElapseSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ElapseSliderStateChanged(evt);
            }
        });

        jSlider2.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider2.setValue(30);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jSlider1.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider1.setValue(15);
        jSlider1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(ElapseSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        .addComponent(ElapseSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        jLabel7.setText("min Weight");

        worstFit.setText("0");

        avgFit.setText("0");
        avgFit.setDoubleBuffered(true);

        maxW.setText("0");

        bestFit.setText("0");

        jLabel5.setText("avg Fitness");

        jLabel2.setText("generation:");

        jLabel4.setText("worst Fitness");

        genCount.setText("0");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("paint?");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });

        jLabel6.setText("max Weight");

        minW.setText("0");

        jLabel3.setText("best Fitness");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(worstFit)
                    .addComponent(bestFit)
                    .addComponent(jLabel3)
                    .addComponent(genCount)
                    .addComponent(jLabel5)
                    .addComponent(avgFit)
                    .addComponent(jLabel6)
                    .addComponent(maxW)
                    .addComponent(jLabel7)
                    .addComponent(minW)
                    .addComponent(jCheckBox1))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(genCount)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(bestFit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(worstFit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(avgFit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(22, 22, 22)
                .addComponent(maxW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(minW)
                .addContainerGap())
        );

        jToggleButton1.setText("pause");
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseClicked(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(95, 95, 95))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1))
                .addGap(26, 26, 26)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        canvas1.setPreferredSize(jPanel1.getPreferredSize());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ElapseSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ElapseSliderStateChanged
        // TODO add your handling code here:
        JSlider source = (JSlider) evt.getSource();
        int val = source.getValue();
        
        //Clock.getInstance().stop();
        if (val > 0) {
            
            synchronized (Clock.getInstance()) {
                Clock.getInstance().setFactor(Math.pow(10d,val/500) );
            }
        }
        //Clock.getInstance().start();
    }//GEN-LAST:event_ElapseSliderStateChanged

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        //System.out.println("!");

        // System.out.println(key);
        switch (key) {
            case KeyEvent.VK_UP:
                keyBrain.setUp();
                break;
            case KeyEvent.VK_DOWN:
                keyBrain.setDown();
                break;
            case KeyEvent.VK_LEFT:
                keyBrain.setLeft();
                break;
            case KeyEvent.VK_RIGHT:
                keyBrain.setRight();
                break;
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        //System.out.println(key);
        switch (key) {
            case KeyEvent.VK_UP:
                keyBrain.resetUp();
                break;
            case KeyEvent.VK_DOWN:
                keyBrain.resetDown();
                break;
            case KeyEvent.VK_LEFT:
                keyBrain.resetLeft();
                break;
            case KeyEvent.VK_RIGHT:
                keyBrain.resetRight();
                break;
        }
    }//GEN-LAST:event_formKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        evol.evolve();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        JSlider source = (JSlider) evt.getSource();
        if (source.getValue() < 100) {
            Param.get().mutationRate = source.getValue();
        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
        // TODO add your handling code here:娣诲姞涓�釜鎺у埗閲嶇粯

        needRepaint = jCheckBox1.isSelected();

    }//GEN-LAST:event_jCheckBox1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        //Scheduler.getInstance().stop();
        javax.swing.JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        File selected = chooser.getSelectedFile();
        if (selected.exists()) {
            int overwriteSelect = JOptionPane.showConfirmDialog(this,
                    "<html><font size=3>File" + selected.getName() + "already exists</font><html>",
                    "overwrite?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (overwriteSelect != JOptionPane.YES_OPTION) {
                return;
            }
        }
        evol.save(selected);

        //////////////////////////////////////////
        // Scheduler.getInstance().start(4);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        //Scheduler.getInstance().stop();
        javax.swing.JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File selected = chooser.getSelectedFile();
        if (selected != null) {
            if (selected.exists()) {
                evol.load(selected);
            } else {
                JOptionPane.showMessageDialog(null, "File does not exist");
            }
        }




        //////////////////////////////////////////
        //Scheduler.getInstance().start(4);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        // TODO add your handling code here:
        JSlider source=(JSlider)evt.getSource();
        int tmp=source.getValue();
        if(tmp>0) Param.get().NumOfFood=tmp;
    }//GEN-LAST:event_jSlider2StateChanged

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseClicked
        // TODO add your handling code here:
        if(jToggleButton1.isSelected())
            Clock.getInstance().stop();
        else Clock.getInstance().start();
    }//GEN-LAST:event_jToggleButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new UserInterface().setVisible(true);
            }
        });


    }

    public void updatePanel() {
        genCount.setText(Integer.toString(Param.get().generation));
        avgFit.setText(Double.toString(Param.get().totalFitness / Param.get().NumOfWorker));
        maxW.setText(Double.toString(Param.get().maxWeight));
        minW.setText(Double.toString(Param.get().minWeight));
        //jLabel1.setText("visual time:"+Long.toString(Clock.getInstance().getClockTime()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider ElapseSlider;
    private javax.swing.JLabel avgFit;
    private javax.swing.JLabel bestFit;
    private java.awt.Canvas canvas1;
    private javax.swing.JLabel genCount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel maxW;
    private javax.swing.JLabel minW;
    private javax.swing.JLabel worstFit;
    // End of variables declaration//GEN-END:variables
    public static UserInterface instance;

    public class evoltor {

        public ArrayList<org.nightcode.demo.game.Worker> next = new ArrayList<Worker>();

        public evoltor() {
        }
        /*
         * initialize the evoltor
         */

        public void init() {
            jLabel1.setText("initializing");
            needRepaint = false;
            workerPool.clear();
            foodPool.clear();
            Worker aWorker;
            //initialize worker
            for (int i = 0; i < Param.get().NumOfWorker; i++) {
                //initialize neural network
                NeuralNetwork neu = new NeuralNetwork();
                
                neu.randomlize(Param.get().inputNodes, Param.get().hidNodes, Param.get().hidLayers, Param.get().outputNodes);
                NeuralBrain nb = new NeuralBrain(neu);
                aWorker = new Worker(nb);
                aWorker.color = Rnd.getNextInt(10, Integer.MAX_VALUE);
                aWorker.setX(Rnd.getNextFloat(0, Param.get().width));
                aWorker.setY(Rnd.getNextFloat(0, Param.get().height));
                aWorker.setFaceClockwise(Rnd.getNextFloat(0f, (float) (2 * Math.PI)));
                //foodPool.add(aFood);
                workerPool.add(aWorker);
                //next
                neu = new NeuralNetwork();
                //initialize the nruralNetwork
                neu.randomlize(Param.get().inputNodes, Param.get().hidNodes, Param.get().hidLayers, Param.get().outputNodes);
                nb = new NeuralBrain(neu);
                aWorker = new Worker(nb);
                //foodPool.add(aFood);
                next.add(aWorker);
                //aWorker.aTask.start();
            }
            //debug Player
            aWorker = new Worker(keyBrain);
            aWorker.setX(Rnd.getNextFloat(0, Param.get().width));
            aWorker.setY(Rnd.getNextFloat(0, Param.get().height));
            aWorker.type = 232;
            //workerPool.add(aWorker);
            //aWorker.start(Clock.getInstance().getClockTime());
            //aWorker.aTask.setExecuteTime(Clock.getInstance().getClockTime());
            Food aFood;
            for (int i = 0; i < Param.get().NumOfFood; i++) {
                aFood = new Food();
                aFood.setX(Rnd.getNextFloat(0, Param.get().width));
                aFood.setY(Rnd.getNextFloat(0, Param.get().height));
                foodPool.add(aFood);
                aFood.start(Clock.getInstance().getVirtualTIme() + Rnd.getNextInt(0, 40));
            }
            //intial the sensor
            for (int i = 0; i < Param.get().NumOfWorker; i++) {
                aWorker = workerPool.get(i);
                aWorker.start(Clock.getInstance().getVirtualTIme () + Rnd.getNextInt(0, 40));
                aWorker.aTask.setExecuteTime(Clock.getInstance().getVirtualTIme() + Rnd.getNextInt(0, 40));
                workerPool.get(i).aTask.start();
            }

            jLabel1.setText("initialied!");
            //start drawing 
            needRepaint = jCheckBox1.isSelected();
            // System.gc();;
        }

        public void init(ArrayList<NeuralBrain> initPopulation) {

            jLabel1.setText("initializing");
            needRepaint = false;
            workerPool.clear();
            foodPool.clear();
            TaskManager.getInstance().clear();
            next.clear();
            Worker aWorker;
            //initialize worker
            for (int i = 0; i < Param.get().NumOfWorker; i++) {
                
                // System.out.println("helloS");
                aWorker = new Worker(initPopulation.get(i));
                aWorker.color = Rnd.getNextInt(10, Integer.MAX_VALUE);
                aWorker.setX(Rnd.getNextFloat(0, Param.get().width));
                aWorker.setY(Rnd.getNextFloat(0, Param.get().height));
                aWorker.setFaceClockwise(Rnd.getNextFloat(0f, (float) (2 * Math.PI)));
                //foodPool.add(aFood);
                workerPool.add(aWorker);
                //next
                NeuralNetwork neu = new NeuralNetwork();
                
                neu.randomlize(Param.get().inputNodes, Param.get().hidNodes, Param.get().hidLayers, Param.get().outputNodes);
                NeuralBrain nb = new NeuralBrain(neu);
                aWorker = new Worker(nb);
                //foodPool.add(aFood);
                next.add(aWorker);
                //aWorker.aTask.start();
            }
            
            aWorker = new Worker(keyBrain);
            aWorker.setX(Rnd.getNextFloat(0, Param.get().width));
            aWorker.setY(Rnd.getNextFloat(0, Param.get().height));
            aWorker.type = 232;
            //workerPool.add(aWorker);
            //aWorker.start(Clock.getInstance().getClockTime());
            //aWorker.aTask.setExecuteTime(Clock.getInstance().getClockTime());

                        Food aFood;
            for (int i = 0; i < Param.get().NumOfFood; i++) {
                aFood = new Food();
                aFood.setX(Rnd.getNextFloat(0, Param.get().width));
                aFood.setY(Rnd.getNextFloat(0, Param.get().height));
                foodPool.add(aFood);
                aFood.start(Clock.getInstance().getVirtualTIme() + Rnd.getNextInt(0, 40));
            }
           
            for (int i = 0; i < Param.get().NumOfWorker; i++) {
                aWorker = workerPool.get(i);
                aWorker.start(Clock.getInstance().getVirtualTIme()  + Rnd.getNextInt(0, 40));
                aWorker.aTask.setExecuteTime(Clock.getInstance().getVirtualTIme() + Rnd.getNextInt(0, 40));
                workerPool.get(i).aTask.start();
            }


            jLabel1.setText("initialized!");
            
            needRepaint = jCheckBox1.isSelected();
            // System.gc();;
        }

        public void mutate() {
            for (Worker i : workerPool) {
                NeuralNetwork ann = i.brain.getAnn();
                if (ann != null) {
                    ann.mutate(Param.get().mutationRate);
                }
            }
        }

        public void crossover(Worker son, Worker daughter) {
            Worker mum = getRoulette();
            Worker dad = getRoulette();
            double[] chromX = mum.brain.getAnn().getNet();
            double[] chromY = dad.brain.getAnn().getNet();
            double[] result1 = new double[chromX.length];
            double[] result2 = new double[chromX.length];
            for (int i = 0; i < chromX.length; i++) {
                double factor1 = Rnd.getNextFloat(0, 1);
                double factor2 = Rnd.getNextFloat(0, 1);
                result1[i] = factor1 * chromX[i] + (1 - factor1) * chromY[i];
                result2[i] = factor2 * chromX[i] + (1 - factor2) * chromY[i];
            }

            double factor1 = Rnd.getNextFloat(0, 1);
            double factor2 = Rnd.getNextFloat(0, 1);
            if (son != null) {
                son.brain.getAnn().setNet(result1, Param.get().inputNodes, Param.get().hidNodes, Param.get().hidLayers, Param.get().outputNodes);
                son.color = (int) (factor1 * dad.color + (1 - factor1) * mum.color);
                son.brain.setFitness(0.2d);
                Color ttt = new Color(son.color);
                int r = ttt.getRed();
                int g = ttt.getGreen();
                int b = ttt.getBlue();
                if (Rnd.getNextFloat(0, 1) < Param.get().mutationRate) {
                    r = r + Rnd.getNextInt(-10, 10);
                }

                if (Rnd.getNextFloat(0, 1) < Param.get().mutationRate) {
                    g = g + Rnd.getNextInt(-10, 10);
                }

                if (Rnd.getNextFloat(0, 1) < Param.get().mutationRate) {
                    b = b + Rnd.getNextInt(-10, 10);
                }
                if (r < 0) {
                    r = 0;
                }
                if (g < 0) {
                    g = 0;
                }
                if (b < 0) {
                    b = 0;
                }
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                son.color = new Color(r, g, b).getRGB();
            }
            if (daughter != null) {
                daughter.brain.getAnn().setNet(result2, Param.get().inputNodes, Param.get().hidNodes, Param.get().hidLayers, Param.get().outputNodes);
                daughter.brain.setFitness(0.2d);
                daughter.color = (int) (factor2 * dad.color + (1 - factor2) * mum.color);
                Color ttt = new Color(son.color);
                int r = ttt.getRed();
                int g = ttt.getGreen();
                int b = ttt.getBlue();
                if (Rnd.getNextFloat(0, 1) < Param.get().mutationRate) {
                    r = r + Rnd.getNextInt(-10, 10);
                }

                if (Rnd.getNextFloat(0, 1) < Param.get().mutationRate) {
                    g = g + Rnd.getNextInt(-10, 10);
                }

                if (Rnd.getNextFloat(0, 1) < Param.get().mutationRate) {
                    b = b + Rnd.getNextInt(-10, 10);
                }
                if (r < 0) {
                    r = 0;
                }
                if (g < 0) {
                    g = 0;
                }
                if (b < 0) {
                    b = 0;
                }
                if (r > 255) {
                    r = 255;
                }
                if (g > 255) {
                    g = 255;
                }
                if (b > 255) {
                    b = 255;
                }
                son.color = new Color(r, g, b).getRGB();
            }
        }

        public void evolve() {

            jLabel1.setText("evoling");
            //Scheduler.getInstance().stop();
            needRepaint = false;
           // Clock.getInstance().stop();
            TaskManager.getInstance().clear();
            Param.get().totalFitness = 0;
            Param.get().minFitness = 0;
            Param.get().maxFitness = 0;
            //System.out.println("hello");
            for (int i = 0; i < Param.get().elitism; i++) {

                workerPool.get(i).brain.setFitness(workerPool.get(i).brain.getFitness() / 2);
            }
            synchronized (workerPool) {
                //sort according to fitnesses
                Collections.sort(workerPool, new Comparator<Worker>() {

                    @Override
                    public int compare(Worker o1, Worker o2) {

                        if (o1.brain.getFitness() > o2.brain.getFitness()) {
                            return -1;
                        } else if (o1.brain.getFitness() < o2.brain.getFitness()) {
                            return 1;
                        }
                        return 0;
                    }
                });
                Param.get().maxFitness = workerPool.get(0).brain.getFitness();
                Param.get().minFitness = workerPool.get(workerPool.size() - 1).brain.getFitness();
                //fitness calculation
                for (Worker i : workerPool) {
                    Param.get().totalFitness += i.brain.getFitness();
                }
                //elite strategy
                for (int i = 0; i < Param.get().elitism; i++) {
                    NeuralNetwork k = workerPool.get(i).brain.getAnn();

                    next.get(i).brain.getAnn().setNet(k.getNet(), Param.get().inputNodes, Param.get().hidNodes, Param.get().hidLayers, Param.get().outputNodes);
                    next.get(i).color = workerPool.get(i).color;
                    next.get(i).brain.setFitness(workerPool.get(i).brain.getFitness());
                    //next.get(i).brain.setFitness(0.2);
                }
                double[] kw = workerPool.get(0).brain.getAnn().getNet();
                Param.get().maxWeight = kw[0];
                Param.get().minWeight = kw[0];
                for (int j = 1; j < kw.length; j++) {
                    if (kw[j] < Param.get().minWeight) {
                        Param.get().minWeight = kw[j];
                    }
                    if (kw[j] > Param.get().maxWeight) {
                        Param.get().maxWeight = kw[j];
                    }
                    //System.out.print((int)kw[j]+" ");
                }
                // System.out.println();;
                //crossover
                for (int i = Param.get().elitism; i < workerPool.size(); i++) {
                    Worker w1 = next.get(i);
                    Worker w2;
                    i++;
                    if (i < workerPool.size()) {
                        w2 = next.get(i);
                    } else {
                        w2 = null;
                    }
                    crossover(w1, w2);
                    w1.brain.getAnn().mutate(Param.get().mutationRate);
                    if (w2 != null) {
                        w2.brain.getAnn().mutate(Param.get().mutationRate);
                    }
                }
                //Scheduler.getInstance().start(4);
                synchronized(foodPool)
                 {
                    foodPool.clear();

                    Food aFood;
                    for (int i = 0; i < Param.get().NumOfFood; i++) {
                        aFood = new Food();
                        aFood.setX(Rnd.getNextFloat(10, Param.get().width - 10));
                        aFood.setY(Rnd.getNextFloat(10, Param.get().height - 10));
                        foodPool.add(aFood);
                        aFood.start(Clock.getInstance().getVirtualTIme() );
                    }
                }
                for (Worker i : next) {
                    i.reset();
                    i.start(Clock.getInstance().getVirtualTIme()  );
                    i.aTask.setExecuteTime(Clock.getInstance().getVirtualTIme() );
                    i.aTask.start();
                }
                
                ArrayList<org.nightcode.demo.game.Worker> tmp = workerPool;
                workerPool = next;
                next = tmp;
                bestFit.setText(Double.toString(Param.get().maxFitness));
                worstFit.setText(Double.toString(Param.get().minFitness));
                //System.out.println("finish");
               
               
                System.gc();
                needRepaint = jCheckBox1.isSelected();
                Param.get().generation++;
                updatePanel();
               // Clock.getInstance().start();
                Clock.getInstance().beginFrame();
                jLabel1.setText("evolved");
            }
        }

        public Worker getRoulette() {
            double Slice = Rnd.getNextFloat(0, 1) * Param.get().totalFitness;

            Worker TheChosenOne = null;

            double FitnessSoFar = 0;

            for (int i = 0; i < workerPool.size(); i++) {
                FitnessSoFar += workerPool.get(i).brain.getFitness();
                if (FitnessSoFar >= Slice) {
                    TheChosenOne = workerPool.get(i);
                    break;
                }

            }
            return TheChosenOne;

        }

        public void save(File selected) {
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                //////////////////////////////////////////
                fos = new FileOutputStream(selected);

                //////////////////////////////////////////
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                oos = new ObjectOutputStream(fos);
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            //configuration

            try {

                oos.writeObject(Param.get());

                for (int i = 0; i < Param.get().NumOfWorker; i++) {

                    oos.writeObject(workerPool.get(i).brain);

                }
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);

            }
            try {
                oos.close();

                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void load(File selected) {
            FileInputStream fos = null;
            ObjectInputStream oos = null;
            try {
                //////////////////////////////////////////
                fos = new FileInputStream(selected);

                //////////////////////////////////////////
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "File not found");
                return;
            }
            try {
                if(fos!=null)
                oos = new ObjectInputStream(fos);
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "io error");
                return;
            }



            ArrayList<NeuralBrain> result = new ArrayList<NeuralBrain>();
            try {
                try {

                    Param.set((Param) oos.readObject());
                    updatePanel();

                    for (int i = 0; i < Param.get().NumOfWorker; i++) {

                        result.add((NeuralBrain) oos.readObject());

                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "File Error");
                            
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "IO Error");
                return;
            }
            try {
                oos.close();

                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            this.init(result);
        }
    }
}
