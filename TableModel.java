/*
 * Final Project Marita Patricia (Patty) Zapata G00532697
 * IT308
 * Professor Rajendra
 */

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableModel extends AbstractTableModel {

	private List<OrderDetail> orders = new ArrayList<OrderDetail>();

	@Override
	public int getRowCount() {
		return orders.size();
	}

	public void addRow(OrderDetail order) {
		orders.add(order);
		fireTableRowsInserted(orders.size() - 1, orders.size());
	}
	
	public void changedRow(OrderDetail order, int selectedRow){
		orders.set(selectedRow, order);
		fireTableDataChanged();
	}

	private String[] columnNames = { "First Name", "Last Name", "Address",
			"City", "State", "Zip", "Book Ordered", "Quantity", "Balance"};

	public String getColumnName(int i) {
		return columnNames[i];
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		OrderDetail orderDetail = orders.get(row);
		switch (col) {
		case 0:
			return orderDetail.getBillingFirstName();
		case 1:
			return orderDetail.getBillingLastName();
		case 2:
			return orderDetail.getBillingStreet();
		case 3:
			return orderDetail.getBillingCity();
		case 4:
			return orderDetail.getBillingState();
		case 5:
			return orderDetail.getBillingZip();
		case 6:
			return orderDetail.getBookSelected();
		case 7:
			return orderDetail.getQuantity();
		case 8:
			return orderDetail.getBalance();
		default:
			return null;
		}
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
}
