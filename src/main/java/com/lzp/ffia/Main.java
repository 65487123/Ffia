
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

package com.lzp.ffia;

import com.lzp.ffia.util.GetFundInfoUtil;
import com.lzp.ffia.util.HttpUtil;
import com.lzp.ffia.util.MessageUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Description:启动类
 *
 * @author: Zeping Lu
 * @date: 2021/5/27 15:00
 */

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        //MessageUtil.sentMessage("17348846527","020011");
        //double d =GetFundInfoUtil.getCurrentNetWorthOfFund("020011");
        //new Ffia((short)1,(short)4,"020011","17348846527").start();
        System.out.println(((14-12)*3600)+((55-56)*60)-22);
    }
}
