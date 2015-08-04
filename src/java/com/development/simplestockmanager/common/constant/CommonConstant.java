package com.development.simplestockmanager.common.constant;

/**
 *
 * @author foxtrot
 */
public interface CommonConstant {

    public static String LANGUAGE_PATH = "com.development.simplestockmanager.common.language.language";

    public interface LANGUAGE {

        public static String EN_US = "en_US";
        public static String CA_ES = "ca_ES";
        public static String ES_ES = "es_ES";
    }

    public interface ENTITY {

        public static String TEXT = "entity.";

        public static String HEADER = TEXT + "header";
        public static String BRAND = TEXT + "brand";
        public static String CLIENT = TEXT + "client";
        public static String EMPLOYEE = TEXT + "employee";
        public static String PRODUCT = TEXT + "product";
        public static String PROVIDER = TEXT + "provider";
        public static String STORE = TEXT + "store";

    }

    public interface TYPE {

        public static String TEXT = "type.";

        public static String HEADER = TEXT + "header";
        public static String EMPLOYEE_TYPE = TEXT + "employee_type";
        public static String PAYMENT_TYPE = TEXT + "payment_type";
        public static String PRICE_TYPE = TEXT + "price_type";
        public static String PRODUCT_TYPE = TEXT + "product_type";
        public static String SEX_TYPE = TEXT + "sex_type";

    }

    public interface HEADER {

        public static String TEXT = "header.";

        public static String CREATE = TEXT + "create";
        public static String SEARCH = TEXT + "search";

    }

    public interface PAGE {

        public static String TEXT = "page.";

        public static String ATTRIBUTES = TEXT + "attributes";
        public static String BROWSER = TEXT + "browser";
        public static String CREDENTIALS = TEXT + "credentials";
        public static String ENABLE = TEXT + "enable";
        public static String LIST = TEXT + "list";
        public static String MANAGER = TEXT + "manager";
        public static String VIEWER = TEXT + "viewer";
        public static String VISIBILITY = TEXT + "visibility";

    }

    public interface VARIANT {

        public interface ARTICLE {

            public static String TEXT = ".article";
            public static String CODE = "{A}";
        }

        public interface COMMENT {

            public static String CODE = "{C}";
        }
        
        public interface GENERAL {

            public static String TEXT = ".general";
            public static String CODE = "{G}";
        }

        public interface SPECIFIC {

            public static String TEXT = ".specific";
            public static String CODE = "{S}";
        }

        public interface TYPE {

            public static String TEXT = ".type";
            public static String CODE = "{T}";
        }

        public interface IDENTIFIER {

            public static String CODE = "{ID}";
        }
        
    }

    public interface COLUMN {

        public static String TEXT = "column.";
        public static String BASE = TEXT + "base";
    }

    public interface LABEL {

        public static String TEXT = "label.";
        public static String BASE = TEXT + "base";

        public static String FIRSTNAME = TEXT + "firstname";
        public static String LASTNAME = TEXT + "lastname";
        public static String SEX_TYPE = TEXT + "sex_type";
        public static String BORN_DATE = TEXT + "born_date";
        public static String PHONE_NUMBER = TEXT + "phone_number";
        public static String EMAIL = TEXT + "email";
        public static String USERNAME = TEXT + "username";
        public static String PASSWORD = TEXT + "password";
        public static String EMPLOYEE_TYPE = TEXT + "employee_type";
        public static String LANGUAGE_TYPE = TEXT + "language";
        public static String NAME = TEXT + "name";
        public static String DESCRIPTION = TEXT + "description";
        public static String IDENTIFIER = TEXT + "identifier";
        public static String PRODUCT_TYPE = TEXT + "product_type";
        public static String PRODUCT = TEXT + "product";
        public static String BRAND = TEXT + "brand";
        public static String PROVIDER = TEXT + "provider";
        public static String STREET = TEXT + "street";
        public static String CITY = TEXT + "city";
        public static String STATE = TEXT + "state";
        public static String COUNTRY = TEXT + "country";
        public static String EMPLOYEE = TEXT + "employee";

