package COIS717;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author WangWei MB45444: Java program that implements the seat reservation
 *         system of exercise 4.3.
 */
public class SeatReservation extends JFrame {
	private static final long serialVersionUID = 4625360666143965698L;
	private int noOfSeats = 20;
	private int noOfClerks = 5;
	private JTextField tfSeats;
	private JTextField tfClerks;
	private Seats seatsINS;
	private DisplayPanel seatsINS_D;
	private JPanel displayPnl;
	private Hashtable<Integer, DisplayPanel> dpClerk = new Hashtable<Integer, DisplayPanel>();

	/** Constructor to setup the GUI components */
	public SeatReservation() {
		this.setBounds(100, 100, 500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Seat Reservation for exercise 4.4");
		this.setBackground(Color.lightGray);
		displayPnl = new JPanel(new FlowLayout());
		seatsINS_D = new DisplayPanel("SeatsInstance");

		JPanel btnPanel = new JPanel(new FlowLayout());
		btnPanel.add(new JLabel("No Of Seats:"));
		tfSeats = new JTextField(noOfSeats + "", 5);
		btnPanel.add(tfSeats);

		btnPanel.add(new JLabel("No Of Clerks:"));
		tfClerks = new JTextField(noOfClerks + "", 5);
		btnPanel.add(tfClerks);

		JButton btnStart = new JButton("Start");
		btnPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				noOfSeats = Integer.parseInt(tfSeats.getText());
				noOfClerks = Integer.parseInt(tfClerks.getText());

				displayPnl.removeAll();
				displayPnl.add(seatsINS_D);
				for (int i = 1; i <= noOfClerks; i++) {
					dpClerk.put(i,
							new DisplayPanel(String.format("Clerk_%d", i),
									Color.green));
					displayPnl.add(dpClerk.get(i));
				}

				getContentPane().add(displayPnl);

				seatsINS = new Seats(seatsINS_D, noOfSeats);
				for (Iterator<Integer> it = dpClerk.keySet().iterator(); it
						.hasNext();) {

					Integer key = it.next();
					DisplayPanel currentDC = dpClerk.get(key);
					Clerk currentClerk = new Clerk(currentDC, seatsINS,
							key * 100);
					currentClerk.start();
				}
			}
		});

		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(btnPanel, BorderLayout.NORTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		// Run GUI codes in Event-Dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SeatReservation(); // Let the constructor do the job
			}
		});
	}
}

class Seats {
	DisplayPanel display;
	int availableSeats = 0;

	Seats(DisplayPanel dc, int setSeats) {
		display = dc;
		availableSeats = setSeats;
	}

	public synchronized Boolean take() {
		if (availableSeats > 0) {
			int temp = availableSeats; // read[v]
			Simulate.HWinterrupt();
			availableSeats = temp - 1; // write[v-1]
			display.setvalue(availableSeats);
			System.out.println(String.format("availableSeats:%d",
					availableSeats));
			return true;
		} else {
			return false;
		}
	}
}

class Clerk extends Thread {
	DisplayPanel display;
	Seats seat;
	int sleepTime = 0;
	int takeNo = 0;

	Clerk(DisplayPanel dc, Seats ss, int setSleepTime) {
		display = dc;
		seat = ss;
		sleepTime = setSleepTime;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(sleepTime);
				if (!seat.take())
					break;
				takeNo++;
				display.setvalue(takeNo);
			}
		} catch (InterruptedException e) {
		} catch (Exception e) {
		}
	}
}

class Simulate {
	public static void HWinterrupt() {
		if (Math.random() < 0.325)
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		;
		// used instead of Thread.yield() to ensure portability
	}
}

class DisplayPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = -9057887085942675833L;

	private JLabel lblValue;
	Font f1 = new Font("Helvetica", Font.BOLD, 14);
	Font f2 = new Font("Times", Font.ITALIC + Font.BOLD, 10);

	public DisplayPanel(String title) {
		this(title, Color.cyan);
	}

	public DisplayPanel(String title, Color c) {
		this(title, Color.cyan, 100, 50);
	}

	public DisplayPanel(String title, Color c, int width, int height) {
		super();
		setBackground(c);
		this.setSize(width, height);
		this.setLayout(new BorderLayout());
		JLabel jlbTitle = new JLabel(title);
		jlbTitle.setBounds(10, 40, 120, 30);
		jlbTitle.setFont(f1);
		this.add(jlbTitle, BorderLayout.NORTH);

		lblValue = new JLabel("0");
		lblValue.setBounds(55, 40, 120, 30);
		this.add(lblValue, BorderLayout.SOUTH);
	}

	public void setcolor(Color c) {
		setBackground(c);
		repaint();
	}

	public void setvalue(int newval) {
		lblValue.setText(String.valueOf(newval));

	}
}