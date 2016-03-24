package club.mecn.service;

/**
 * Created by Administrator on 2016/1/28.
 */
public interface MailService {
    /**
     * 发送邮件方法
     * @param to
     * @param subject
     * @param body
     */
    void sendEmail(String to,String subject,String body);

    /**
     *
     * @param to 注册用户邮箱
     */
    void sendRegisterEmail(String to);


    /**
     * 发送重置密码邮件
     * @param username
     * @param to
     */
    void sendResetPasswordEmail(String username ,String to);

}
