package com.health.gateway.config;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Region;

public class SignatureDemo {

	public AmazonS3 buildAmazonS3(String ak, String sk, String endpoint) {
		AWSCredentials credentials = new BasicAWSCredentials(ak, sk);
		ClientConfiguration clientConfiguration = new ClientConfiguration().withProtocol(Protocol.HTTP);

		String signingRegion = Region.CN_Beijing.getFirstRegionId();// 设定AWS服务区域
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(endpoint, signingRegion);
		AmazonS3ClientBuilder amazonS3ClientBuilder = AmazonS3ClientBuilder.standard();
		return amazonS3ClientBuilder.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withClientConfiguration(clientConfiguration).withEndpointConfiguration(endpointConfiguration).build();
	}

	public Date getExpireTime(int s) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, s);
		return calendar.getTime();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		LocalDateTime expirationDateTime = LocalDateTime.now().plusDays(7);//url的有效时间10年
		Date expiration = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());
		SignatureDemo demo = new SignatureDemo();
		String endpoint = "fjoss.xstore.ctyun.cn";// endpoint形式为：http://ip:port或https://rgwdomain
		AmazonS3 amazonS3 = demo.buildAmazonS3("wdt2aKC7l9cTZlGoG67W", "Jzzo5RnkgprXi4D0JcXRH5Xw0zxFNZH6PeqovPr2", endpoint);
		String bucket = "rt-live";
		// https://vod-transcode-nk4d.fjoss.xstore.ctyun.cn/a35933e8cb8b4998ac2d392141194a1a/100000000102.MP4
		String key = "2020-09-22/2020092117455352270_net.m3u8";
		String presignedUrl = amazonS3.generatePresignedUrl(bucket, key,
				demo.getExpireTime(10000), HttpMethod.GET).toString();
		String decode = URLDecoder.decode(presignedUrl, "UTF-8");
		System.out.println(decode);
		System.out.println("*****生成的预签名URL如下*****");
		System.out.println(presignedUrl);
	}
}
