
/* Copyright zeping lu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lzp.ffia.util;

import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:发送短信工具类
 *
 * @author: Zeping Lu
 * @date: 2021/5/27 15:24
 */
public class MessageUtil {

    public static void sentMessage(String telephoneNumber, String code) {
        String host = "http://yzx.market.alicloudapi.com";
        String path = "/yzx/sendSms";
        //这个appcode是买的,这里我做了修改。如果需要,可以自己买。
        String appcode = "8f5551199e124307ec141f86b148236";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("mobile", telephoneNumber);
        querys.put("param", "code:" + code);
        querys.put("tpl_id", "TP1710262");
        Map<String, String> bodys = new HashMap<>();

        try {
            HttpResponse response = HttpUtil.doPost(host, path, headers, querys, bodys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
