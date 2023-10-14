package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.ShowAllController;
import model.CheckoutEntry;
import model.CheckoutRecord;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberCheckOutEntryView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CheckoutRecord checkoutRecord;
	private JTable jtbRecordDetail;
	private String[][] data;
	private LibraryMemberDetailsView memberHistoryView;

	/**
	 * Create the frame.
	 */
	public MemberCheckOutEntryView(LibraryMemberDetailsView memberHistoryView, CheckoutRecord checkoutRecord) {
		this.memberHistoryView = memberHistoryView;
		this.checkoutRecord = checkoutRecord;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 566, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		jtbRecordDetail = new JTable();
		jtbRecordDetail.setBounds(44, 220, 0, 0);
		contentPane.add(jtbRecordDetail);

		String[] column = { "ISBN", "Book Title", "Book Copy Number", "Check Out Date", "Due Date" };
		data = showCheckoutRecordDetails();
		jtbRecordDetail = new JTable(data, column);
		jtbRecordDetail.setBounds(10, 40, 400, 200);
		// make jtable not to editable
		jtbRecordDetail.setDefaultEditor(Object.class, null);

		JScrollPane sp = new JScrollPane(jtbRecordDetail);
		sp.setLocation(10, 40);
		sp.setSize(550, 200);
		contentPane.add(sp);

		JLabel lblNewLabel = new JLabel("Checkout Record Details");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 550, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOK_click();
			}
		});
		btnOk.setBounds(237, 252, 117, 29);
		contentPane.add(btnOk);
	}

	protected void btnOK_click() {
		memberHistoryView.setVisible(true);
		this.dispose();
	}

	private String[][] showCheckoutRecordDetails() {
		String[][] result = new String[checkoutRecord.getCheckoutEntries().size()][5];
		int i=0;
		for (CheckoutEntry ce : checkoutRecord.getCheckoutEntries()) {
			result[i][0] = ce.getCheckedOutbook().getBook().getIsbnNumber();
			result[i][1] = ce.getCheckedOutbook().getBook().getTitle();
			result[i][2] = ce.getCheckedOutbook().getUniqueCopyNumber()+"";
			result[i][3] = ce.getCheckOutDate().toString();
			result[i][4] = ce.getDueDate().toString();
			i++;
		}
		return result;
	}

}
