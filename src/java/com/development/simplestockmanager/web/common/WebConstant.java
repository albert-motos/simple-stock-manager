package com.development.simplestockmanager.web.common;

/**
 *
 * @author foxtrot
 */
public interface WebConstant {

    public static String UNDEFINED = "undefined";

    public interface VALIDATOR {

        public interface MODE {

            public static long CREATE = 0;
            public static long EDIT = 1;
            public static long SEARCH = 2;
        }
    }

    public interface SELECTOR {

        public interface MODE {

            public static long ENABLE = 0;
            public static long ALL = 1;
            public static long NONE = 2;
        }
    }

    public interface WEB {

        public static String BASE = "/SimpleStockManager";
        public static String INDEX = BASE + "/index.xhtml";
        public static String LOGIN = BASE + "/login.xhtml";

        public interface EDIT {

            public static String TEXT = WEB.BASE + "/edit";
            public static String BRAND = TEXT + "/brand.xhtml";
            public static String CLIENT = TEXT + "/client.xhtml";
            public static String EMPLOYEE = TEXT + "/employee.xhtml";
            public static String PRODUCT = TEXT + "/product.xhtml";
            public static String PROVIDER = TEXT + "/provider.xhtml";
            public static String STORE = TEXT + "/store.xhtml";
            
            public static String EMPLOYEE_TYPE = TEXT + "/employee_type.xhtml";
            public static String PAYMENT_TYPE = TEXT + "/payment_type.xhtml";
            public static String PRICE_TYPE = TEXT + "/price_type.xhtml";
            public static String PRODUCT_TYPE = TEXT + "/product_type.xhtml";
            public static String SEX_TYPE = TEXT + "/sex_type.xhtml";
        }

        public interface SEARCH {

            public static String TEXT = WEB.BASE + "/search";
            public static String BRAND = TEXT + "/brand.xhtml";
            public static String CLIENT = TEXT + "/client.xhtml";
            public static String EMPLOYEE = TEXT + "/employee.xhtml";
            public static String PRODUCT = TEXT + "/product.xhtml";
            public static String PROVIDER = TEXT + "/provider.xhtml";
            public static String STORE = TEXT + "/store.xhtml";

            public static String EMPLOYEE_TYPE = TEXT + "/employee_type.xhtml";
            public static String PAYMENT_TYPE = TEXT + "/payment_type.xhtml";
            public static String PRICE_TYPE = TEXT + "/price_type.xhtml";
            public static String PRODUCT_TYPE = TEXT + "/product_type.xhtml";
            public static String SEX_TYPE = TEXT + "/sex_type.xhtml";
        }
    }

    public interface SESSION {

        public static String BRAND = "brand";
        public static String CLIENT = "client";
        public static String EMPLOYEE = "employee";
        public static String PRODUCT = "product";
        public static String PROVIDER = "provider";
        public static String STORE = "store";

        public static String EMPLOYEE_TYPE = "employee_type";
        public static String PAYMENT_TYPE = "payment_type";
        public static String PRICE_TYPE = "price_type";
        public static String PRODUCT_TYPE = "product_type";
        public static String SEX_TYPE = "sex_type";
    }

    public interface LOGGER {

        public interface SERVICE {

            public interface AUTHENTICATION {

                public static String CLASS = "AuthenticationService class";
                public static String CONSTRUCTOR = CLASS + " -> constructor function";
                public static String LOGIN = CLASS + " -> login function";
                public static String LOGOUT = CLASS + " -> logout function";
                public static String REDIRECT = CLASS + " -> redirect function";
                public static String GET_CURRENT_EMPLOYEE = CLASS + " -> getCurrentEmployee function";
            }

            public interface MENU {

                public static String CLASS = "MenuService class";
                public static String CONSTRUCTOR = CLASS + " -> constructor function";
            }

            public interface LABEL {

                public static String CLASS = "LabelService class";
                public static String CONSTRUCTOR = CLASS + " -> constructor function";
            }

            public interface BUTTON {

                public static String CLASS = "ButtonService class";
                public static String CONSTRUCTOR = CLASS + " -> constructor function";
            }
        }
    }

    public interface STATUS {

        public static long INDETERMINATE = 0;
        public static long HIDDEN = 1;
        public static long VISIBLE = 2;
    }
}
