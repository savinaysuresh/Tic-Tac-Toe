import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe extends JFrame implements ItemListener, ActionListener 
{

    int i, j, ii, jj, x, y, yesnull;
    int a[][] = {
        {26, 1, 2, 3, 4, 5, 27}, {26, 6, 7, 8, 9, 10, 27}, {26, 11, 12, 13, 14, 15, 27},
        {26, 16, 17, 18, 19, 20, 27}, {26, 21, 22, 23, 24, 25, 27}, {26, 1, 6, 11, 16, 21, 27}, 
        {26, 2, 7, 12, 17, 22, 27}, {26, 3, 8, 13, 18, 23, 27}, {26, 4, 9, 14, 19, 24, 27}, 
        {26, 5, 10, 15, 20, 25, 27}, {26, 1, 7, 13, 19, 25, 27}, {26, 5, 9, 13, 17, 21, 27}
    };
    
    int a1[][] = {
        {26, 1, 2, 3, 4, 5, 27}, {26, 6, 7, 8, 9, 10, 27}, {26, 11, 12, 13, 14, 15, 27},
        {26, 16, 17, 18, 19, 20, 27}, {26, 21, 22, 23, 24, 25, 27}, {26, 1, 6, 11, 16, 21, 27}, 
        {26, 2, 7, 12, 17, 22, 27}, {26, 3, 8, 13, 18, 23, 27}, {26, 4, 9, 14, 19, 24, 27}, 
        {26, 5, 10, 15, 20, 25, 27}, {26, 1, 7, 13, 19, 25, 27}, {26, 5, 9, 13, 17, 21, 27}
    };
				
    boolean state, type, set;

    Icon ic1, ic2, icon, ic11, ic22;
    Checkbox c1, c2;
    JLabel l1, l2;
    JButton b[] = new JButton[25];
    JButton reset;

    public void showButton() 
    {
        x = 10;
        y = 10;
        j = 0;
        
        for (i = 0; i <= 24; i++, x += 100, j++) 
        {
            b[i] = new JButton();
            
            if (j == 5) 
            {
                j = 0;
                y += 100;
                x = 10;
            }
            
            b[i].setBounds(x, y, 100, 100);
            add(b[i]);
            b[i].addActionListener(this);
        }

        reset = new JButton("RESET");
        reset.setBounds(200, 700, 100, 50);
        add(reset);
        reset.addActionListener(this);
    }

    public void check(int num1) 
    {
        for (ii = 0; ii <= 11; ii++)
            for (jj = 1; jj <= 5; jj++) 
                if (a[ii][jj] == num1) 
                    a[ii][6] = 27;
    }

    public void complogic(int num) 
    {
        for (i = 0; i <= 11; i++) 
            for (j = 1; j <= 5; j++)
                if (a[i][j] == num) 
                {
                    a[i][0] = 27;
                    a[i][6] = 26;
                }
        
        for (i = 0; i <= 11; i++) 
        {
            set = true;

            if (a[i][6] == 26) 
            {
                int count = 0;

                for (j = 1; j <= 5; j++) 
                {
                    if (b[(a[i][j] - 1)].getIcon() != null)
                        count++;
                    else
                        yesnull = a[i][j];
                }

                if (count == 4) 
                {
                    b[yesnull - 1].setIcon(ic2);
                    this.check(yesnull);
                    set = false;
                    break;
                }
            } 
            
            else if (a[i][0] == 26) 
            {
                for (j = 1; j <= 5; j++) 
                {
                    if (b[(a[i][j] - 1)].getIcon() == null) 
                    {
                        b[(a[i][j] - 1)].setIcon(ic2);
                        this.check(a[i][j]);
                        set = false;
                        break;
                    }
                }

                if (!set)
                    break;
            }

            if (!set)
                break;
        }
    }

    TicTacToe() 
    {
        super("tic tac toe");

        CheckboxGroup cbg = new CheckboxGroup();
        c1 = new Checkbox("vs computer", cbg, false);
        c2 = new Checkbox("vs friend", cbg, false);
        c1.setBounds(120, 80, 100, 40);
        c2.setBounds(120, 150, 100, 40);
        add(c1);
        add(c2);
        c1.addItemListener(this);
        c2.addItemListener(this);

        state = true;
        type = true;
        set = true;
        ic1 = new ImageIcon("ic1.jpg");
        ic2 = new ImageIcon("ic2.jpg");
        ic11 = new ImageIcon("ic11.jpg");
        ic22 = new ImageIcon("ic22.jpg");

        setLayout(null);
        setSize(660, 900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void itemStateChanged(ItemEvent e) 
    {
        if (c1.getState())
            type = false;
        else if (c2.getState())
            type = true;

        remove(c1);
        remove(c2);
        repaint(0, 0, 660, 900);
        showButton();
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (type) 
        {
            if (e.getSource() == reset) 
            {
                for (i = 0; i <= 24; i++)
                    b[i].setIcon(null);
            } 
            
            else 
            {
                for (i = 0; i <= 24; i++) 
                {
                    if (e.getSource() == b[i]) 
                    {
                        if (b[i].getIcon() == null) 
                        {
                            if (state) 
                            {
                                icon = ic2;
                                state = false;
                            } 
                            else 
                            {
                                icon = ic1;
                                state = true;
                            }
                            b[i].setIcon(icon);
                        }
                    }
                }
            }
        } 
        else 
        {
            if (e.getSource() == reset) 
            {
                for (i = 0; i <= 24; i++)
                    b[i].setIcon(null);

                for (i = 0; i <= 11; i++) 
                {
                    for (j = 0; j <= 6; j++)
                        a[i][j] = a1[i][j];
                }
            } 
            else 
            {
                for (i = 0; i <= 24; i++) 
                {
                    if (e.getSource() == b[i]) 
                    {
                        if (b[i].getIcon() == null) 
                        {
                            b[i].setIcon(ic1);

                            if (b[6].getIcon() == null) 
                            {
                                b[6].setIcon(ic2);
                                this.check(5);
                            } 
                            else 
                            {
                                this.complogic(i);
                            }
                        }
                    }
                }
            }
        }

        for (i = 0; i <= 11; i++) 
        {
            Icon icon1 = b[(a[i][1] - 1)].getIcon();
            Icon icon2 = b[(a[i][2] - 1)].getIcon();
            Icon icon3 = b[(a[i][3] - 1)].getIcon();
            Icon icon4 = b[(a[i][4] - 1)].getIcon();
            Icon icon5 = b[(a[i][5] - 1)].getIcon();

            if ((icon1 == icon2) && (icon2 == icon3) && (icon3 == icon4) && (icon4 == icon5) && (icon1 != null)) 
            {
                if (icon1 == ic1) 
                {
                    b[(a[i][1] - 1)].setIcon(ic11);
                    b[(a[i][2] - 1)].setIcon(ic11);
                    b[(a[i][3] - 1)].setIcon(ic11);
                    b[(a[i][4] - 1)].setIcon(ic11);
                    b[(a[i][5] - 1)].setIcon(ic11);

                    JOptionPane.showMessageDialog(TicTacToe.this, "!!!YOU won!!! click reset");
                    break;
                } 
                else if (icon1 == ic2) 
                {
                    b[(a[i][1] - 1)].setIcon(ic22);
                    b[(a[i][2] - 1)].setIcon(ic22);
                    b[(a[i][3] - 1)].setIcon(ic22);
                    b[(a[i][4] - 1)].setIcon(ic22);
                    b[(a[i][5] - 1)].setIcon(ic22);

                    JOptionPane.showMessageDialog(TicTacToe.this, "!!!AWK (COMPUTER) won!!! click reset");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) 
    {
        new TicTacToe();
    }
}
