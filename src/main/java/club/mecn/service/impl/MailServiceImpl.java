package club.mecn.service.impl;

import club.mecn.service.MailService;
import club.mecn.util.GenRandomCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Administrator on 2016/1/28.
 */
@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String to,String subject,String body)
    {
        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);

        mailSender.send(email);
    }

    public void sendRegisterEmail(String to)  {


        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            String message = "<h2>欢迎加入mecn.club 镜之边缘中文网</h2><p><a href='#'>点击此链接激活邮箱(此邮箱可找回密码)</a></p>";

            helper.setFrom("pk@mecn.club");
            helper.setTo(to);
            helper.setSubject("欢迎加入mecn.club!!!");

            mimeMessage.setContent(message,"text/html;charset=UTF-8");
            mailSender.send(mimeMessage);
            }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void sendResetPasswordEmail(String username ,String to)
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            String tempPassword = GenRandomCodeUtil.genRandomPassword();

            String message = "<h2>"+username+" 这是你的临时密码,请尽快设置新密码!<h3><p style='color:red'>"+tempPassword+"</h3>";

            helper.setFrom("pk@mecn.club");
            helper.setTo(to);
            helper.setSubject("MECN临时密码");

            mimeMessage.setContent(message,"text/html;charset=UTF-8");
            mailSender.send(mimeMessage);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
