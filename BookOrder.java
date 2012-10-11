/*
 * Final Project Marita Patricia (Patty) Zapata G00532697
 * IT308
 * Professor Rajendra
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;

public class BookOrder extends JFrame {

	public static String states[] = { "Alabama", "Alaska", "American Samoa",
			"Arizona", "Arkansas", "California", "Colorado", "Connecticut",
			"Delaware", "District of Columbia", "Florida", "Georgia", "Guam",
			"Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
			"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
			"Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
			"Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
			"New York", "North Carolina", "North Dakota",
			"Northern Marianas Islands", "Ohio", "Oklahoma", "Oregon",
			"Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina",
			"South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
			"Virginia", "Virgin Islands", "Washington", "West Virginia",
			"Wisconsin", "Wyoming" };
	// BILLING
	public JPanel billingAddressPanel;
	public JTextField billingFirstNameTextField;
	public JTextField billingLastNameTextField;
	public JTextField billingStreetTextField;
	public JTextField billingCityTextField;
	public SpinnerModel spinnerModelStateBilling;
	public JSpinner spinnerBilling;
	public JTextField billingZipTextField;

	// CHECKBOX TO MAKE BILLING = SHIPPING
	public JCheckBox addressCheckBox;

	// SHIPPING
	public JPanel shippingAddressPanel;

	public JTextField shippingFirstNameTextField;
	public JTextField shippingLastNameTextField;
	public JTextField shippingStreetTextField;
	public JTextField shippingCityTextField;
	public SpinnerModel spinnerModelStateShipping;
	public JSpinner spinnerShipping;
	public JTextField shippingZipTextField;

	// ORDER DETAILS
	public JPanel orderPanel;
	public JComboBox productsComboBox;
	ArrayList<String> list = new ArrayList<String>();
	public final String products[] = { "Computer Science 101", "History 101",
			"Geography 101", "English 101", "Biology 101", "Algebra 101",
			"Calculus 101" };

	int selectedProduct;
	int orderTotal;
	int sliderValue;
	final int MIN_ORDER = 0;
	final int MAX_ORDER = 5;
	public JFormattedTextField balanceTextField;

	public JSlider quantityPurchase;

	public JRadioButton[] paymentMethod;

	ButtonGroup group = new ButtonGroup();
	public JPasswordField creditCardOrDebitTextField;

	// BUTTONS
	public JButton newButton;
	public JButton editButton;
	public JButton saveButton;
	public JButton cancelButton;

	// SPLIT
	public JSplitPane split;

	// TABLE :-(
	public JPanel orderTablePanel;

	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();

	public TableModel model;
	JTable table;

	Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
	Border redBorder = BorderFactory.createLineBorder(Color.red, 2);

	CheckBoxesHandler checkBoxHandler = new CheckBoxesHandler();

	public BookOrder() {
		setTitle("Book Order");
		setBackground(Color.pink);
		JPanel holdsEverythingPanel = new JPanel();
		holdsEverythingPanel.setLayout(new BorderLayout());
		getContentPane().add(holdsEverythingPanel);

		createBillingAddressPanel();
		createShippingAddressPanel();
		createOrderDetailPanel();

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 245, 238));
		topPanel.add(createBillingAddressPanel());
		topPanel.add(shippingAddressPanel);
		topPanel.add(orderPanel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255, 245, 238));
		newButton = new JButton("New");
		editButton = new JButton("Edit");
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");

		buttonPanel.add(newButton);
		buttonPanel.add(editButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		ButtonHandler handler = new ButtonHandler();
		newButton.addActionListener(handler);
		editButton.addActionListener(handler);
		saveButton.addActionListener(handler);
		cancelButton.addActionListener(handler);

		topPanel.add(buttonPanel, BorderLayout.SOUTH);
		holdsEverythingPanel.add(topPanel);

		JPanel bottomPanel = new JPanel();

		createOrderTablePanel();
		bottomPanel.add(orderTablePanel);
		holdsEverythingPanel.add(bottomPanel);

		split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
		split.setDividerLocation(450 + split.getInsets().top);
		holdsEverythingPanel.add(split);
	}

	public JPanel createBillingAddressPanel() {
		billingAddressPanel = new JPanel();
		billingAddressPanel.setPreferredSize(new Dimension(360, 350));
		billingAddressPanel.setBackground(new Color(255, 245, 238));
		billingAddressPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder(javax.swing.BorderFactory
						.createMatteBorder(1, 1, 1, 1, Color.gray),
						"Billing Information"));
		billingAddressPanel.setLayout(gridBagLayout);
		constraints.insets = new Insets(10, 20, 0, 10);
		JLabel firstNameLabel = new JLabel("First Name");
		constraints.gridx = 0;// column
		constraints.gridy = 1;// row

		gridBagLayout.setConstraints(firstNameLabel, constraints);
		billingAddressPanel.add(firstNameLabel);

		billingFirstNameTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 1;// row
		gridBagLayout.setConstraints(billingFirstNameTextField, constraints);
		billingAddressPanel.add(billingFirstNameTextField);

		JLabel lastNameLabel = new JLabel("Last Name");
		constraints.gridx = 0;// column
		constraints.gridy = 2;// row
		// constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(lastNameLabel, constraints);
		billingAddressPanel.add(lastNameLabel);

		billingLastNameTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 2;// row
		gridBagLayout.setConstraints(billingLastNameTextField, constraints);
		billingAddressPanel.add(billingLastNameTextField);

		JLabel addressLabel = new JLabel("Address");
		constraints.gridx = 0;// column
		constraints.gridy = 3;// row
		// constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(addressLabel, constraints);
		billingAddressPanel.add(addressLabel);
		billingStreetTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 3;// row
		gridBagLayout.setConstraints(billingStreetTextField, constraints);
		billingAddressPanel.add(billingStreetTextField);

		JLabel cityLabel = new JLabel("City");
		constraints.gridx = 0;// column
		constraints.gridy = 4;// row
		// constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(cityLabel, constraints);
		billingAddressPanel.add(cityLabel);
		billingCityTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 4;// row
		gridBagLayout.setConstraints(billingCityTextField, constraints);
		billingAddressPanel.add(billingCityTextField);

		JLabel stateLabel = new JLabel("State");
		constraints.gridx = 0;// column
		constraints.gridy = 5;// row
		// constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(stateLabel, constraints);
		billingAddressPanel.add(stateLabel);

		spinnerModelStateBilling = new SpinnerListModel(states);
		spinnerBilling = new JSpinner(spinnerModelStateBilling);
		Dimension d = spinnerBilling.getPreferredSize();
		d.width = 175;
		spinnerBilling.setPreferredSize(d);
		constraints.gridx = 1;// column
		constraints.gridy = 5;// row
		constraints.ipady = 15;
		// constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(spinnerBilling, constraints);
		billingAddressPanel.add(spinnerBilling);

		JLabel zipLabel = new JLabel("Zip");
		constraints.gridx = 0;// column
		constraints.gridy = 6;// row
		// constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(zipLabel, constraints);
		billingAddressPanel.add(zipLabel);
		billingZipTextField = new JTextField(15);

		billingZipTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				// Border border = BorderFactory.createLineBorder(Color.red, 1);
				JTextField textField = (JTextField) e.getSource();
				String content = textField.getText();
				if (content.length() != 0) {
					try {
						Integer.parseInt(content);
						billingZipTextField.setBorder(border);
					} catch (NumberFormatException nfe) {
						textField.requestFocus();
						billingZipTextField.setBorder(redBorder);
						billingZipTextField.setText("Enter a valid zip code");

					}
				}
			}
		});
		constraints.gridx = 1;// column
		constraints.gridy = 6;// row
		gridBagLayout.setConstraints(billingZipTextField, constraints);
		billingAddressPanel.add(billingZipTextField);

		addressCheckBox = new JCheckBox("Same for Shipping?");
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.gridx = 0;// column
		constraints.gridy = 7;// row
		gridBagLayout.setConstraints(addressCheckBox, constraints);
		addressCheckBox.addItemListener(checkBoxHandler);
		billingAddressPanel.add(addressCheckBox);

		for (Component c : billingAddressPanel.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setBorder(border);
			}
		}
		for (Component c : billingAddressPanel.getComponents()) {
			if (c instanceof JLabel) {
				((JLabel) c).setForeground(Color.DARK_GRAY);
			}
		}
		for (Component c : billingAddressPanel.getComponents()) {
			if (c instanceof JCheckBox) {
				((JCheckBox) c).setForeground(Color.DARK_GRAY);
			}

		}

		return billingAddressPanel;
	}

	public JPanel createShippingAddressPanel() {
		shippingAddressPanel = new JPanel();

		shippingAddressPanel.setBackground(new Color(255, 245, 238));
		shippingAddressPanel.setPreferredSize(new Dimension(360, 350));
		shippingAddressPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray),
				"Shipping Information"));
		shippingAddressPanel.setLayout(gridBagLayout);

		JLabel firstNameLabel = new JLabel("First Name");
		constraints.gridx = 0;// column
		constraints.gridy = 1;// row
		constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(firstNameLabel, constraints);
		shippingAddressPanel.add(firstNameLabel);

		shippingFirstNameTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 1;// row
		gridBagLayout.setConstraints(shippingFirstNameTextField, constraints);
		shippingAddressPanel.add(shippingFirstNameTextField);

		JLabel lastNameLabel = new JLabel("Last Name");
		constraints.gridx = 0;// column
		constraints.gridy = 2;// row
		constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(lastNameLabel, constraints);
		shippingAddressPanel.add(lastNameLabel);

		shippingLastNameTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 2;// row
		gridBagLayout.setConstraints(shippingLastNameTextField, constraints);
		shippingAddressPanel.add(shippingLastNameTextField);

		JLabel addressLabel = new JLabel("Address      ");
		constraints.gridx = 0;// column
		constraints.gridy = 3;// row
		constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(addressLabel, constraints);
		shippingAddressPanel.add(addressLabel);
		shippingStreetTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 3;// row
		gridBagLayout.setConstraints(shippingStreetTextField, constraints);
		shippingAddressPanel.add(shippingStreetTextField);

		JLabel cityLabel = new JLabel("City           ");
		constraints.gridx = 0;// column
		constraints.gridy = 4;// row
		constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(cityLabel, constraints);
		shippingAddressPanel.add(cityLabel);
		shippingCityTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 4;// row
		gridBagLayout.setConstraints(shippingCityTextField, constraints);
		shippingAddressPanel.add(shippingCityTextField);

		JLabel stateLabel = new JLabel("State         ");
		constraints.gridx = 0;// column
		constraints.gridy = 5;// row
		constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(stateLabel, constraints);
		shippingAddressPanel.add(stateLabel);

		spinnerModelStateShipping = new SpinnerListModel(states);
		spinnerShipping = new JSpinner(spinnerModelStateShipping);
		Dimension d = spinnerShipping.getPreferredSize();
		d.width = 175;
		spinnerShipping.setPreferredSize(d);
		constraints.gridx = 1;// column
		constraints.gridy = 5;// row
		constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(spinnerShipping, constraints);
		shippingAddressPanel.add(spinnerShipping);

		JLabel zipLabel = new JLabel("Zip            ");
		constraints.gridx = 0;// column
		constraints.gridy = 6;// row
		constraints.insets = new Insets(10, 0, 0, 0);
		gridBagLayout.setConstraints(zipLabel, constraints);
		shippingAddressPanel.add(zipLabel);
		shippingZipTextField = new JTextField(15);
		constraints.gridx = 1;// column
		constraints.gridy = 6;// row
		gridBagLayout.setConstraints(shippingZipTextField, constraints);
		shippingAddressPanel.add(shippingZipTextField);

		shippingZipTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String content = textField.getText();
				shippingZipTextField.setBorder(border);
				if (content.length() != 0) {
					try {
						Integer.parseInt(content);
						shippingZipTextField.setBorder(border);
					} catch (NumberFormatException nfe) {
						textField.requestFocus();
						shippingZipTextField.setBorder(redBorder);
						shippingZipTextField.setText("Enter a valid zip code");
					}
				}
			}
		});

		JLabel invisible = new JLabel();
		constraints.gridx = 0;// column
		constraints.gridy = 7;// row
		gridBagLayout.setConstraints(invisible, constraints);
		shippingAddressPanel.add(invisible);

		for (Component c : shippingAddressPanel.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setBorder(border);
			}
		}
		for (Component c : shippingAddressPanel.getComponents()) {
			if (c instanceof JLabel) {
				((JLabel) c).setForeground(Color.DARK_GRAY);
			}
		}

		return shippingAddressPanel;
	}

	public void createOrderDetailPanel() {
		orderPanel = new JPanel();
		orderPanel.setBackground(new Color(255, 245, 238));
		orderPanel.setPreferredSize(new Dimension(360, 350));
		orderPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray),
				"Order Details"));
		constraints.insets = new Insets(10, 20, 0, 30);
		orderPanel.setLayout(gridBagLayout);

		JLabel productsToPurchaseLabel = new JLabel("Product");

		constraints.gridx = 0;// column
		constraints.gridy = 0;// row

		constraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagLayout.setConstraints(productsToPurchaseLabel, constraints);
		orderPanel.add(productsToPurchaseLabel);

		productsComboBox = new JComboBox(products);
		constraints.insets = new Insets(10, 20, 0, 30);
		constraints.gridx = 1;// column
		constraints.gridy = 0;// row
		constraints.fill = GridBagConstraints.BOTH;
		gridBagLayout.setConstraints(productsComboBox, constraints);
		orderPanel.add(productsComboBox);

		productsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				productsComboBox = (JComboBox) ae.getSource();
				selectedProduct = productsComboBox.getSelectedIndex();
				quantityPurchase.setValue(0);
			}
		});

		JLabel qtyBooksLabel = new JLabel("How many?");
		constraints.gridx = 0;// column
		constraints.gridy = 1;// row

		gridBagLayout.setConstraints(qtyBooksLabel, constraints);
		orderPanel.add(qtyBooksLabel);

		quantityPurchase = new JSlider(JSlider.HORIZONTAL, MIN_ORDER,
				MAX_ORDER, 0);
		constraints.insets = new Insets(10, 20, 0, 30);
		quantityPurchase
				.setLabelTable(quantityPurchase.createStandardLabels(1));
		constraints.gridx = 1;// column
		constraints.gridy = 1;// row
		gridBagLayout.setConstraints(quantityPurchase, constraints);
		orderPanel.add(quantityPurchase);
		quantityPurchase.setMajorTickSpacing(5);
		quantityPurchase.setMinorTickSpacing(1);
		quantityPurchase.setPaintTicks(true);
		quantityPurchase.setPaintLabels(true);
		quantityPurchase.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					sliderValue = (int) source.getValue();
					bookPriceSelected();
					NumberFormat formattedOrderTotal = NumberFormat
							.getInstance();
					balanceTextField.setText(""
							+ formattedOrderTotal.format(orderTotal));
				}
			}
		});

		JLabel totalLabel = new JLabel("Total");
		constraints.gridx = 0;// column
		constraints.gridy = 2;// row
		constraints.insets = new Insets(10, 20, 0, 30);
		gridBagLayout.setConstraints(totalLabel, constraints);
		orderPanel.add(totalLabel);

		balanceTextField = new JFormattedTextField("");
		constraints.gridx = 1;// column
		constraints.gridy = 2;// row
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.0;

		gridBagLayout.setConstraints(balanceTextField, constraints);
		orderPanel.add(balanceTextField);
		balanceTextField.setEditable(false);

		JLabel paymentMethodLabel = new JLabel("Method");
		constraints.insets = new Insets(10, 20, 0, 30);
		constraints.gridx = 0;// column
		constraints.gridy = 3;// row
		gridBagLayout.setConstraints(paymentMethodLabel, constraints);
		constraints.fill = GridBagConstraints.EAST;
		orderPanel.add(paymentMethodLabel);

		final JRadioButton[] paymentMethod = new JRadioButton[2];
		paymentMethod[0] = new JRadioButton("Credit");
		constraints.gridx = 1;// column
		constraints.gridy = 3;// row
		constraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagLayout.setConstraints(paymentMethod[0], constraints);
		paymentMethod[1] = new JRadioButton("Debit");
		constraints.gridx = 1;// column
		constraints.gridy = 4;// row
		gridBagLayout.setConstraints(paymentMethod[1], constraints);

		// paymentMethod[0].setActionCommand("Credit");
		// paymentMethod[1].setActionCommand("Debit");

		for (int i = 0; i < paymentMethod.length; i++) {
			group.add(paymentMethod[i]);
			orderPanel.add(paymentMethod[i]);
		}

		JLabel creditCardLabel = new JLabel("Acc. #");
		orderPanel.add(creditCardLabel);

		creditCardOrDebitTextField = new JPasswordField();
		creditCardOrDebitTextField.setForeground(Color.gray);
		orderPanel.add(creditCardOrDebitTextField);
		constraints.insets = new Insets(10, 20, 0, 30);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		orderPanel.add(creditCardLabel);
		constraints.gridx = 0;// column
		constraints.gridy = 5;// row
		gridBagLayout.setConstraints(creditCardLabel, constraints);
		constraints.gridx = 1;// column
		constraints.gridy = 5;// row
		gridBagLayout.setConstraints(creditCardOrDebitTextField, constraints);

		for (Component c : orderPanel.getComponents()) {
			if (c instanceof JTextField) {
				((JTextField) c).setBorder(border);
			}
		}
		for (Component c : orderPanel.getComponents()) {
			if (c instanceof JLabel) {
				((JLabel) c).setForeground(Color.DARK_GRAY);
			}
		}
		for (Component c : orderPanel.getComponents()) {
			if (c instanceof JRadioButton) {
				((JRadioButton) c).setForeground(Color.DARK_GRAY);
			}
		}
	}

	public void createOrderTablePanel() {
		orderTablePanel = new JPanel();
		model = new TableModel();
		table = new Table();
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(1180, 600));
		orderTablePanel.add(jsp);
	}

	public int bookPriceSelected() {
		switch (selectedProduct) {
		case 0:
			return orderTotal = sliderValue * 100;
		case 1:
			return orderTotal = sliderValue * 200;
		case 2:
			return orderTotal = sliderValue * 300;
		case 3:
			return orderTotal = sliderValue * 400;
		case 4:
			return orderTotal = sliderValue * 500;
		case 5:
			return orderTotal = sliderValue * 600;
		case 6:
			return orderTotal = sliderValue * 700;
		default:
			return 0;
		}
	}

	public static void main(String args[]) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.getLookAndFeelDefaults().put("defaultFont",
					new Font("Book Antiqua", Font.PLAIN, 12));
		} catch (Exception e) {
			e.printStackTrace();
		}
		BookOrder mainFrame = new BookOrder();
		mainFrame.pack();
		mainFrame.setSize(1180, 1000);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int selectedRow = table.getSelectedRow();
			String str = e.getActionCommand();
			if (str.equals("New") || (str.equals("Cancel"))) {
				billingFirstNameTextField.setText("");
				billingLastNameTextField.setText("");
				billingStreetTextField.setText("");
				billingCityTextField.setText("");
				spinnerModelStateBilling.setValue(states[0]);
				billingZipTextField.setText("");
				shippingFirstNameTextField.setText("");
				shippingLastNameTextField.setText("");
				shippingStreetTextField.setText("");
				shippingCityTextField.setText("");
				spinnerModelStateShipping.setValue(states[0]);
				shippingZipTextField.setText("");
				productsComboBox.setSelectedIndex(0);
				quantityPurchase.setValue(0);
				balanceTextField.setText("");
				group.clearSelection();
				creditCardOrDebitTextField.setText("");
				addressCheckBox.setSelected(false);
			}

			if (str.equals("Edit")) {
				billingFirstNameTextField.setText(model.getValueAt(selectedRow,
						0).toString());
				billingLastNameTextField.setText(model.getValueAt(selectedRow,
						1).toString());
				billingStreetTextField.setText(model.getValueAt(selectedRow, 2)
						.toString());
				billingCityTextField.setText(model.getValueAt(selectedRow, 3)
						.toString());
				spinnerModelStateBilling.setValue(model.getValueAt(selectedRow,
						4));
				billingZipTextField.setText(model.getValueAt(selectedRow, 5)
						.toString());
				productsComboBox.setSelectedItem(model.getValueAt(selectedRow,
						6));
				quantityPurchase.setValue((Integer) model.getValueAt(
						selectedRow, 7));
				balanceTextField.setText(model.getValueAt(selectedRow, 8)
						.toString());
			}
			if (str.equals("Save") && (selectedRow == -1)) {
				int response = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to submit this order?",
						"Confirm", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.YES_OPTION) {

					model.addRow(new OrderDetail(billingFirstNameTextField
							.getText(), billingLastNameTextField.getText(),
							billingStreetTextField.getText(),
							billingCityTextField.getText(),
							spinnerModelStateBilling.getValue().toString(),
							billingZipTextField.getText(),
							String.valueOf(productsComboBox.getSelectedItem()),
							quantityPurchase.getValue(), balanceTextField
									.getText()));
				}
			}

			if (str.equals("Save") && (selectedRow >= 0)) {
				model.changedRow(
						new OrderDetail(billingFirstNameTextField.getText(),
								billingLastNameTextField.getText(),
								billingStreetTextField.getText(),
								billingCityTextField.getText(),
								spinnerModelStateBilling.getValue().toString(),
								billingZipTextField.getText(), String
										.valueOf(productsComboBox
												.getSelectedItem()),
								quantityPurchase.getValue(), balanceTextField
										.getText()), selectedRow);
			}
		}
	}

	class CheckBoxesHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent ie) {

			String firstName = billingFirstNameTextField.getText();
			String lastName = billingLastNameTextField.getText();
			String street = billingStreetTextField.getText();
			String city = billingCityTextField.getText();
			String state = spinnerModelStateBilling.getValue().toString();
			String zip = billingZipTextField.getText();

			if (addressCheckBox.isSelected()) {
				shippingFirstNameTextField.setText(firstName);
				shippingLastNameTextField.setText(lastName);
				shippingStreetTextField.setText(street);
				shippingCityTextField.setText(city);
				spinnerModelStateShipping.setValue(state);
				shippingZipTextField.setText(zip);
			} else {
				shippingFirstNameTextField.setText("");
				shippingLastNameTextField.setText("");
				shippingStreetTextField.setText("");
				shippingCityTextField.setText("");
				spinnerModelStateShipping.setValue(states[0]);
				shippingZipTextField.setText("");
			}
		}
	}

	class Table extends JTable {
		public Component prepareRenderer(TableCellRenderer renderer, int row,
				int column) {
			Component returnComp = super.prepareRenderer(renderer, row, column);
			Color alternateColor = new Color(255, 245, 238);
			Color grayColor = new Color(233, 233, 233);
			if (!returnComp.getBackground().equals(getSelectionBackground())) {
				Color bg = (row % 2 == 0 ? alternateColor : grayColor);
				returnComp.setBackground(bg);
				bg = null;
			}
			return returnComp;
		};
	}
}
