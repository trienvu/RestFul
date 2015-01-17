package com.example.xxxx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Organization;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import ezvcard.property.Title;
import ezvcard.property.Url;

public class ImportDataActivity extends Activity {
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Uri data = getIntent().getData();
		if (data != null) {
			getIntent().setData(null);
			try {
				importData(data);
			} catch (Exception e) {
				// warn user about bad data here
				finish();
				return;
			}
		}

		// launch home Activity (with FLAG_ACTIVITY_CLEAR_TOP) here…
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TASK
				| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	private void importData(Uri data) {
		final String scheme = data.getScheme();
		InputStream is = null;
		JSONObject json = null;
		String jsonStream = null;
		if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
			try {
				ContentResolver cr = context.getContentResolver();
				is = cr.openInputStream(data);
				if (is == null)
					return;

				StringBuffer buf = new StringBuffer();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				String str;
				if (is != null) {
					while ((str = reader.readLine()) != null) {
						buf.append(str + "\n");
					}
				}
				json = new JSONObject(buf.toString());
				jsonStream = buf.toString();
			} catch (FileNotFoundException e) {

			} catch (IOException e) {

			} catch (JSONException e) {

			}

			finally {
				if (is != null)
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}

		// perform your data import here…
		try {
			importVCard(jsonStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void importVCard(String json) throws Throwable, IOException {
		// File file = new File("F:/VcardFile/MyCard.vcf");
		List<VCard> vCards = Ezvcard.parse(json).all();

		for (VCard vcard : vCards) {
			StructuredName structuredName = vcard.getStructuredName();
			String firstName = structuredName.getGiven(); // Ten dat khi sinh
			String lastName = structuredName.getFamily();
			String prefixName = structuredName.getPrefixes().toString();

			Organization organization = vcard.getOrganization();
			List<String> infors = organization.getValues();
			String company = null;
			if (infors.size() > 0)
				company = infors.get(0);
			String department = null;
			if (infors.size() > 1)
				department = infors.get(1);
			List<Title> titles = vcard.getTitles();
			List<Url> webs = vcard.getUrls();

			List<Telephone> telephones = vcard.getTelephoneNumbers();
			String homeNumber = null;
			String mobileNumber = null;
			String workNumber = null;
			String faxNumber = null;
			String otherNumber = null;

			int numberCount = 0;
			String numberTemp = null;
			Iterator<Telephone> iterator = telephones.iterator();
			while (iterator.hasNext()) {
				numberTemp = iterator.next().getText();
				if (numberCount == 0)
					mobileNumber = numberTemp;
				else if (numberCount == 1)
					homeNumber = numberTemp;
				else if (numberCount == 2)
					workNumber = numberTemp;
				else if (numberCount == 3)
					faxNumber = numberTemp;
				else
					otherNumber = numberTemp;
				numberCount++;
			}

			String note = vcard.getNotes().get(0).getValue();
			Log.d("Trien", "Result:" +  firstName + ":" + lastName + ":"
					+ prefixName + ":" + company + ":" + department + ":"
					+ titles.get(0).getValue() + ":" + homeNumber + ":"
					+ mobileNumber + ":" + workNumber + ":" + faxNumber + ":"
					+ otherNumber + ":" + note);
			System.out.format("%s", firstName + ":" + lastName + ":"
					+ prefixName + ":" + company + ":" + department + ":"
					+ titles.get(0).getValue() + ":" + homeNumber + ":"
					+ mobileNumber + ":" + workNumber + ":" + faxNumber + ":"
					+ otherNumber + ":" + note);
		}
	}
}
