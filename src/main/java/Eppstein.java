import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.EppsteinPowerLawGenerator;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;

public class Eppstein {
	private static Eppstein Eppstein;
	static JLabel label;
	static int e;
	static Factory edgeFactory = new Factory<String>() {
		int i;

		public String create() {
			return new Integer(i++).toString();
		}
	};
	static Factory vertexFactory = new Factory<Integer>() {
		int i;

		public Integer create() {
			return new Integer(i++);
		}
	};
	static Factory<Graph> graphFactory = new Factory<Graph>() {
		public Graph create() {
			return new UndirectedSparseGraph();
		}
	};

	private static Graph Eppstein(int numVertices, int numEdges, int r) {
		EppsteinPowerLawGenerator gn = new EppsteinPowerLawGenerator(graphFactory, vertexFactory, edgeFactory,
				numVertices, numEdges, r);
		return gn.create();
	}
	

	public static void main(String[] args) {
		
		Graph<Integer, String> g = Eppstein(10, 20, 10000);
		Layout<Integer, String> layout = new FRLayout(g);
		layout.setSize(new Dimension(450, 450));
		VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
		vv.setPreferredSize(new Dimension(500, 500));
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(ModalGraphMouse.Mode.PICKING);
		vv.setGraphMouse(gm);
		final JTextField latticeSize = new JTextField("2", 4);
		final JTextField latticeSize1 = new JTextField("2", 4);
		final JTextField latticeSize2 = new JTextField("2", 4);
		JPanel panel = new JPanel();
		JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				int numVertices = Integer.parseInt(latticeSize.getText());
				int numEdges = Integer.parseInt(latticeSize1.getText());
				int r =Integer.parseInt(latticeSize2.getText());

				Graph<Integer, String> g = Eppstein(numVertices, numEdges, r);
				Layout<Integer, String> layout = new FRLayout(g);
				layout.setSize(new Dimension(400, 400));
				VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
				panel.add(vv);
				JFrame frame = new JFrame("View 1");

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(panel);
				frame.pack();
				frame.setVisible(true);
			}
		});

		panel.add(vv);
		panel.add(new JLabel("Выберите вершину\n"));
		panel.add(latticeSize);
		panel.add(latticeSize1);
		panel.add(latticeSize2);
		panel.add(generateButton);
		JFrame frame = new JFrame("Interactive Graph View 1");
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);


	}

}