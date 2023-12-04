import javax.swing.*;

public class MapFrame extends JFrame implements TaxiListener {

    private MapPanel mapPanel;

    MapFrame() {
        this.mapPanel = new MapPanel(new Map(), this);
        this.add(mapPanel);
        this.setTitle("Map");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        // Set this MapFrame as the TaxiListener for the mapPanel
        mapPanel.setTaxiListener(this);
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    @Override
    public void onCollectPressed() {
        Collection.getClosestTaxi(mapPanel.getTaxis());
    }
}
