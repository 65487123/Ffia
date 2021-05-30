
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
import com.lzp.ffia.util.ThreadFactoryImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Description:启动类
 *
 * @author: Zeping Lu
 * @date: 2021/5/27 15:00
 */

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 2, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryImpl("ffia"));
        threadPool.execute(() -> new Ffia((short) 1, (short) 1, "020011",
                "17348846527", 0.9998).start());
        threadPool.execute(() -> new Ffia((short) 1, (short) 1, "501036",
                "17348846527", 0.9998).start());
    }
}
