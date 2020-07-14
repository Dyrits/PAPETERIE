package fr.eni.papeterie.ihm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelButtons extends JPanel {
    private final List<IPanelButtonsObserver> observers;
    private JButton btnPrevious;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnForward;

    public PanelButtons() {
        super();
        this.setLayout(new FlowLayout());
        this.add(getBtnPrevious());
        this.add(getBtnNew());
        this.add(getBtnSave());
        this.add(getBtnDelete());
        this.add(getBtnForward());
        this.observers = new ArrayList<>();
    }

    public void addObserver(IPanelButtonsObserver observer){
        observers.add(observer);
    }

    public JButton getBtn(String source) {
        Icon icon = new ImageIcon(this.getClass().getResource(source));
        return new JButton(icon);
    }

    public JButton getBtnPrevious() {
        if (this.btnPrevious == null) {
            this.btnPrevious = getBtn("./resources/Back24.gif");
            this.btnPrevious.addActionListener(event -> {
                for(IPanelButtonsObserver observer: this.observers){
                    observer.btnPrevious();
                }
            });
        }
        return this.btnPrevious;
    }

    public JButton getBtnNew() {
        if (this.btnNew == null) {
            this.btnNew = getBtn("./resources/New24.gif");
            this.btnNew.addActionListener(event -> {
                for(IPanelButtonsObserver observer: this.observers){
                    observer.btnNew();
                }
            });
        }
        return this.btnNew;
    }

    public JButton getBtnSave() {
        if (this.btnSave == null) {
            this.btnSave = getBtn("./resources/Save24.gif");
            this.btnSave.addActionListener(event -> {
                for(IPanelButtonsObserver observer: this.observers){
                    observer.btnSave();
                }
            });
        }
        return this.btnSave;
    }

    public JButton getBtnDelete() {
        if (this.btnDelete == null) {
            this.btnDelete = getBtn("./resources/Delete24.gif");
            this.btnDelete.addActionListener(event -> {
                for(IPanelButtonsObserver observer: this.observers){
                    observer.btnDelete();
                }
            });
        }
        return this.btnDelete;
    }

    public JButton getBtnForward() {
        if (this.btnForward == null) {
            this.btnForward = getBtn("./resources/Forward24.gif");
            this.btnForward.addActionListener(event -> {
                for(IPanelButtonsObserver observer: this.observers){
                    observer.btnForward();
                }
            });
        }
        return this.btnForward;
    }
}
