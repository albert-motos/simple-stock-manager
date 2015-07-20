package com.development.simplestockmanager.business.object.nullpackage;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.AnalyticsTime;

/**
 * Null class for AnalyticsTime object
 *
 * @author foxtrot
 */
public class AnalyticsTimeNull extends AnalyticsTime {

    public AnalyticsTimeNull() {
        super(BusinessConstant.IDENTIFIER.INVALID);
    }
}
