
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
import com.lzp.ffia.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final Logger LOGGER = LoggerFactory.getLogger(Ffia.class);
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
    private final short TIME_OF_INCREASE_EVERY_MONTH;

    /**
     * 短信要发送给的手机号码
     */
    private final String PHONE_NUMBER;


    /**
     * 当月定投确认份额时的基金净值,每月定投日会刷新
     */
    private double benchmarkNetWorth = 2.0;

    /**
     * 当月加注次数
     */
    private short timesOfRaisesThisMonth;


    /**
     * 需要发短信通知的点,定投当日确认份额后的净值的百分比。
     * <p>
     * 比如如果设为0.9998,当查出来的净值低于定投当日的净值的0.9998就会发短信
     */
    private final double NOTIFICATION_POINT;

    public Ffia(short MONTHLY_FIXED_INVESTMENTDAY, short TIME_OF_INCREASE_EVERY_MONTH,
                String FUND_CODE, String PHONE_NUMBER, double NOTIFICATION_POINT) {
        this.MONTHLY_FIXED_INVESTMENTDAY = MONTHLY_FIXED_INVESTMENTDAY;
        this.FUND_CODE = FUND_CODE;
        this.TIME_OF_INCREASE_EVERY_MONTH = TIME_OF_INCREASE_EVERY_MONTH;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.NOTIFICATION_POINT = NOTIFICATION_POINT;
    }

    /**
     * 启动辅助,从当月定投日开始截至到下个月定投日,监测到当日2.55分时基金净值低于定投份额确认日净值的99.8%
     * 时,向指定手机号发送基金代码短信。
     * 每发送一次短信就默认短信接收人已经去加仓了。如果当月加仓次数满了,则这个月不会再发送
     */
    public void start() {
        LOGGER.info("assistance successfully launched");
        for (; ; ) {
            sleepAndExecuteCoreLogic();
        }
    }


    /**
     * 睡到14.55并执行基金定投辅助核心逻辑
     */
    private void sleepAndExecuteCoreLogic() {
        List<Short> dayHourMinAndSec = getCurrentNumOfDaysInMonth();
        try {
            sleepUntilSixToClosingPoint(dayHourMinAndSec.get(1), dayHourMinAndSec.get(2), dayHourMinAndSec.get(3));
            //已经睡到这一天的14.55了。或者项目启动时候就已经14.55-15.00之间了
            if (dayHourMinAndSec.get(0) == MONTHLY_FIXED_INVESTMENTDAY) {
                Thread.sleep(3600);
                this.benchmarkNetWorth = GetFundInfoUtil.getCurrentNetWorthOfFund(FUND_CODE);
            } else {
                if ((timesOfRaisesThisMonth < TIME_OF_INCREASE_EVERY_MONTH) && notificationPointReached()) {
                    MessageUtil.sentMessage(PHONE_NUMBER, FUND_CODE);
                    timesOfRaisesThisMonth++;
                }
            }
            //睡20个小时,睡醒后就是第二天啦
            Thread.sleep(20 * 3600);
        } catch (InterruptedException e) {
            sleepAndExecuteCoreLogic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean notificationPointReached() throws Exception {
        //不需要那么精确,所以就不用BigDecimal了
        return GetFundInfoUtil.getCurrentNetWorthOfFund(FUND_CODE) < benchmarkNetWorth * NOTIFICATION_POINT;
    }


    /**
     * 休眠到当天14.55
     */
    private void sleepUntilSixToClosingPoint(short curHour, short curMin, short curSec) throws InterruptedException {
        if (curHour > 14) {
            //睡到第二天
            Thread.sleep((24 - curHour) * 3600 + 1);
            sleepAndExecuteCoreLogic();
        } else if (curHour == 14 && curMin >= 55) {
            return;
        }
        Thread.sleep(((14 - curHour) * 3600) + ((55 - curMin) * 60) - curSec);
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
