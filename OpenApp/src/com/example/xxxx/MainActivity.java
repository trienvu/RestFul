package com.example.xxxx;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.property.Organization;
import ezvcard.property.StructuredName;
import ezvcard.property.Telephone;
import ezvcard.property.Title;
import ezvcard.property.Url;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public static void importVCard() throws Throwable, IOException {
		File file = new File("F:/VcardFile/MyCard.vcf");
		List<VCard> vCards = Ezvcard.parse(file).all();

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
			System.out.format("%s", firstName + ":" + lastName + ":"
					+ prefixName + ":" + company + ":" + department + ":"
					+ titles.get(0).getValue() + ":" + homeNumber + ":"
					+ mobileNumber + ":" + workNumber + ":" + faxNumber + ":"
					+ otherNumber + ":" + note);

		}
	}
}
