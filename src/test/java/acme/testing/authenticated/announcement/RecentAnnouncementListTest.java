
package acme.testing.authenticated.announcement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Announcement;
import acme.framework.helpers.FactoryHelper;
import acme.testing.TestHarness;

public class RecentAnnouncementListTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	@Autowired
	private AnnouncementRepository repository;
		
	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
		FactoryHelper.autowire(this);
		this.patchAnnouncements();
	}
	
	protected void patchAnnouncements() {
		Collection<Announcement> announcements;
		Date moment;

		announcements = this.repository.findAnnouncementsToPatch();
		for (final Announcement announcement : announcements) {
			moment = this.adjustMoment(announcement.getCreationMoment());
			announcement.setCreationMoment(moment);
			this.repository.save(announcement);
		}
	}
	protected String computeDeltaMoment(final int deltaDays) {
		assert deltaDays <= 0;

		String result;
		Calendar calendar;
		SimpleDateFormat formatter;

		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, deltaDays);
		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		result = formatter.format(calendar.getTime());

		return result;
	}

	
	protected Date adjustMoment(final Date currentMoment) {
		assert currentMoment != null;

		Date result;
		Calendar calendar;
		int day;

		calendar = Calendar.getInstance();

		calendar.setTime(currentMoment);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, -day);

		result = calendar.getTime();

		return result;

	}



	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int deltaDays, final String title, final String body, final String criticalFlag, final String link) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List Recent Announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		String moment;
		moment = this.computeDeltaMoment(deltaDays);
		
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, title);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("creationMoment", moment);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("criticalFlag", criticalFlag);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
		
	}
}
