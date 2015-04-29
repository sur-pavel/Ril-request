package de.robv.android.xposed.mods.ril-request;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class CatchOem implements IXposedHookLoadPackage {
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals(" com.android.internal.telephony"))
            return;

        findAndHookMethod("com.android.internal.telephony.RIL", lpparam.classLoader, "invokeOemRilRequestRaw", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
	XposedBridge.log("LocalRILRequest:" + localRILRequest);
  XposedBridge.log("ParamArrayOfByte :" + paramArrayOfByte);  }
        });
    }
}