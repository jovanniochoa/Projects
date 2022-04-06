//import java.lang.*; // by default imported
import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

/**********************
 * @author jovanniochoa
 * 1. Pseudocode
 * 2. Source file:.Icecream.java
 * 3. Object code file: Icecream
 * 4. runtine --> vm --> machine code
 */
// public class Icecream extends Object{
public class Icecream{

	public static void main(String[] args) {
		// class|     reference var|memory allocation|object
		IceCreamParlor myIceCreamParlor = new IceCreamParlor(); // constructor method
		myIceCreamParlor.setTitle("Welcome to 2336 Ice Cream Parlor");
		myIceCreamParlor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myIceCreamParlor.setVisible(true);
		//myIceCreamParlor.setSize(200,300);
	}

}

// extends with keyword extends

class IceCreamParlor extends JFrame{
	private static final long serialVersionUID = 1L;
	IceCreamFlavorPanel flvrPanel;
	IceCreamTypePanel typePanel;
	
	public IceCreamParlor() {
		flvrPanel = new IceCreamFlavorPanel();
		typePanel = new IceCreamTypePanel();
		
		add(typePanel, BorderLayout.WEST);
		add(flvrPanel, BorderLayout.EAST);

		pack();
	}
}

class IceCreamTypePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JLabel scoopLabel;
	JRadioButton sglScoopRB, dblScoopRb, trplScoopRB;
	JRadioButton sundaeRB, coneRB, banSplitRB, softYogRB;
	JComboBox<String> sundaeComBox;
	JComboBox<String> scoopComBox;
	ButtonGroup typeGroup;
	ButtonGroup scoopGroup;
	
	public IceCreamTypePanel() {
		String[] sundaeTypes = {"Pick type of Sundae",
								"Black Forest Sundae",
								"Brownie Fudge Sundae",
								"Candy Bar Sundae",
								"Hot Caramel Sundae",
								"Hot Fudge Sundae",
								"Strawberry Shortcake Sundae",
								"Tuxedo Sundae"};
		
		String[] scoopTypes = {"Pick tnumber of Scoops",
				"Single Scoop",
				"Double Scoop",
				"Tripple Scoop"};
		
		sundaeComBox = new JComboBox<>(sundaeTypes);
		scoopComBox = new JComboBox<>(scoopTypes);
		sundaeRB = new JRadioButton("SUNDAES");
		banSplitRB = new JRadioButton("BANANA SPLITt");
		coneRB = new JRadioButton("ICE CREAM CONES");
		softYogRB = new JRadioButton("SOFT FROZEN YOGURT");
		
		typeGroup = new ButtonGroup();
		typeGroup.add(sundaeRB);
		typeGroup.add(banSplitRB);
		typeGroup.add(coneRB);
		typeGroup.add(softYogRB);
		
		scoopLabel = new JLabel("Number of Scoops:");
		sglScoopRB = new JRadioButton("Single Scoop");
		dblScoopRb = new JRadioButton("Double Scoop");
		trplScoopRB = new JRadioButton("Tripple Scoop");
		
		scoopGroup = new ButtonGroup();
		scoopGroup.add(sglScoopRB);
		scoopGroup.add(dblScoopRb);
		scoopGroup.add(trplScoopRB);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		int x = 0;
		int y = 0;
		
		add(banSplitRB, c, x++, y);
		add(coneRB, c, x++, y);
		add(softYogRB, c, x, y);
		x = 0;
		y = 1;
		add(sundaeRB, c, x++, y);
		add(sundaeComBox, c, x, y);
		
		x = 0;
		y = 2;
		c.insets = new Insets(20, 0, 0, 0); // puts a space before
		add(scoopLabel, c , x++, y);
		add(scoopComBox, c , x++, y);
		
		/*
		x = 0;
		y = 2;
		c.insets = new Insets(20, 0, 0, 0); // puts a space before
		add(scoopLabel, c , x++, y);
		add(sglScoopRB, c , x++, y);
		add(dblScoopRb, c , x++, y);
		add(trplScoopRB, c , x++, y);
		*/
				
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Try one of our famous flavors"));
	}
	
	public void add(JComponent obj, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		add(obj, c);
	}
}

class IceCreamFlavorPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	JCheckBox chocChkBox, bPecanChkBox, cDoughChkBox, mintChkBox, vanChkBox, cCreamChkBox;
	
	public IceCreamFlavorPanel() {
		chocChkBox = new JCheckBox("Chocolate");
		bPecanChkBox = new JCheckBox("Butter Pecan");
		cDoughChkBox = new JCheckBox("Cookie Dough");
		mintChkBox = new JCheckBox("Mint");
		vanChkBox = new JCheckBox("Vanilla");
		cCreamChkBox = new JCheckBox("Cookies and Cream");
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		int x = 0;
		int y = 0;
		add(chocChkBox, c , x, y++);
		add(bPecanChkBox, c , x, y++);
		add(cDoughChkBox, c , x, y++);
		x = 1;
		y = 0;
		add(mintChkBox, c , x, y++);
		add(vanChkBox, c , x, y++);
		add(cCreamChkBox, c , x, y++);
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select Flavors"));
		
	}
	
	public void add(JCheckBox obj, GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		add(obj, c);
	}
}