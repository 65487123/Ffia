
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
import org.apache.http.util.EntityUtils;

import java.util.HashMap;

/**
 * Description:获取基金信息的工具类
 *
 * @author: Zeping Lu
 * @date: 2021/5/27 17:21
 */
public class GetFundInfoUtil {

    public static double getCurrentNetWorthOfFund(String fundCode) throws Exception {
        HttpResponse httpResponse = HttpUtil.doGet("http://fundgz.1234567.com.cn", "/js/"
                + fundCode + ".js", new HashMap<>(16), new HashMap<>(16));
        String[] strings = EntityUtils.toString(httpResponse.getEntity()).split(",");
        for (String string : strings) {
            if (string.startsWith("\"gsz\":\"")) {
                return Double.parseDouble(string.substring(7, string.length() - 1));
            }
        }
        throw new RuntimeException("cannot get net worth");
    }
}
