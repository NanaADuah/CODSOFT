import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ATMView {
    private JPanel rootPanel;
    private JTextArea txtAreaText;
    private JButton btnViewBalance;
    private JButton btnConfirm;
    private JButton btnWidthdraw;
    private JButton btnDeposit;
    private JPanel panelBalance;
    private JLabel balanceLabel;
    private JButton btnSeven;
    private JButton btnFour;
    private JButton btnOne;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnSix;
    private JButton btnFive;
    private JButton btnEight;
    private JButton btnNine;
    private JButton btnCancel;
    private JButton btnZero;
    private JButton btnAccept;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelKeypad;
    private JPanel panelRight;
    private JPanel panelView;
    private JPanel panelWithdraw;
    private JLabel labelWithdraw;
    private JPanel panelDeposit;
    private JLabel labelDeposit;
    private JPanel panelDefault;
    private JButton btnEnterCard;
    private JPanel panelCard;
    private JLabel lblAccountNumber;
    private JLabel lblAccountType;
    private JLabel lblEnterPin;
    private JLabel lblPin;
    private JPanel panelPin;
    private JLabel lblInvalidPin;
    private String InputValue = "0";
    private boolean inputEnabled = false;
    String CurrentView = "Default";
    protected ATM atm = new ATM();

    public void appendView(String value){
        if(inputEnabled) {
            if(InputValue.length() < 6) {         //prevent overflow of characters
                InputValue += value;
                UpdateCurrentView();
            }
        }
    }

    public void UpdateCurrentView(){

        if(!atm.isCardEntered())
        {
            View(false);
        }else
        {
            if(CurrentView.equalsIgnoreCase("Pin")) {
                panelDefault.setVisible(false);
                panelDeposit.setVisible(false);
                panelBalance.setVisible(false);
                panelWithdraw.setVisible(true);
                panelKeypad.setVisible(true);
                panelPin.setVisible(true);

                String[] temp = InputValue.split("");
                StringBuilder tempOutput = new StringBuilder();

                for (String s : temp) {
                    if(!s.isEmpty())
                        tempOutput.append("*");
                }
                lblPin.setText(tempOutput.toString());
            }else
            {
                View(true);
                panelPin.setVisible(false);
                if(CurrentView.equalsIgnoreCase("Withdraw")) {
                    panelDefault.setVisible(false);
                    panelDeposit.setVisible(false);
                    panelBalance.setVisible(false);
                    panelWithdraw.setVisible(true);
                    labelWithdraw.setText(String.format("R%.2f", Double.parseDouble(InputValue)));
                }else
                if(CurrentView.equalsIgnoreCase("Deposit")){
                    panelDefault.setVisible(false);
                    panelDeposit.setVisible(true);
                    panelBalance.setVisible(false);
                    panelWithdraw.setVisible(false);
                    labelDeposit.setText(String.format("R%.2f", Double.parseDouble(InputValue)));
                }else
                if(CurrentView.equalsIgnoreCase("Balance")){
                    panelDefault.setVisible(false);
                    panelDeposit.setVisible(false);
                    panelBalance.setVisible(true);
                    panelWithdraw.setVisible(false);
                    balanceLabel.setText(String.format("R%.2f",atm.getUserBalance()));
                    lblAccountNumber.setText(String.format("Account number: %s", atm.getUserAccountNumber()));
                    lblAccountType.setText(String.format("Account Type: %s", atm.getUserAccountType()));
                }else
                if(CurrentView.equalsIgnoreCase("Default")){
                    panelDefault.setVisible(true);
                    panelDeposit.setVisible(false);
                    panelBalance.setVisible(false);
                    panelWithdraw.setVisible(false);
                }
            }
        }
    }

    public void View(boolean value)
    {
        panelLeft.setVisible(value);
        panelRight.setVisible(value);
        panelTop.setVisible(value);
        panelView.setVisible(value);
        panelCard.setVisible(!value);
    }
    public ATMView() {
        //UserAccount user = new UserAccount();  /** not used anymore, but can be implemented in constructor however **/

        UpdateCurrentView();

        if(!atm.isCardEntered())
        {
            View(false);
            btnEnterCard.setVisible(true);
            panelPin.setVisible(false);
        }

        btnViewBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentView = "Balance";
                inputEnabled = false;
                UpdateCurrentView();
            }
        });

        btnWidthdraw.addActionListener(e -> {
            inputEnabled = true;
            InputValue = "0";
            CurrentView = "Withdraw";
            UpdateCurrentView();
        });

        btnZero.addActionListener(e -> appendView("0"));
        btnOne.addActionListener(e -> appendView("1"));
        btnTwo.addActionListener(e -> appendView("2"));
        btnThree.addActionListener(e -> appendView("3"));
        btnFour.addActionListener(e -> appendView("4"));
        btnFive.addActionListener(e -> appendView("5"));
        btnSix.addActionListener(e -> appendView("6"));
        btnSeven.addActionListener(e -> appendView("7"));
        btnEight.addActionListener(e -> appendView("8"));
        btnNine.addActionListener(e -> appendView("9"));
        btnDeposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputEnabled = true;
                InputValue = "0";
                CurrentView = "Deposit";
                UpdateCurrentView();
            }
        });
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputEnabled = false;
                CurrentView = "Default";
                atm.removeCard();
                panelCard.setVisible(true);
                panelPin.setVisible(false);
                btnEnterCard.setVisible(true);
                lblInvalidPin.setVisible(false);
                InputValue = "";
                UpdateCurrentView();
            }
        });
        btnEnterCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atm.enterCard();
                CurrentView = "Pin";
                lblEnterPin.setVisible(true);
                btnEnterCard.setVisible(false);
                InputValue = "";
                inputEnabled = true;
                UpdateCurrentView();
                //View(true);
            }

        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = InputValue.length();
                int minLength = CurrentView.equals("Pin") ? 0 : 1;

                if(length == minLength)
                {
                    if(!CurrentView.equals("Pin"))
                        InputValue = "0";
                }
                else
                if(length > minLength)
                {
                    InputValue = InputValue.substring(0, length-1);
                }
                UpdateCurrentView();
            }
        });

        btnAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(inputEnabled)
                {
                    if(CurrentView.equalsIgnoreCase("Pin"))
                    {
                        if(InputValue.length() == 4) {
                            CurrentView = "Default";
                            atm.setPinEntered(true);
                            lblInvalidPin.setVisible(false);
                        }
                        else
                        {
                            lblInvalidPin.setVisible(true);
                            InputValue = "";
                        }
                        UpdateCurrentView();
                    } else
                    {
                        inputEnabled = false;
                        try {
                            float amount = Float.parseFloat(InputValue);
                            if(CurrentView.equalsIgnoreCase("Withdraw"))
                            {
                                String result = atm.withdraw(amount);
                                labelWithdraw.setText(result);

                            }
                            else {
                                if(CurrentView.equalsIgnoreCase("Deposit")) {
                                    String result = atm.deposit(amount);
                                    labelDeposit.setText(result);
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            labelDeposit.setText("An error occurred. Try again later");
                            labelWithdraw.setText("An error occurred. Try again later");
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM");
        frame.setContentPane(new ATMView().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
