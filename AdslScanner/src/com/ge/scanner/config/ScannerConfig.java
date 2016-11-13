package com.ge.scanner.config;

import com.ge.util.IniOperation;

import java.io.File;

/**
 * Created by Storm_Falcon on 2016/11/11.
 *
 */
public class ScannerConfig {

	private IniOperation mIni;

	private ScannerConfig() {
		mIni = new IniOperation();
		String iniPath = System.getProperty("user.dir") + File.separatorChar + "scanner.ini";
		if (!mIni.loadIni(iniPath)) {
			System.out.println("File \"" + iniPath + "\" not found.");
			System.exit(-1);
		}
	}

	private static ScannerConfig instance;
	public static synchronized ScannerConfig getInstance() {
		if (instance == null) {
			instance = new ScannerConfig();
		}
		return instance;
	}

	public String getCmInfo(String key) {
		return mIni.getKeyValue("CM", key);
	}

	public String getCoaClassName(String vendorId) {
		return mIni.getKeyValue("Coa-Classes", vendorId);
	}

	public String getCrmProxy() {
		return mIni.getKeyValue("CRM-Proxy", "url");
	}

	public String getTimeLogValue(String key) {
		return mIni.getKeyValue("Timer-Log", key);
	}

	public int getScannerValue(String key) {
		return mIni.getKeyValueInt("Scanner", key);
	}

	public int getHealerValue(String key) {
		return mIni.getKeyValueInt("Healer", key);
	}
}