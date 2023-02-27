import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20170605.SendSmsRequest;
import com.aliyuncs.sms.model.v20170605.SendSmsResponse;

public class AliyunSms {

    public static void sendSms(String accessKeyId, String accessKeySecret, String signName, String templateCode, String phoneNumbers, String templateParam) throws ClientException {
        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化ascClient需要的几个参数
        final String product = "Dysmsapi";
        final String domain = "dysmsapi.aliyuncs.com";

        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

        SendSmsRequest request = new SendSmsRequest();
        // 使用POST方法发送短信
        request.setMethod(MethodType.POST);
        // 设置短信接收号码
        request.setPhoneNumbers(phoneNumbers);
        // 设置短信签名
        request.setSignName(signName);
        // 设置短信模板ID
        request.setTemplateCode(templateCode);
        // 设置短信模板变量值
        request.setTemplateParam(templateParam);

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = AliyunSmsUtils.getInstance().getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            // 请求成功
            System.out.println("发送短信成功！");
        } else {
            // 请求失败
            System.out.println("发送短信失败：" + sendSmsResponse.getMessage());
        }
    }
}
