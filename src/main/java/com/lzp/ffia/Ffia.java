
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:基金定投辅助
 *
 *
 * @author: Zeping Lu
 * @date: 2021/5/27 16:49
 */
public class Ffia {

    /**
     * 每月定投日(1-28)
     */
    private final short MONTHLY_FIXED_INVESTMENTDAY;

    /**
     * 基金代码
     */
    private final String FUND_CODE;

    /**
     * 每月加仓次数
     */
    private final short TIME_OF_INCREASE_IN_MONTH;

    /**
     * 短信要发送给的手机号码
     */
    private final String PHONE_NUMBER;


    /**
     * 当月定投确认份额时的基金净值,每月定投日会刷新
     */
    private double benchmarkNetWorth;

    public Ffia(short MONTHLY_FIXED_INVESTMENTDAY, short TIME_OF_INCREASE_IN_MONTH, String FUND_CODE, String PHONE_NUMBER) {
        this.MONTHLY_FIXED_INVESTMENTDAY = MONTHLY_FIXED_INVESTMENTDAY;
        this.FUND_CODE = FUND_CODE;
        this.TIME_OF_INCREASE_IN_MONTH = TIME_OF_INCREASE_IN_MONTH;
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    /**
     * 启动辅助,从当月定投日开始截至到下个月定投日,监测到当日2.55分时基金净值低于定投份额确认日净值的99.8%
     * 时,向指定手机号发送基金代码短信。
     * 每发送一次短信就默认短信接收人已经去加仓了。如果当月加仓次数满了,则这个月不会再发送
     */
    public void start() {
        List<Short> dayHourMinAndSec = getCurrentNumOfDaysInMonth();
        if (dayHourMinAndSec.get(0) == MONTHLY_FIXED_INVESTMENTDAY) {

        } else {

        }
    }


    /**
     * 休眠到当天14.55
     */
    private void sleepUntilSixToClosingPoint(short curHour,short curMin,short curSec) throws InterruptedException {
        if (curHour>14){
            Thread.sleep((24-curHour)*3600+1);
        }else if (curHour==14){
            
        }
        Thread.sleep(((14-curHour)*3600)+((55-curMin)*60)-curHour);
    }

    /**
     * 获取当前时刻(以List返回,装有四个元素,分别是天、时、分、秒)
     */
    private List<Short> getCurrentNumOfDaysInMonth() {
        List<Short> dayHourMinAndSec = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        dayHourMinAndSec.add(Short.parseShort(formatter.format(date).substring(8, 10)));
        dayHourMinAndSec.add(Short.parseShort(formatter.format(date).substring(11, 13)));
        dayHourMinAndSec.add(Short.parseShort(formatter.format(date).substring(14, 16)));
        dayHourMinAndSec.add(Short.parseShort(formatter.format(date).substring(17, 19)));
        return dayHourMinAndSec;
    }



}
