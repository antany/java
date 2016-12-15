package in.antany.downloader.ui.component;

import in.antany.downloader.ui.DownloaderTableModel;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DLProgressBar extends JProgressBar implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rowNumber;
	private int columnNumber = 4;
	private DownloaderTableModel dtm= null;

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getRowNumber() {
		return this.rowNumber;

	}

	public DLProgressBar(DownloaderTableModel dtm) {
		super(0, 100);
		this.dtm = dtm;
		setValue(0);
		setString("0%");
		setStringPainted(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		if (value instanceof DLProgressBar) {
			return (DLProgressBar) value;
		} else {
			return null;
		}
	}
	
	public void setDLProgress(int value){
		setValue(value);
		setString(value+"%");
		setStringPainted(true);
		dtm.fireTableCellUpdated(rowNumber-1, columnNumber);
	}

}
