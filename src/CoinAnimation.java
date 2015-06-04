import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Bilel on 4-6-2015.
 */
public class CoinAnimation extends Rectangle2D.Double {

    // CoinAnimatie.
    // Gewoon 'paint(Graphics2D) aanroepen, dan wordt automatisch ook de update ook verwerkt.

    // Deze klasse extends Rectangle2D, de container van de munt zelf
    private BufferedImage coinImage;
    private TexturePaint paint;
    private Rectangle2D.Double coinContainer;
    private Point2D.Double coinSetPoint = new Point2D.Double(0, 0);

    // Animatie Timer
    Timer timer;

    CoinAnimation() {
        initCoins();
    }

    public void paint(Graphics2D g2) {
        g2.setPaint(paint);
        g2.fill(coinContainer);

        update();
    }

    public void update() {
        // Coin locatie opnieuw installen
        coinSetPoint.setLocation(coinSetPoint.getX(), coinSetPoint.getY() + 1);
        // Frame steeds aanpassen aan locatie van coin (Zodat hele coin wel te zien blijft)
        coinContainer.setFrame(coinSetPoint.getX(), coinImage.getWidth() - (coinSetPoint.getY() % coinImage.getWidth()),
                coinImage.getWidth(), coinImage.getHeight());
        // Opnieuw samenvoegen
        paint = new TexturePaint(coinImage, coinContainer);
    }

    // Coin opzetten
    public void initCoins() {
        try {
            coinImage = ImageIO.read(getClass().getResource("Coin.png"));
            coinContainer = new Rectangle2D.Double(coinSetPoint.getX(), coinSetPoint.getY(), coinImage.getWidth(), coinImage.getHeight());
            paint = new TexturePaint(coinImage, new Rectangle2D.Double(0, 0, coinImage.getWidth(), coinImage.getHeight()));
        } catch (IOException i) {
        }
    }
}