        public static String TYPE = TEXT + "type";

        public interface TRANSLATION {

            public static String TEXT = LABEL.TEXT + "translation.";

            public static String EN_US = TEXT + "en_us";
            public static String CA_ES = TEXT + "ca_es";
            public static String ES_ES = TEXT + "es_es";
        }

        public static String STATUS = TEXT + "status";

    }

    public interface SPECIAL {

        public static String TEXT = "special.";
        public static String NON_SELECTION = TEXT + "non_selection";
        public static String BETWEEN = TEXT + "between";
        public static String CONJUCTION = TEXT + "conjuction";
        public static String COUNTER = TEXT + "counter";
        public static String VISIBLE = TEXT + "visible";
        public static String HIDDEN = TEXT + "hidden";
        public static String ACTIONS = TEXT + "actions";

        public interface AUDITORY {

            public static String TEXT = SPECIAL.TEXT + "auditory.";
            public static String HEADER = TEXT + "header";
            public static String CREATED_USER = TEXT + "created_user";
            public static String LAST_MODIFIED_USER = TEXT + "last_modified_user";
            public static String CREATED_DATE = TEXT + "created_date";
            public static String LAST_MODIFIED_DATE = TEXT + "last_modified_date";
        }
    }

    public interface MENU {

        public static String TEXT = "menu";

        public interface ENTITY {

            public static String TEXT = MENU.TEXT + ".entity";

            public interface BRAND {

