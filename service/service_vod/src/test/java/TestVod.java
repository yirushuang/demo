
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.google.gson.Gson;


import java.util.List;

public class TestVod {

    public static void main(String[] args) throws ClientException {
        // 创建SubmitMediaInfoJob实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shenzhen",           // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                "LTAI5tAYZSVmXU2jybYWbfQV",       // 您的AccessKey ID
                "2kh5Pi31uGWQU0K1G89X3Gc7DWCHmZ" ); // 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        // 视频ID，可以通过GetVideoList接口查询得到
        request.setVideoId("4ca1ebf586f44ce9ad255a0eb4a1ad1d");
        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

        //创建获取视频地址request和response
//        GetPlayInfoRequest request = new GetPlayInfoRequest();
//        GetPlayInfoResponse response = new GetPlayInfoResponse();
//
//        //向request对象里面设置视频id
//        request.setVideoId("4ca1ebf586f44ce9ad255a0eb4a1ad1d");
//
//        //调用初始化对象里面的方法，传递request，获取数据
//        response = client.getAcsResponse(request);
//
//        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
//        //播放地址
//        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
//            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
//        }
//        //Base信息
//        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
