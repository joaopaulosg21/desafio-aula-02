import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GeradorDeFigurinhas {

    public void criar(InputStream inputStream, String nome) throws Exception {

        BufferedImage imagemOriginal = ImageIO.read(inputStream);
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int aumentoDaAltura = (int)(altura * 0.1);
        int novaAltura = altura + aumentoDaAltura;

        BufferedImage novaImagem = new BufferedImage(largura,novaAltura,BufferedImage.TRANSLUCENT);
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0,0,null);


        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, aumentoDaAltura/2);
        graphics.setFont(fonte);
        graphics.setColor(Color.YELLOW);

        int drawLargura = (int) (largura * 0.35);
        int drawAltura = (int)(novaAltura * 0.97);
        graphics.drawString("TOPZERA",drawLargura,drawAltura);

        ImageIO.write(novaImagem,"png",new File("src/saida/"+nome));
    }
}