                public static String TEXT = ENTITY.TEXT + ".brand";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface CLIENT {

                public static String TEXT = ENTITY.TEXT + ".client";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface EMPLOYEE {

                public static String TEXT = ENTITY.TEXT + ".employee";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface PRODUCT {

                public static String TEXT = ENTITY.TEXT + ".product";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface PROVIDER {

                public static String TEXT = ENTITY.TEXT + ".provider";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface STORE {

                public static String TEXT = ENTITY.TEXT + ".store";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }
        }

        public interface TYPE {

            public static String TEXT = MENU.TEXT + ".type";

            public interface EMPLOYEE_TYPE {

                public static String TEXT = TYPE.TEXT + ".employee_type";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface PAYMENT_TYPE {

                public static String TEXT = TYPE.TEXT + ".payment_type";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface PRICE_TYPE {

                public static String TEXT = TYPE.TEXT + ".price_type";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface PRODUCT_TYPE {

                public static String TEXT = TYPE.TEXT + ".product_type";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }

            public interface SEX_TYPE {

                public static String TEXT = TYPE.TEXT + ".sex_type";
                public static String CREATE = TEXT + ".create";
                public static String SEARCH = TEXT + ".search";
            }
        }
    }

    public interface MESSAGE {

        public static String TEXT = "message.";

        public interface SUMMARY {

            public static String TEXT = MESSAGE.TEXT + "summary.";
            public static String ERROR = TEXT + "error";
            public static String FATAL = TEXT + "fatal";
            public static String INFO = TEXT + "info";
            public static String WARNING = TEXT + "warning";
        }

        public interface DETAIL {

            public static String TEXT = MESSAGE.TEXT + "detail.";
            public static String BASE = TEXT + "base";
            public static String COMMENT = TEXT + "comment";

            public interface FATAL {

                public static String TEXT = DETAIL.TEXT + "fatal.";
                public static String DATABASE = TEXT + "database";
            }

            public interface ERROR {

                public static String TEXT = DETAIL.TEXT + "error.";
                public static String SINGULAR = TEXT + "singular";
                public static String PLURAL = TEXT + "plural";
                
                public static String DATE = TEXT + "date";
                public static String UNIQUE = TEXT + "unique";
                public static String PRODUCT = TEXT + "product";
            }

            public interface INFO {

                public static String TEXT = DETAIL.TEXT + "info.";
                public static String CREATE = TEXT + "create";
                public static String EDIT = TEXT + "edit";
                public static String NONE = TEXT + "none";
            }
            
            public interface WARNING {

                public static String TEXT = DETAIL.TEXT + "warning.";
                public static String SINGULAR = TEXT + "singular";
                public static String PLURAL = TEXT + "plural";
                
                public static String SELECTOR = TEXT + "selector";
            }
            
        }

        /**
        public interface WARNING {

            public static String TEXT = MESSAGE.TEXT + ".warning";
            public static String FIRSTNAME = TEXT + ".firstname";
            public static String LASTNAME = TEXT + ".lastname";
            public static String SEX_TYPE = TEXT + ".sextype";
            public static String BORN_DATE = TEXT + ".borndate";
            public static String PHONE_NUMBER = TEXT + ".phonenumber";
            public static String EMAIL = TEXT + ".email";
            public static String SUMMARY = TEXT + ".summary";
            public static String EMPLOYEE_TYPE = TEXT + ".employeetype";
            public static String LANGUAGE = TEXT + ".language";
            public static String USERNAME = TEXT + ".username";
            public static String PASSWORD = TEXT + ".password";
            public static String NAME = TEXT + ".name";
            public static String DESCRIPTION = TEXT + ".description";
            public static String IDENTIFIER = TEXT + ".identifier";
            public static String PRODUCT_TYPE = TEXT + ".producttype";
            public static String PROVIDER = TEXT + ".provider";
            public static String BRAND = TEXT + ".brand";
            public static String STREET = TEXT + ".street";
            public static String CITY = TEXT + ".city";
            public static String STATE = TEXT + ".state";
            public static String COUNTRY = TEXT + ".country";
            public static String EMPLOYEE = TEXT + ".employee";
            public static String TYPE = TEXT + ".type";

            public interface TRANSLATION {

                public static String TEXT = WARNING.TEXT + ".translation";
                public static String CA_ES = TEXT + ".ca_es";
                public static String ES_ES = TEXT + ".es_es";
                public static String EN_US = TEXT + ".en_us";

            }

            public interface DETAIL {

                public static String TEXT = WARNING.TEXT + ".detail";
                public static String SINGULAR = TEXT + ".singular";
                public static String PLURAL = TEXT + ".plural";
            }
        }

        public interface ERROR {

            public static String TEXT = MESSAGE.TEXT + ".error";
            public static String BORN_DATE = TEXT + ".borndate";
            public static String USERNAME = TEXT + ".username";
            public static String NAME = TEXT + ".name";
            public static String PRODUCT = TEXT + ".product";

            public static String SUMMARY = TEXT + ".summary";

            public static String TYPE = TEXT + ".type";

            public interface TRANSLATION {

                public static String TEXT = WARNING.TEXT + ".translation";
                public static String CA_ES = TEXT + ".ca_es";
                public static String ES_ES = TEXT + ".es_es";
                public static String EN_US = TEXT + ".en_us";

            }

            public interface DETAIL {

                public static String TEXT = ERROR.TEXT + ".detail";
                public static String SINGULAR = TEXT + ".singular";
                public static String PLURAL = TEXT + ".plural";
            }
        }

//        public interface FATAL {
//
//            public static String TEXT = MESSAGE.TEXT + ".fatal";
//
//            public static String SUMMARY = TEXT + ".summary";
//
//            public interface DETAIL {
//
//                public static String TEXT = FATAL.TEXT + ".detail";
//                public static String DATABASE = TEXT + ".database";
//            }
//        }
        public interface INFO {

            public static String TEXT = MESSAGE.TEXT + ".info";

            public static String SUMMARY = TEXT + ".summary";

            public interface DETAIL {

                public static String TEXT = INFO.TEXT + ".detail";

                public interface OBJECT {

                    public static String TEXT = DETAIL.TEXT + ".object";
                    public static String BRAND = TEXT + ".brand";
                    public static String CLIENT = TEXT + ".client";
                    public static String EMPLOYEE = TEXT + ".employee";
                    public static String PRODUCT = TEXT + ".product";
                    public static String PROVIDER = TEXT + ".provider";
                    public static String STORE = TEXT + ".store";

                    public static String EMPLOYEE_TYPE = TEXT + ".employee_type";
                    public static String PAYMENT_TYPE = TEXT + ".payment_type";
                    public static String PRICE_TYPE = TEXT + ".price_type";
                    public static String PRODUCT_TYPE = TEXT + ".product_type";
                    public static String SEX_TYPE = TEXT + ".sex_type";
                }

                public interface ACTION {

                    public static String TEXT = DETAIL.TEXT + ".action";
                    public static String CREATE = TEXT + ".create";
                    public static String EDIT = TEXT + ".edit";
                    public static String NONE = TEXT + ".none";
                }
            }
        }
        * */
    }

    /**
     * ******************************
     */
    public interface LABEL2 {

        public static String TEXT = "label";
        public static String FIRSTNAME = TEXT + ".firstname";
        public static String LASTNAME = TEXT + ".lastname";
        public static String SEX_TYPE = TEXT + ".sextype";
        public static String BORN_DATE = TEXT + ".borndate";
        public static String PHONE_NUMBER = TEXT + ".phonenumber";
        public static String EMAIL = TEXT + ".email";
        public static String USERNAME = TEXT + ".username";
        public static String PASSWORD = TEXT + ".password";
        public static String EMPLOYEE_TYPE = TEXT + ".employeetype";
        public static String LANGUAGE_TYPE = TEXT + ".language";
        public static String NAME = TEXT + ".name";
        public static String DESCRIPTION = TEXT + ".description";
        public static String IDENTIFIER = TEXT + ".identifier";
        public static String PRODUCT_TYPE = TEXT + ".producttype";
        public static String BRAND = TEXT + ".brand";
        public static String PROVIDER = TEXT + ".provider";
        public static String STREET = TEXT + ".street";
        public static String CITY = TEXT + ".city";
        public static String STATE = TEXT + ".state";
        public static String COUNTRY = TEXT + ".country";
        public static String EMPLOYEE = TEXT + ".employee";
        public static String TYPE = TEXT + ".type";

        public static String NON_SELECTION = TEXT + ".nonselection";
        public static String STATUS = TEXT + ".status";

        public interface AUDITORY {

            public static String TEXT = LABEL.TEXT + ".auditory";
            public static String HEADER = TEXT + ".header";
            public static String BETWEEN = TEXT + ".between";
            public static String AND = TEXT + ".and";
            public static String CREATED_USER = TEXT + ".user.create";
            public static String LAST_MODIFIED_USER = TEXT + ".user.update";
            public static String CREATED_DATE = TEXT + ".date.create";
            public static String LAST_MODIFIED_DATE = TEXT + ".date.update";
        }

        public interface ENABLE {

            public static String TEXT = LABEL.TEXT + ".enable";
            public static String BRAND = TEXT + ".brand";
            public static String CLIENT = TEXT + ".client";
            public static String EMPLOYEE = TEXT + ".employee";
            public static String PROVIDER = TEXT + ".provider";
            public static String PRODUCT = TEXT + ".product";
            public static String STORE = TEXT + ".store";
            public static String EMPLOYEE_TYPE = TEXT + ".employee_type";
            public static String PAYMENT_TYPE = TEXT + ".payment_type";
            public static String PRICE_TYPE = TEXT + ".price_type";
            public static String PRODUCT_TYPE = TEXT + ".product_type";
            public static String SEX_TYPE = TEXT + ".sex_type";
        }

        public interface TRANSLATION {

            public static String TEXT = LABEL.TEXT + ".translation";
            public static String CA_ES = TEXT + ".ca_es";
            public static String ES_ES = TEXT + ".es_es";
            public static String EN_US = TEXT + ".en_us";

        }
    }

    public interface LIST {

        public static String TEXT = "list";

        public interface COLUMN {

            public static String TEXT = LIST.TEXT + ".column";

            public interface STATUS {

                public static String TEXT = COLUMN.TEXT + ".status";
                public static String VISIBLE = TEXT + ".visible";
                public static String HIDDEN = TEXT + ".hidden";
            }

            public static String NAME = TEXT + ".name";
            public static String FIRSTNAME = TEXT + ".firstname";
            public static String LASTNAME = TEXT + ".lastname";
        }

    }

    public interface HEADER2 {

        public static String TEXT = "header";

        public interface ENTITY {

            public static String TEXT = ".entity";

            public interface BRAND {

                public static String TEXT = ENTITY.TEXT + ".brand";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";
                public static String LIST = TEXT + ".list";
                public static String BROWSER = TEXT + ".browser";
            }

            public interface CLIENT {

                public static String TEXT = ENTITY.TEXT + ".client";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";
                public static String LIST = TEXT + ".list";
                public static String BROWSER = TEXT + ".browser";
            }

            public interface EMPLOYE {

                public static String TEXT = ENTITY.TEXT + ".employee";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String CREDENTIALS = TEXT + ".credentials";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";
                public static String LIST = TEXT + ".list";
                public static String BROWSER = TEXT + ".browser";
            }

            public interface PRODUCT {

                public static String TEXT = ENTITY.TEXT + ".product";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";
                public static String LIST = TEXT + ".list";
                public static String BROWSER = TEXT + ".browser";
            }

            public interface PROVIDER {

                public static String TEXT = ENTITY.TEXT + ".provider";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";
                public static String LIST = TEXT + ".list";
                public static String BROWSER = TEXT + ".browser";
            }

            public interface STORE {

                public static String TEXT = ENTITY.TEXT + ".store";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String MANAGER = TEXT + ".manager";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";
                public static String LIST = TEXT + ".list";
                public static String BROWSER = TEXT + ".browser";
            }

        }

        public interface TYPE {

            public static String TEXT = ".type";

            public interface EMPLOYEE_TYPE {

                public static String TEXT = TYPE.TEXT + ".employee_type";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String BROWSER = TEXT + ".browser";
                public static String LIST = TEXT + ".list";
                public static String TRANSLATION = TEXT + ".translation";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";

            }

            public interface PAYMENT_TYPE {

                public static String TEXT = TYPE.TEXT + ".payment_type";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String BROWSER = TEXT + ".browser";
                public static String LIST = TEXT + ".list";
                public static String TRANSLATION = TEXT + ".translation";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";

            }

            public interface PRICE_TYPE {

                public static String TEXT = TYPE.TEXT + ".price_type";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String BROWSER = TEXT + ".browser";
                public static String LIST = TEXT + ".list";
                public static String TRANSLATION = TEXT + ".translation";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";

            }

            public interface PRODUCT_TYPE {

                public static String TEXT = TYPE.TEXT + ".product_type";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String BROWSER = TEXT + ".browser";
                public static String LIST = TEXT + ".list";
                public static String TRANSLATION = TEXT + ".translation";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";

            }

            public interface SEX_TYPE {

                public static String TEXT = TYPE.TEXT + ".sex_type";
                public static String ATTRIBUTES = TEXT + ".attributes";
                public static String BROWSER = TEXT + ".browser";
                public static String LIST = TEXT + ".list";
                public static String TRANSLATION = TEXT + ".translation";
                public static String VISIBILITY = TEXT + ".visibility";
                public static String VIEWER = TEXT + ".viewer";

            }
        }
    }

    public interface BUTTON {

        public static String TEXT = "button";
        public static String ADD = TEXT + ".add";
        public static String CANCEL = TEXT + ".cancel";
        public static String EDIT = TEXT + ".edit";
        public static String FINISH = TEXT + ".finish";
        public static String SEARCH = TEXT + ".search";
        public static String CLEAR = TEXT + ".clear";
    }

    public interface MESSAGE2 {

        public static String TEXT = "message";

        public interface WARNING {

            public static String TEXT = MESSAGE.TEXT + ".warning";
            public static String FIRSTNAME = TEXT + ".firstname";
            public static String LASTNAME = TEXT + ".lastname";
            public static String SEX_TYPE = TEXT + ".sextype";
            public static String BORN_DATE = TEXT + ".borndate";
            public static String PHONE_NUMBER = TEXT + ".phonenumber";
            public static String EMAIL = TEXT + ".email";
            public static String SUMMARY = TEXT + ".summary";
            public static String EMPLOYEE_TYPE = TEXT + ".employeetype";
            public static String LANGUAGE = TEXT + ".language";
            public static String USERNAME = TEXT + ".username";
            public static String PASSWORD = TEXT + ".password";
            public static String NAME = TEXT + ".name";
            public static String DESCRIPTION = TEXT + ".description";
            public static String IDENTIFIER = TEXT + ".identifier";
            public static String PRODUCT_TYPE = TEXT + ".producttype";
            public static String PROVIDER = TEXT + ".provider";
            public static String BRAND = TEXT + ".brand";
            public static String STREET = TEXT + ".street";
            public static String CITY = TEXT + ".city";
            public static String STATE = TEXT + ".state";
            public static String COUNTRY = TEXT + ".country";
            public static String EMPLOYEE = TEXT + ".employee";
            public static String TYPE = TEXT + ".type";

            public interface TRANSLATION {

                public static String TEXT = WARNING.TEXT + ".translation";
                public static String CA_ES = TEXT + ".ca_es";
                public static String ES_ES = TEXT + ".es_es";
                public static String EN_US = TEXT + ".en_us";

            }

            public interface DETAIL {

                public static String TEXT = WARNING.TEXT + ".detail";
                public static String SINGULAR = TEXT + ".singular";
                public static String PLURAL = TEXT + ".plural";
            }
        }

        public interface ERROR {

            public static String TEXT = MESSAGE.TEXT + ".error";
            public static String BORN_DATE = TEXT + ".borndate";
            public static String USERNAME = TEXT + ".username";
            public static String NAME = TEXT + ".name";
            public static String PRODUCT = TEXT + ".product";

            public static String SUMMARY = TEXT + ".summary";

            public static String TYPE = TEXT + ".type";

            public interface TRANSLATION {

                public static String TEXT = WARNING.TEXT + ".translation";
                public static String CA_ES = TEXT + ".ca_es";
                public static String ES_ES = TEXT + ".es_es";
                public static String EN_US = TEXT + ".en_us";

            }

            public interface DETAIL {

                public static String TEXT = ERROR.TEXT + ".detail";
                public static String SINGULAR = TEXT + ".singular";
                public static String PLURAL = TEXT + ".plural";
            }
        }

        public interface FATAL {

            public static String TEXT = MESSAGE.TEXT + ".fatal";

            public static String SUMMARY = TEXT + ".summary";

            public interface DETAIL {

                public static String TEXT = FATAL.TEXT + ".detail";
                public static String DATABASE = TEXT + ".database";
            }
        }

        public interface INFO {

            public static String TEXT = MESSAGE.TEXT + ".info";

            public static String SUMMARY = TEXT + ".summary";

            public interface DETAIL {

                public static String TEXT = INFO.TEXT + ".detail";

                public interface OBJECT {

                    public static String TEXT = DETAIL.TEXT + ".object";
                    public static String BRAND = TEXT + ".brand";
                    public static String CLIENT = TEXT + ".client";
                    public static String EMPLOYEE = TEXT + ".employee";
                    public static String PRODUCT = TEXT + ".product";
                    public static String PROVIDER = TEXT + ".provider";
                    public static String STORE = TEXT + ".store";

                    public static String EMPLOYEE_TYPE = TEXT + ".employee_type";
                    public static String PAYMENT_TYPE = TEXT + ".payment_type";
                    public static String PRICE_TYPE = TEXT + ".price_type";
                    public static String PRODUCT_TYPE = TEXT + ".product_type";
                    public static String SEX_TYPE = TEXT + ".sex_type";
                }

                public interface ACTION {

                    public static String TEXT = DETAIL.TEXT + ".action";
                    public static String CREATE = TEXT + ".create";
                    public static String EDIT = TEXT + ".edit";
                    public static String NONE = TEXT + ".none";
                }
            }
        }
    }
}
