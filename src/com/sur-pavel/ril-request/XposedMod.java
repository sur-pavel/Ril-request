package de.robv.android.xposed.mods.ril-request;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import android.widget.TextView;

public class CatchOem implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.android.internal.telephony"))
            return;

        findAndHookMethod("com.android.internal.telephony.RIL", lpparam.classLoader, 
                          "invokeOemRilRequestRaw", "android.os.Bundle", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
	XposedBridge.log("invokeOemRilRequestRaw arguments:" + tv);
          }
        });
    }
}


/* XposedHelpers.callMethod(param.thisObject, "invokeOemRilRequestRaw", );


Method m = XposedHelpers.findMethodExact(clazz, "getString", String.class);

    m.setAccessible(true);

    XposedBridge.hookMethod(m, new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            log("call -> " + (String) param.args[0]);
            super.beforeHookedMethod(param);
        }
    });

    String id = (String) m.invoke(null, "ro.build.id");
    log("step 1 try invoke->" + id);
    log("step 2 Call Build.ID ->" + Build.ID);}}`   */
