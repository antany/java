package in.antany.downloader.ui;

import in.antany.downloader.util.dto.DownloadDto;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DownloaderTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "No", "URL", "Size", "MS", "Progress",
			"Status" };
	private ArrayList<DownloadDto> data = new ArrayList<DownloadDto>();

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (data.size() > rowIndex) {
			switch (columnIndex) {
			case 0:
				return data.get(rowIndex).getNo();
			case 1:
				return data.get(rowIndex).getUrl();
			case 2:
				return data.get(rowIndex).getSize();
			case 3:
				return data.get(rowIndex).isMultiDownloadSupport();
			case 4:
				return data.get(rowIndex).getProgressBar();
			case 5:
				return data.get(rowIndex).getStatus();
			default:
				return 0;
			}
		} else {
			return 0;
		}

	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public void addRow(DownloadDto dw){
		data.add(dw);
		fireTableRowsInserted(0, data.size());
	}
	
	
}
