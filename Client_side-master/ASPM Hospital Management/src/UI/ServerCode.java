package UI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class ServerCode {
	public ServerCode() {
		System.out.println("Executing server code...");
	}

	public int PostData(JSONObject json, String url, String callFun, String headerData) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String responseString = null;
		try {
			StringEntity entity;
			entity = new StringEntity(json.toString());
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			if (callFun == "Login")
				httpPost.setHeader("role", headerData);
			CloseableHttpResponse response;
			response = client.execute(httpPost);
			if (callFun == "Login") {
				Client.authToken = response.getFirstHeader("token").getValue();
			}
			HttpEntity en = response.getEntity();
			responseString = EntityUtils.toString(en, "UTF-8");

			System.out.println(responseString);
			System.out.println("Response:" + response.getStatusLine().getStatusCode());

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (responseString != "0")
			return 1;

		return 0;
	}
	public String getData(String url) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		String responseString = null;
		try {
			httpGet.addHeader("token", Client.authToken);
			CloseableHttpResponse response;
			response = client.execute(httpGet);
			HttpEntity en = response.getEntity();
			responseString = EntityUtils.toString(en, "UTF-8");
			System.out.println(responseString);
			System.out.println("Response: " + response.getStatusLine().getStatusCode());

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return responseString;
	}
}