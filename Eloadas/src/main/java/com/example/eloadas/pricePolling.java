package com.example.eloadas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.oanda.v20.Context;
import com.oanda.v20.ContextBuilder;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.pricing.ClientPrice;
import com.oanda.v20.pricing.PricingGetRequest;
import com.oanda.v20.pricing.PricingGetResponse;
import com.oanda.v20.primitives.DateTime;
import com.oanda.v20.primitives.InstrumentName;

import static com.oanda.v20.instrument.CandlestickGranularity.H1;

public class pricePolling {

    public static void main(String[] args) {
        Context ctx = new ContextBuilder(config.URL).setToken(config.TOKEN).setApplication("PricePolling").build();
        AccountID accountId = config.ACCOUNTID;
        List<String> instruments = new ArrayList<>(Arrays.asList("EUR_USD", "USD_JPY", "GBP_USD", "USD_CHF"));

        try {
            PricingGetRequest request = new PricingGetRequest(accountId, instruments);
            DateTime since = null;
            while (true) {
                if (since != null) {
                    System.out.println("Polling since " + since);
                    request.setSince(since);
                }
                PricingGetResponse resp = ctx.pricing.get(request);
                for (ClientPrice price : resp.getPrices())
                    System.out.println(price);
                since = resp.getTime();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPrice(String instrument) {
        Context ctx = new ContextBuilder(config.URL).setToken(config.TOKEN).setApplication("PricePolling").build();
        AccountID accountId = config.ACCOUNTID;

        String result="";
        try {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new
                    InstrumentName(instrument));
            request.setGranularity(H1);
            request.setCount(1L);
            InstrumentCandlesResponse resp = ctx.instrument.candles(request);
            for(Candlestick candle: resp.getCandles())
                result+=(candle.getTime()+"\t"+candle.getMid().getC()).toString();
            System.out.println(result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String getOldPrice(String instrument,String start,String end) throws ParseException {
        Context ctx = new ContextBuilder(config.URL).setToken(config.TOKEN).setApplication("PricePolling").build();
        AccountID accountId = config.ACCOUNTID;
        //List<String> instruments = new ArrayList<>(Arrays.asList(instrument));
        String result="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDate=Calendar.getInstance();
        startDate.setTime(sdf.parse(start));

        Calendar endDate=Calendar.getInstance();
        endDate.setTime(sdf.parse(end));

        //System.out.println(endDate.before(startDate));
        String newDate="";



        try {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new
                    InstrumentName(instrument));
            //equest.setGranularity(H1);
            request.setCount(3L);
            InstrumentCandlesResponse resp = ctx.instrument.candles(request);
            while (startDate.before(endDate)) {
                for (Candlestick candle : resp.getCandles()) {
                    newDate = sdf.format(startDate.getTime());
                    candle.setTime(newDate);
                    result+=startDate.getTime()+" : "+candle.getMid().toString();
                    //result += (candle.setTime(newDate) + "\t" + candle.getMid().getC()).toString();
                    result+="\n";

                    startDate.add(Calendar.DATE, 1);
                    //String newDate=sdf.format(startDate.getTime());
                    //System.out.println(startDate.getTime());
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<Double> getChart(String instrument, String start, String end) throws ParseException {
        Context ctx = new ContextBuilder(config.URL).setToken(config.TOKEN).setApplication("PricePolling").build();
        AccountID accountId = config.ACCOUNTID;
        //List<String> instruments = new ArrayList<>(Arrays.asList(instrument));
        ArrayList<Double> result=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar startDate=Calendar.getInstance();
        startDate.setTime(sdf.parse(start));

        Calendar endDate=Calendar.getInstance();
        endDate.setTime(sdf.parse(end));

        //System.out.println(endDate.before(startDate));
        String newDate="";

        try {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new
                    InstrumentName(instrument));
            request.setGranularity(H1);
            request.setCount(3L);
            InstrumentCandlesResponse resp = ctx.instrument.candles(request);
            while (startDate.before(endDate)) {
                for (Candlestick candle : resp.getCandles()) {
                    newDate = sdf.format(startDate.getTime());
                    candle.setTime(newDate);
                    result.add(Double.parseDouble(candle.getMid().getC().toString()));
                    //result += (candle.setTime(newDate) + "\t" + candle.getMid().getC()).toString();

                    startDate.add(Calendar.DATE, 1);
                    //String newDate=sdf.format(startDate.getTime());
                    //System.out.println(startDate.getTime());
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}


