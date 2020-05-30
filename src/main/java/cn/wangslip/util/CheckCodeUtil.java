package cn.wangslip.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CheckCodeUtil {
    public static final int WIDTH = 110;//生成图片的宽度
    public static final int HEIGHT = 26;//生成图片的高度
    public static final int WORDS_NUMBER = 4;//验证码中字符的个数

    public Map<String, Object> check(String createTypeFlag) throws ServletException, IOException {
        Map<String, Object> ret = new HashMap<>();
        // TODO Auto-generated method stub
//        String createTypeFlag = req.getParameter("createTypeFlag");//接收客户端传递的createTypeFlag标识
        //在内存中创建一张图片
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        //得到图片
        Graphics g = bi.getGraphics();
        //设置图片的背景色
        setBackGround(g);
        //设置图片的边框
        setBorder(g);
        //在图片上画干扰线
        drawRandomLine(g);
        //在图片上放上随机字符
        String randomString = this.drawRandomNum((Graphics2D) g, createTypeFlag);
        ret.put("image", bi);
        ret.put("code", randomString);
        return ret;
    }

    //设置图片背景色
    //@param g
    private void setBackGround(Graphics g) {
        //设置颜色
        g.setColor(Color.WHITE);
        //填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    /*
     * 设置图片的边框
     * @param g
     * */
    private void setBorder(Graphics g) {
        //设置边框颜色
        g.setColor(Color.BLUE);
        //边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /*
     * 在图片上画随机线条
     * @param g
     * */
    private void drawRandomLine(Graphics g) {
        //设置颜色
        g.setColor(Color.GREEN);
        //设置线条个数并画线
        for (int i = 0; i < 3; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /*
     * 在图片上画随机字符
     * @param g
     * @param createTypeFlag
     * @return String
     * */
    private String drawRandomNum(Graphics g, String createTypeFlag) {
        //设置颜色
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 20));

        //数字字母的组合
        String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        String baseNum = "0123456789";
        String baseLetter = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
        if (createTypeFlag != null && createTypeFlag.length() > 0) {
            if (createTypeFlag.equals("nl")) {
                //截取数字和字母的组合nl
                return createRandomChar((Graphics2D) g, baseNumLetter);
            } else if (createTypeFlag.equals("n")) {
                //截取数字的组合
                return createRandomChar((Graphics2D) g, baseNum);
            } else if (createTypeFlag.equals("l")) {
                //截取字母的组合
                return createRandomChar((Graphics2D) g, baseLetter);
            }
        } else {
            //截取数字和字母的组合
            return createRandomChar((Graphics2D) g, baseNumLetter);
        }
        return "";
    }

    /*
     * 创建随机字符
     * @param g
     * @param baseChar
     * @return String
     * */
    private String createRandomChar(Graphics2D g, String baseChar) {
        StringBuffer b = new StringBuffer();
        int x = 5;
        String ch = "";
        for (int i = 0; i < WORDS_NUMBER; i++) {
            //设置字体的旋转角度
            int degree = new Random().nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            b.append(ch);

            //正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 20);
            //反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return b.toString();
    }
}