package one;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * ����API�������ʾ������ �� �ۺ����� ���߽ӿ��ĵ���http://www.juhe.cn/docs/54
 **/

public class JuheDemo {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// �����������KEY
	public static final String APPKEY = "c480db323ad91f25c8314c27dea9193b";

	// 1.���δʼ���
	public static void getRequest1() {
		String result = null;
		String url = "http://v.juhe.cn/sms/black";// ����ӿڵ�ַ
		Map params = new HashMap();// �������
		params.put("word", "");// ��Ҫ���Ķ������ݣ���ҪUTF8 URLENCODE
		params.put("key", APPKEY);// Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 2.���Ͷ���
	public static void getRequest2() {
		String result = null;
		String url = "http://v.juhe.cn/sms/send";// ����ӿڵ�ַ
		Map params = new HashMap();// �������
		params.put("mobile", "13168089115");// ���ն��ŵ��ֻ�����
		params.put("tpl_id", "56298");// ����ģ��ID����ο��������Ķ���ģ������
		params.put("tpl_value", "%23code%23%3d431515");// �������ͱ���ֵ�ԡ������ı��������߱���ֵ�д���#&=�е�����һ��������ţ����ȷֱ����urlencode������ٴ��ݣ�<a
														// href="http://www.juhe.cn/news/index/id/50"
														// target="_blank">��ϸ˵��></a>
		params.put("key", APPKEY);// Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)
		params.put("dtype", "json");// �������ݵĸ�ʽ,xml��json��Ĭ��json

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":" + object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		getRequest2();
	}

	/**
	 *
	 * @param strUrl
	 *            �����ַ
	 * @param params
	 *            �������
	 * @param method
	 *            ���󷽷�
	 * @return ���������ַ���
	 * @throws Exception
	 */
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// ��map��תΪ���������
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}