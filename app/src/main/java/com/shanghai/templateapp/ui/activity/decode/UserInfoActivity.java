package com.shanghai.templateapp.ui.activity.decode;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.shanghai.templateapp.R;
import com.shanghai.templateapp.base.BaseActivity;
import com.shanghai.templateapp.base.connectors.decode.UserInfoConnector;
import com.shanghai.templateapp.models.entity.UserInfoEntity;
import com.shanghai.templateapp.presenters.decode.UserInfoPresenter;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity<UserInfoPresenter> implements UserInfoConnector.View {
    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String ENCODING = "UTF-8";
    public static final String keyStr = "1236547893214569";
    String custidnum;
    String custidtype;
    String custmobile;
    String custname;
    String ipaddress;
    String signedcustinfo;
    String userid;
    String useridD;
    String id;
    @BindView(R.id.tv_update)
    TextView mTextView;
    List<String> jsonList = new ArrayList<>();
    String json;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //    setContentView(R.layout.activity_user_info);
//        mPresenter.getUserInfo();
//
//
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getUserInfo();
    }

    @Override
    public void setUserInfo(List<UserInfoEntity> u) {
        String a = "@@@@@@@@@@";
        String b = "##########";
        for (int i = 0; i < u.size(); i++) {
            custidnum = encrypt(u.get(i).getCustidnum(), keyStr);
            custidtype = encrypt(u.get(i).getCustidtype(), keyStr);
            custmobile = encrypt(u.get(i).getCustmobile(), keyStr);
            custname = encrypt(u.get(i).getCustname(), keyStr);
            ipaddress = encrypt(u.get(i).getIpaddress(), keyStr);
            signedcustinfo = encrypt(u.get(i).getSignedcustinfo(), keyStr);
            userid = encrypt(u.get(i).getUserid(), keyStr);
            useridD=u.get(i).getUserid();
            id = u.get(i).getId();
            if (i == u.size() - 1) {
                json = "id:" + id + a + "custidnumv2:" + custidnum + a + "custidtypev2:" + custidtype + a + "custmobilev2:" + custmobile + a + "custnamev2:" + custname + a
                        + "ipaddressv2:" + ipaddress + a + "signedcustinfov2:" + signedcustinfo + a + "useridv2:" + userid +a+"userid:"+useridD;

            } else {
                json = "id:" + id + a + "custidnumv2:" + custidnum + a + "custidtypev2:" + custidtype + a + "custmobilev2:" + custmobile + a + "custnamev2:" + custname + a
                        + "ipaddressv2:" + ipaddress + a + "signedcustinfov2:" + signedcustinfo + a + "useridv2:" + userid +a+"userid:"+useridD + b;

            }
            jsonList.add(json);
        }
        mPresenter.getUpUsetInfo(jsonList.toString());
        Log.i("json", json);
        Log.i("json1", jsonList.toString());

    }

    @OnClick(R.id.tv_update)
    void onUpdate() {
        mPresenter.getUserInfo();
    }

    @Override
    public void upUserInfo(String jsonArrays) {

    }

    @Override
    public void stateError() {
        super.stateError();
      //  Log.i("setUserInfo", "err");
    }

    private static Key toKey(String key) {
        byte[] enCodeFormat = key.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
        return secretKeySpec;
    }

    public static String decrypt(String content, String key) {
//        if (EmptyUtil.isEmpty(content)) {
//            return "";
//        }
        try {
            Key k = toKey(key);
            byte[] data = parseHexStr2Byte(content);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, k);

            return new String(cipher.doFinal(data));
        } catch (Exception e) {
            //  logger.error("AES 密文处理异常", e);
            e.printStackTrace();
        }
        return "";
    }

    public static String encrypt(String content, String key) {
//        if (EmptyUtil.isEmpty(content)) {
//            return "";
//        }
        try {
            Key k = toKey(key);
            byte[] data = content.getBytes(ENCODING);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, k);

            return parseByte2HexStr(cipher.doFinal(data));
        } catch (Exception e) {
            // logger.error("AES 密文处理异常", e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换成二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
