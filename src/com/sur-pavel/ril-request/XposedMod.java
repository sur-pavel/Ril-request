package de.robv.android.xposed.mods.ril-request;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.widget.TextView;

public class CatchOem implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals(" com.android.internal.telephony"))
            return;

        findAndHookMethod("com.android.internal.telephony.RIL", lpparam.classLoader, 
                          "invokeOemRilRequestRaw", "android.os.Bundle", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
	TextView tv = (TextView) param.thisObject;
	XposedBridge.log("invokeOemRilRequestRaw arguments:" + tv);
          }
        });
    }
}


/* XposedHelpers.callMethod(param.thisObject, "invokeOemRilRequestRaw", );*/
