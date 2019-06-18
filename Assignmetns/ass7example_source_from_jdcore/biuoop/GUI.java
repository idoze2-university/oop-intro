package biuoop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;









public class GUI
{
  private KeyboardSensorImpl keyboardSensor;
  private DialogManagerImpl dialogManager;
  private RecordingDrawSurface surface;
  private JFrame frame;
  private JPanel mainPanel;
  private JPanel drawingPanel;
  private int width;
  private int height;
  
  public GUI(String title, int width, int height)
  {
    this.width = width;
    this.height = height;
    
    frame = new JFrame(title);
    mainPanel = new JPanel();
    drawingPanel = new GUIPanel(null);
    
    frame.setDefaultCloseOperation(3);
    frame.setSize(width, height);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.setIgnoreRepaint(true);
    frame.setContentPane(mainPanel);
    frame.setLayout(new BorderLayout());
    mainPanel.add(drawingPanel, "Center");
    mainPanel.setPreferredSize(new Dimension(width, height));
    frame.pack();
    
    dialogManager = new DialogManagerImpl(frame, width, height);
    keyboardSensor = new KeyboardSensorImpl(frame);
    surface = new RecordingDrawSurface(this.width, this.height);
  }
  





  public DialogManager getDialogManager()
  {
    return dialogManager;
  }
  




  public DrawSurface getDrawSurface()
  {
    return new RecordingDrawSurface(width, height);
  }
  






  public void show(DrawSurface surface)
  {
    final RecordingDrawSurface recordingDrawSurface = (RecordingDrawSurface)surface;
    
    if (recordingDrawSurface.isRendered()) {
      throw new DrawSurfaceAlreadyRenderedException("You can not show the same draw surface twice, you must request a new one each time using getDrawSurface()");
    }
    

    recordingDrawSurface.setRendered(true);
    final GUI self = this;
    
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run() {
        frame.createBufferStrategy(2);
        BufferStrategy bf = frame.getBufferStrategy();
        Graphics graphics = bf.getDrawGraphics();
        selfsurface = recordingDrawSurface;
        frame.paint(graphics);
        bf.show();
      }
    });
  }
  





  public KeyboardSensor getKeyboardSensor()
  {
    return keyboardSensor;
  }
  
  private class GUIPanel extends JPanel {
    private GUIPanel() {}
    
    public void paint(Graphics g) {
      if (surface != null) {
        surface.paint(g);
      }
    }
  }
  




  public void close()
  {
    frame.dispatchEvent(new WindowEvent(frame, 201));
  }
}
