package com.example;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class EncodeTest {

    /**
     * 演示 shiro 自带的各种编码和解码, 和 HashService 实现 来处理各种加密
     */
    @Test
    public void encodeTest()
    {
        //定义源字符串
        String str = "abc";

        //进行 base64 编码和解码
        String s = Base64.encodeToString(str.getBytes());
        String s1 = Base64.decodeToString(s);

        //进行 16 进制的编码和解码
        String s2 = Hex.encodeToString(str.getBytes());
        String s3 = new String(Hex.decode(s2));

        //md5 不可逆加密
        Md5Hash md5Hash = new Md5Hash(str, "111", 3);
        //sha256 不可逆加密
        Sha256Hash sha256Hash = new Sha256Hash(str, "111", 3);

        //HashService 的默认实现 DefaultHashService 的加密方式
        DefaultHashService hs = new DefaultHashService();
        //设置私盐, 和后面传入的公盐会结合为一个全新的盐
        //hs.setPrivateSalt(new SimpleByteSource("111"));
        //设置加密方式, 默认是 SHA-512
        hs.setHashAlgorithmName("md5");
        //设置是否生成公盐, 默认为 false
        hs.setGeneratePublicSalt(true);
        //设置公盐的生成器, SecureRandomNumberGenerator 用于生成一个随机数
        hs.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        //散列次数
        hs.setHashIterations(3);

        //构建 HashRequest 对象, 指定加密方式, 加密数据源, 盐, 散列次数, 如果 DefaultHashSerive 已经设置过, 则这里可以不用再设置一次
        /*HashRequest hr = new HashRequest.Builder().setAlgorithmName("MD5").setSource(ByteSource.Util.bytes(str))
                .setSalt(ByteSource.Util.bytes("111")).setIterations(3).build();*/
        HashRequest hr = new HashRequest.Builder().setSource(ByteSource.Util.bytes(str)).setSalt("111").build();

        //执行加密, 由于配置的有私盐, 所以结果和上面的加密结果不一致
        String md5Hash2 = hs.computeHash(hr).toString();

    }
}
