/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.common;

/**
 *
 * @author foxtrot
 */
public interface BusinessConstant {

    public interface IDENTIFIER {

        public static long INVALID = -1;
    }

    public interface DELETE {

        public static long SUCCESS = 1;
        public static long FAILURE = 0;
    }

    public interface UPDATE {

        public static long SUCCESS = 1;
        public static long FAILURE = 0;
    }

    public interface PROJECT {

        public static String PERSISTENCE_UNIT = "SimpleStockManagerPU";
        public static String WEB = "/SimpleStockManager";
    }

    public interface QUERY {

        public static String ANALYTICS_TIME = "a";
        public static String BRAND = "b";
        public static String CLIENT = "c";
        public static String EMPLOYEE = "e";
        public static String EMPLOYEE_TYPE = "e";
        public static String EMPLOYEE_TYPE_TRANSLATION = "e";
        public static String INVOICE = "i";
        public static String ITEM = "i";
        public static String LANGUAGE = "l";
        public static String PAYMENT_TYPE = "p";
        public static String PAYMENT_TYPE_TRANSLATION = "p";
        public static String PRICE = "p";
        public static String PRICE_TYPE = "p";
        public static String PRICE_TYPE_TRANSLATION = "p";
        public static String PRODUCT = "p";
        public static String PRODUCT_TYPE = "p";
        public static String PRODUCT_TYPE_TRANSLATION = "p";
        public static String PROVIDER = "p";
        public static String RECORD = "r";
        public static String SEX_TYPE = "s";
        public static String SEX_TYPE_TRANSLATION = "s";
        public static String STOCK = "s";
        public static String STORE = "s";
    }

}
