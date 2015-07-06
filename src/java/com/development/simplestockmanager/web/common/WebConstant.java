package com.development.simplestockmanager.web.common;

/**
 *
 * @author foxtrot
 */
public interface WebConstant {

    public interface VALIDATOR {

        public interface MODE {

            public static long CREATE = 0;
            public static long EDIT = 1;
        }
    }
    
    public interface WEB {

        public static String BASE = "/SimpleStockManager";
        public static String INDEX = BASE + "/index.xhtml";
        public static String LOGIN = BASE + "/login.xhtml";

//        public interface ADD {
//
//            public static String ADD = PROJECT.WEB + "/add";
//            public static String CLIENT = ADD + "/client.xhtml";
//        }
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
}
