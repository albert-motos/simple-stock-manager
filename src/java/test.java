
import com.development.simplestockmanager.business.persistence.Language;
import com.development.simplestockmanager.business.persistence.SexType;
import com.development.simplestockmanager.web.common.WebConstant;
import com.development.simplestockmanager.web.object.component.selector.LanguageSelector;
import com.development.simplestockmanager.web.object.component.selector.SexTypeSelector;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author foxtrot
 */
public class test {
    public static void main(String[] args) {
        SexTypeSelector selector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, "es_ES");
        selector.setSelection(selector.getList().get(1));
        SexType sexType = selector.getSelectedValue();
        
        SexTypeSelector selector2 = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, "en_US", sexType);
        System.out.println(selector2.getList());
        System.out.println(selector2.getSelection());
        System.out.println(selector2.getSelectedValue());
        
        
////        System.out.println(selector.getDisplayName(sexType));
//        System.out.println(selector.getList());
//        selector.setSelection("");
//        System.out.println(selector.getSelectedValue());
    }
}
