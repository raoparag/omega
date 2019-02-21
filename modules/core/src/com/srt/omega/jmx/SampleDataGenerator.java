package com.srt.omega.jmx;

import com.haulmont.bali.db.QueryRunner;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.app.Authenticated;
import com.srt.omega.entity.Booking;
import com.srt.omega.entity.BookingItem;
import com.srt.omega.entity.BookingStatus;
import com.srt.omega.entity.Contact;
import com.srt.omega.entity.Country;
import com.srt.omega.entity.Industry;
import com.srt.omega.entity.Organisation;
import com.srt.omega.entity.PDPA;
import com.srt.omega.entity.PaymentCategory;
import com.srt.omega.entity.PaymentMethod;
import com.srt.omega.entity.Show;
import com.srt.omega.entity.ShowStatus;
import com.srt.omega.entity.ShowTiming;
import com.srt.omega.entity.ShowType;
import com.srt.omega.entity.ShowVenue;
import com.srt.omega.entity.TicketCategory;
import com.srt.omega.entity.TicketStatus;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component("omega_SampleDataGenerator")
public class SampleDataGenerator implements SampleDataGeneratorMBean {
    private Log log = LogFactory.getLog(getClass());
    @Inject
    protected Persistence persistence;
    @Inject
    private Metadata metadata;
    private List<ShowVenue> showVenueList = new ArrayList<>();
    private List<Show> showList = new ArrayList<>();
    private List<Organisation> organisationList = new ArrayList<>();
    private List<Industry> industryList = new ArrayList<>();
    private List<Industry> schoolList = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();
    private List<PaymentCategory> paymentCategoryList = new ArrayList<>();

    @Override
    @Authenticated
    public String generateSampleData(int numberOfShows, int numberOfOrgs, int numberOfBookings) {
        try {
            addIndustries();
            addCountries();
            addPaymentCategories();
            addShowVenues();
            addShows(numberOfShows);
            addShowTicketCategories();
            addShowTimings();
            addOrganisations(numberOfOrgs);
            addContacts(numberOfOrgs * 5);
            addBookings(numberOfBookings);
            return "Done";
        } catch (Throwable e) {
            log.error("Error", e);
            return ExceptionUtils.getStackTrace(e);
        }
    }

    private void addCountries() {
        persistence.runInTransaction(em -> {
            Country country = metadata.create(Country.class);
            country.setName("Singapore");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("USA");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("UK");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("Australia");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("Canada");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("India");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("Malaysia");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("Brazil");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("Brunei");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("China");
            em.persist(country);
            countryList.add(country);
            country = metadata.create(Country.class);
            country.setName("Thailand");
            em.persist(country);
            countryList.add(country);
        });
    }

    private void addPaymentCategories() {
        persistence.runInTransaction(em -> {
            PaymentCategory paymentCategory = metadata.create(PaymentCategory.class);
            paymentCategory.setName("School Paid");
            em.persist(paymentCategory);
            paymentCategoryList.add(paymentCategory);
            paymentCategory = metadata.create(PaymentCategory.class);
            paymentCategory.setName("Individual Paid");
            em.persist(paymentCategory);
            paymentCategoryList.add(paymentCategory);
            paymentCategory = metadata.create(PaymentCategory.class);
            paymentCategory.setName("Corp Paid");
            em.persist(paymentCategory);
            paymentCategoryList.add(paymentCategory);
            paymentCategory = metadata.create(PaymentCategory.class);
            paymentCategory.setName("School Comps");
            em.persist(paymentCategory);
            paymentCategoryList.add(paymentCategory);
            paymentCategory = metadata.create(PaymentCategory.class);
            paymentCategory.setName("Corporate Comps");
            em.persist(paymentCategory);
            paymentCategoryList.add(paymentCategory);
            paymentCategory = metadata.create(PaymentCategory.class);
            paymentCategory.setName("Other Comps");
            em.persist(paymentCategory);
            paymentCategoryList.add(paymentCategory);
        });
    }

    private void addIndustries() {
        persistence.runInTransaction(em -> {
            Industry industry = metadata.create(Industry.class);
            industry.setName("Aerospace & Defense");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Agriculture, Forestry, Fishing, Mining");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Alumni Associations");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("AmCham");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Association / Social Club");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("AustCham");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Automotive");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Banking & Financial Servcices");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("BritCham");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Chemical & Petroleum");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Childcare");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Computer Hardware");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Computer Software & Services");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Construction");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Consulting");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Consumer Products");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Education");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Enrichment Centre");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Enrichment Centre (Mandarin)");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("EuroCham");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Food & Beverage");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Government");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Healthcare");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Hospitality (Hotels & Leisure)");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("HR");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Insurance");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Int’l Sch – Middle & High School");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Int’l Sch – Preschool / Kindergarten");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Int’l Sch – Primary School");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("International School");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Junior College");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Kindergarten");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Legal");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Life Sciences");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Manufacturing");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Marketing, PR, Advertising");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Media / Entertainment");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Non Profit");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Oil & Gas");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Parent Association");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Pharmaceuticals");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Pre School");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Primary School");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Professional & Consulting Services");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Property & Construction");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Recreation Club");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Regular");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Relocation");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Research");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Retail");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Secondary School");
            em.persist(industry);
            industryList.add(industry);
            schoolList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Service Provider / Telecommunications");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Singapore Trade Association");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Technology");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Tertiary Institution");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Travel / Transportation");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Utilities & Energy");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("VWO");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Wholesale / Distribution");
            em.persist(industry);
            industryList.add(industry);
            industry = metadata.create(Industry.class);
            industry.setName("Other");
            em.persist(industry);
            industryList.add(industry);
        });
    }

    private void addShowVenues() {
        persistence.runInTransaction(em -> {
            ShowVenue showVenue = metadata.create(ShowVenue.class);
            showVenue.setName("KC Arts Center");
            showVenue.setCapacity(500);
            em.persist(showVenue);
            showVenueList.add(showVenue);
            showVenue = metadata.create(ShowVenue.class);
            showVenue.setName("Marina Bay Sands");
            showVenue.setCapacity(450);
            em.persist(showVenue);
            showVenueList.add(showVenue);
            showVenue = metadata.create(ShowVenue.class);
            showVenue.setName("SRT Theater");
            showVenue.setCapacity(255);
            em.persist(showVenue);
            showVenueList.add(showVenue);
            showVenue = metadata.create(ShowVenue.class);
            showVenue.setName("Singapore Arts Theater");
            showVenue.setCapacity(200);
            em.persist(showVenue);
            showVenueList.add(showVenue);
        });
    }

    private void addShows(int numberOfShows) {
        persistence.runInTransaction(em -> {
            for (int i = 0; i < numberOfShows; i++) {
                Show show = metadata.create(Show.class);
                show.setName(showNames[ThreadLocalRandom.current().nextInt(showNames.length)]);
                show.setCode(RandomStringUtils.randomAlphabetic(3).toUpperCase());
                show.setStartDate(Date.valueOf("2019-01-01"));
                show.setEndDate(Date.valueOf("2019-08-08"));
                show.setStatus(ShowStatus.Current);
                show.setType(ShowType.fromId(ThreadLocalRandom.current().nextInt(1, 6) * 10));
                show.setShowVenue(showVenueList.get(ThreadLocalRandom.current().nextInt(showVenueList.size())));
                em.persist(show);
                showList.add(show);
            }
        });
    }

    private void addShowTimings() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        persistence.runInTransaction(em -> {
            for (int i = 0; i < showList.size(); i++) {
                Show show = showList.get(i);
                Set<ShowTiming> showTimingSet = new HashSet<>();
                for (int j = 0; j < 10; j++) {
                    ShowTiming showTiming = metadata.create(ShowTiming.class);
                    try {
                        showTiming.setShowTime(formatter.parse("2019-02-" +
                                ThreadLocalRandom.current().nextInt(1, 28) + " " +
                                ThreadLocalRandom.current().nextInt(10, 24) + ":00:00"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    showTiming.setShow(showList.get(i));
                    em.persist(showTiming);
                    showTimingSet.add(showTiming);
                }
                show.setShowTimings(showTimingSet);
            }
        });
    }

    private void addShowTicketCategories() {
        persistence.runInTransaction(em -> {
            for (int i = 0; i < showList.size(); i++) {
                Show show = showList.get(i);
                Set<TicketCategory> ticketCategorySet = new HashSet<>();
                for (int j = 1; j < 11; j++) {
                    TicketCategory ticketCategory = metadata.create(TicketCategory.class);
                    ticketCategory.setCapacity(ThreadLocalRandom.current().nextInt(30, 60));
                    ticketCategory.setCategoryName("Category " + j);
                    ticketCategory.setPrice((double) ThreadLocalRandom.current().nextInt(50, 100));
                    ticketCategory.setShow(show);
                    em.persist(ticketCategory);
                    ticketCategorySet.add(ticketCategory);
                }
                show.setTicketCategories(ticketCategorySet);
            }
        });
    }

    private void addOrganisations(int numberOfOrgs) {
        List<String> orgNames = Arrays.asList(this.orgNames);
        Collections.shuffle(orgNames);
        List<String> schoolNames = Arrays.asList(this.schoolNames);
        Collections.shuffle(schoolNames);
        persistence.runInTransaction(em -> {
            for (int i = 0; i < numberOfOrgs; i++) {
                Organisation organisation = getOrganisation(orgNames, i);
                em.persist(organisation);
                organisationList.add(organisation);
            }
            for (int i = 0; i < numberOfOrgs / 2; i++) {
                Organisation organisation = getSchool(schoolNames, i);
                em.persist(organisation);
                organisationList.add(organisation);
            }
            Organisation organisation = getOrganisation(orgNames, 0);
            organisation.setName("SRT");
            em.persist(organisation);
            organisationList.add(organisation);
            organisation = getOrganisation(orgNames, 0);
            organisation.setName("Individual");
            em.persist(organisation);
            organisationList.add(organisation);
        });
    }

    private Organisation getOrganisation(List<String> orgNames, int i) {
        Organisation organisation = metadata.create(Organisation.class);
        organisation.setAddress1("Block " + RandomStringUtils.randomNumeric(2));
        organisation.setAddress2(streetNames[ThreadLocalRandom.current().nextInt(streetNames.length)]);
        organisation.setCity(RandomStringUtils.randomAlphabetic(10));
        organisation.setCountry(countryList.get(ThreadLocalRandom.current().nextInt(0, countryList.size() - 1)));
        organisation.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@gmail.com");
        organisation.setName(orgNames.get(i % orgNames.size()));
        organisation.setPhone(RandomStringUtils.randomNumeric(8));
        organisation.setPostalCode(RandomStringUtils.randomNumeric(6));
        organisation.setIndustry(industryList.get(ThreadLocalRandom.current().nextInt(0, industryList.size() - 1)));
        return organisation;
    }

    private Organisation getSchool(List<String> schoolNames, int i) {
        Organisation organisation = metadata.create(Organisation.class);
        organisation.setAddress1("Block " + RandomStringUtils.randomNumeric(2));
        organisation.setAddress2(streetNames[ThreadLocalRandom.current().nextInt(streetNames.length)]);
        organisation.setCity(RandomStringUtils.randomAlphabetic(10));
        organisation.setCountry(countryList.get(ThreadLocalRandom.current().nextInt(0, countryList.size() - 1)));
        organisation.setEmail(RandomStringUtils.randomAlphanumeric(10) + "@gmail.com");
        organisation.setName(schoolNames.get(i % schoolNames.size()));
        organisation.setPhone(RandomStringUtils.randomNumeric(8));
        organisation.setPostalCode(RandomStringUtils.randomNumeric(6));
        organisation.setIndustry(schoolList.get(ThreadLocalRandom.current().nextInt(0, schoolList.size() - 1)));
        return organisation;
    }

    private void addContacts(int numberOfContacts) {
        persistence.runInTransaction(em -> {
            for (int i = 0; i < numberOfContacts; i++) {
                Contact contact = metadata.create(Contact.class);
                contact.setOrganisation(organisationList.get(ThreadLocalRandom.current().nextInt(organisationList.size())));
                contact.setFirstName(firstNames[ThreadLocalRandom.current().nextInt(firstNames.length)]);
                contact.setLastName(lastNames[ThreadLocalRandom.current().nextInt(lastNames.length)]);
                contact.setEmail(contact.getFirstName().substring(0, (contact.getFirstName().length() / 2))
                        + contact.getLastName().substring(0, (contact.getLastName().length() / 2)) + "@gmail.com");
                contact.setPdpa(PDPA.fromId(ThreadLocalRandom.current().nextInt(1, 5) * 10));
                contact.setJobTitle(RandomStringUtils.randomAlphabetic(10));
                contact.setPhone(RandomStringUtils.randomNumeric(10));
                em.persist(contact);
            }
        });
    }

    private void addBookings(int numberOfBookings) {
        persistence.runInTransaction(em -> {
            for (int i = 0; i < numberOfBookings; i++) {
                Booking booking = metadata.create(Booking.class);
                booking.setShow(showList.get(ThreadLocalRandom.current().nextInt(showList.size())));
                booking.setBookingConfirmationNumber(booking.getShow().getCode() + String.format("%04d", i * 100));
                booking.setBookingStatus(BookingStatus.fromId(ThreadLocalRandom.current().nextInt(1, 5) * 10));
                booking.setOrganisation(organisationList.get(ThreadLocalRandom.current().nextInt(organisationList.size())));
                booking.setTicketStatus(TicketStatus.fromId(ThreadLocalRandom.current().nextInt(1, 3) * 10));
                booking.setPaymentMethod(PaymentMethod.fromId(ThreadLocalRandom.current().nextInt(1, 6) * 10));
                BookingItem bookingItem1 = getBookingItem(booking);
                BookingItem bookingItem2 = getBookingItem(booking);
                List<BookingItem> bookingItemList = new ArrayList<>();
                bookingItemList.add(bookingItem1);
                bookingItemList.add(bookingItem2);
                booking.setBookingItems(bookingItemList);
                calculateTotals(booking);
                em.persist(booking);
                em.persist(bookingItem1);
                em.persist(bookingItem2);
            }
        });
    }

    private void calculateTotals(Booking booking) {
        int totalQuantity = booking.getBookingItems().parallelStream().mapToInt(BookingItem::getQuantity).sum();
        booking.setTotalQuantity(totalQuantity);
        double totalAmount = 0;
        for (BookingItem item : booking.getBookingItems()) {
            totalAmount = totalAmount + (item.getQuantity() * (item.getTicketCategory().getPrice() + item.getSisticFee()));
            totalAmount = totalAmount - (item.getDiscount() / 100 * item.getQuantity() * item.getTicketCategory().getPrice());
        }
        booking.setTotalPrice(totalAmount);
    }

    private BookingItem getBookingItem(Booking booking) {
        BookingItem bookingItem = metadata.create(BookingItem.class);
        bookingItem.setBooking(booking);
        bookingItem.setPaymentCategory(paymentCategoryList.get(ThreadLocalRandom.current().nextInt(0, paymentCategoryList.size() - 1)));
        bookingItem.setDiscount((double) ThreadLocalRandom.current().nextInt(10));
        bookingItem.setQuantity(ThreadLocalRandom.current().nextInt(10));
        bookingItem.setSisticFee((double) ThreadLocalRandom.current().nextInt(5));
        List<TicketCategory> ticketCats = new ArrayList<>(booking.getShow().getTicketCategories());
        bookingItem.setTicketCategory(ticketCats.get(ThreadLocalRandom.current().nextInt(ticketCats.size())));
        List<ShowTiming> showTimings = new ArrayList<>(booking.getShow().getShowTimings());
        bookingItem.setShowTiming(showTimings.get(ThreadLocalRandom.current().nextInt(showTimings.size())));
        return bookingItem;
    }

    @Override
    public String removeAllData(String confirm) {
        if (!"ok".equals(confirm)) {
            return "Pass 'ok' in the parameter";
        }
        try {
            cleanupTable("OMEGA_BOOKING_ITEM");
            cleanupTable("OMEGA_BOOKING");
            cleanupTable("OMEGA_TICKET_CATEGORY");
            cleanupTable("OMEGA_SHOW_TIMING");
            cleanupTable("OMEGA_SHOW");
            cleanupTable("OMEGA_SHOW_VENUE");
            cleanupTable("OMEGA_CONTACT");
            cleanupTable("OMEGA_ORGANISATION");
            cleanupTable("OMEGA_PAYMENT_CATEGORY");
            cleanupTable("OMEGA_COUNTRY");
            cleanupTable("OMEGA_INDUSTRY");
            return "Done";
        } catch (Throwable e) {
            log.error("Error", e);
            return ExceptionUtils.getStackTrace(e);
        }
    }

    private void cleanupTable(String table) throws SQLException {
        QueryRunner runner = new QueryRunner(persistence.getDataSource());
        runner.update("delete from " + table);
    }

    private String[] firstNames = {"Arleen", "Tammara", "Tanesha", "Johna", "Suzy", "Margorie", "Fermina", "Samuel", "Willa", "Heide", "Carolyn", "Emmitt", "Tom", "Dwain", "Lakeesha", "Luella", "Ezra", "Lyndon", "Camilla", "Abbey", "Lashell", "Blair", "Jeanice", "Rosendo", "Carl", "Bobbie", "Jessi", "Krystyna", "Adaline", "Vivienne", "Kandi", "Mayme", "Cristobal", "Lorinda", "Brandie", "Ashleigh", "Shelba", "Hyon", "Lennie", "Dick", "Zoraida", "Diedre", "Edmond", "Jim", "Joanna", "Sherlene", "Sharie", "Buster", "Charley", "Arica"};
    private String[] lastNames = {"Malcom", "Roland", "Norman", "Elenora", "Roseann", "Fredericka", "Sylvie", "Floyd", "Domonique", "Detra", "Sharell", "Ahmed", "Noreen", "Bobbye", "Johanna", "Sanford", "Jackqueline", "Zora", "Juliette", "Sharmaine", "Sandra", "Millard", "Bernard", "Aracelis", "Mignon", "Eliana", "Chu", "Rheba", "Teofila", "Ramonita", "Sharlene", "Selene", "Tereasa", "Debby", "Leslie", "Letha", "Lauralee", "Weldon", "Christiane", "Carlena", "Mohamed", "Bernadine", "Normand", "Lori", "Lynelle", "Letitia", "Frederic", "Cassy", "Jarrett", "Kandace"};
    private String[] showNames = {"Death of a Salesman", "A Streetcar Named Desire", "Who’s Afraid of Virginia Woolf?", "Long Day’s Journey into Night", "Fences", "Angels in America: A Gay Fantasia on National Themes", "Waiting for Godot: A Tragicomedy in Two Acts", "Pygmalion", "A Raisin in the Sun", "Our Town", "Six Characters in Search of an Author", "The Glass Menagerie", "Glengarry Glen Ross", "August: Osage County", "True West", "The Iceman Cometh", "Look Back in Anger", "A View from the Bridge", "The Little Foxes", "The Real Thing", "Master Harold and the Boys", "The Homecoming", "Ruined", "Mother Courage and Her Children", "Six Degrees of Separation", "Doubt", "Top Girls", "Present Laughter", "Noises Off", "Marat/Sade", "The Lieutenant of Inishmore", "Machinal", "The Norman Conquests", "The Bald Soprano", "M. Butterfly", "The Dybbuk", "Saved", "Topdog/Underdogby Suzan-Lori Parks", "The Front Page", "Accidental Death of an Anarchist", "Picnic", "Journey’s End", "The Odd Couple", "The orphans’ home cycle", "The Women", "What The Butler Saw", "Awake and Sing!", "The Piano Lesson", "Uncommon Women and Others", "The Weir", "Gypsy", "My Fair Lady", "Sweeney Todd: The Demon Barber of Fleet Street", "Fiddler on the Roof", "Guys and Dolls", "Oklahoma!", "Cabaret", "West Side Story", "The Music Man", "A Chorus Line", "Chicago", "The Fantasticks", "Carousel", "Company", "Show Boat", "The King and I", "Little Shop of Horrors", "Sunday in the Park With George", "How to Succeed in Business Without Really Trying", "A Little Night Music", "She Loves Me", "Nine", "Follies", "Falsettos", "Ragtime", "Kiss Me, Kate", "1776", "Into the Woods", "A Funny Thing Happened on the Way to the Forum", "Urinetown", "The 25th Annual Putnam County Spelling Bee", "Wicked", "Hair", "Evita", "Hello, Dolly!", "La Cage aux Folles", "110 in the Shade", "The Producers", "Lady in the Dark", "City of Angels", "Dreamgirls", "Avenue Q", "The Book of Mormon", "42nd Street", "Brigadoon", "The Cradle Will Rock", "The Best Little Whorehouse in Texas", "Jesus Christ Superstar", "Once on This Island", "Adding Machine", "On the Town", "Les Miserables", "Bat Boy", "Caroline, or Change", "South Pacific", "The Pajama Game", "The Sound of Music", "Hairspray", "The Phantom of the Opera", "Damn Yankees", "Rent", "Grey Gardens", "Assassins", "Mame", "Man of La Mancha", "A Man of No Importance", "You're a Good Man, Charlie Brown", "Sweet Charity", "Camelot", "Anything Goes", "Wonderful Town", "The Light in the Piazza", "The Drowsy Chaperone", "The Full Monty", "Romance/Romance", "Godspell", "Of Thee I Sing", "The Secret Garden", "Pippin", "Kiss of the Spider Woman", "Finian's Rainbow", "Pal Joey", "Annie Get Your Gun", "Pacific Overtures", "Hedwig and the Angry Inch", "On Your Toes", "Candide", "Annie", "Beauty and the Beast", "Ain't Misbehavin'", "Bye Bye Birdie", "Jelly's Last Jam", "A New Brain", "Floyd Collins", "Grand Hotel", "Violet", "A Day in Hollywood, A Night in the Ukraine", "Bring in 'da Noise, Bring in 'da Funk", "The Scottsboro Boys", "Next to Normal"};
    private String[] orgNames = {"Aetos", "ANZ", "Bank of America Merrill Lynch", "BNP Paribas", "Breadtalk Group", "CapitaLand", "Certis Cisco Security", "Changi Airport Group", "Citibank", "CPG Corporation", "Dairy Farm International", "DBS", "Dell", "Deloitte", "Deutsche Bank", "DHL", "Dnata", "DTZ Singapore, a company of UGL", "Emerson Process Management Asia Pacific", "Ernst & Young", "ExxonMobil", "Far East Organisation", "Flextronics", "General Electric", "GLOBAL FOUNDRIES Singapore", "Hewlett-Packard", "Hitachi Asia", "Hong Leong Group", "HSBC", "IBM", "Infineon Technologies", "InterContinental Hotels Group", "ISS Facility Services", "Kentucky Fried Chicken", "Keppel Corporation Ltd", "KPMG", "M+W Group", "M1", "Marina Bay Sands", "MayBank", "McDonald’s", "MediaCorp", "Micron Semiconductor Asia", "Millennium & Copthorne Hotels", "NatSteel Holdings", "NTUC Fairprice", "OCBC", "Pan Pacific Hotels Group", "Panasonic Asia Pacific", "Philips Electronics (S) Pte Ltd", "PricewaterhouseCoopers", "Procter & Gamble", "PSA International", "RC Hotels Pte Ltd ", "Resort World Sentosa", "Rotary Engineering", "SATS", "Seagate", "Sembcorp", "Shell", "Sheng Siong", "Siemens", "Singapore Airlines", "Singapore Press Holdings (SPH)", "SingPost", "Singtel", "Sony", "ST Engineering", "ST Telemedia", "Standard Chartered Bank", "STATS ChipPAC", "UBS AG", "UOB", "UTAC", "Venture"};
    private String[] schoolNames = {"Admiralty Primary School", "Ahmad Ibrahim Primary School", "Ai Tong School", "Alexandra Primary School", "Anchor Green Primary School", "Anderson Primary School", "Anglo-Chinese School (Junior)", "Anglo-Chinese School (Primary)", "Angsana Primary School", "Ang Mo Kio Primary School", "Balestier Hill Primary School", "Beacon Primary School", "Bedok Green Primary School", "Bendemeer Primary School", "Blangah Rise Primary School", "Boon Lay Garden Primary School", "Bukit Panjang Primary School", "Bukit Timah Primary School", "Bukit View Primary School", "Canberra Primary School", "Canossa Convent Primary School", "Cantonment Primary School", "Casuarina Primary School", "Catholic High School (Primary)", "Cedar Primary School", "Changkat Primary School", "CHIJ (Katong) Primary", "CHIJ (Kellock)", "CHIJ Our Lady of Good Counsel", "CHIJ Our Lady of the Nativity", "CHIJ Our Lady Queen of Peace", "CHIJ Primary (Toa Payoh)", "CHIJ St. Nicholas Girls' School", "Chongfu School", "Chongzheng Primary School", "Chua Chu Kang Primary School", "Clementi Primary School", "Compassvale Primary School", "Concord Primary School", "Coral Primary School", "Corporation Primary School", "Da Qiao Primary School", "Damai Primary School", "Dazhong Primary School", "De La Salle School", "East Coast Primary School", "East Spring Primary School", "East View Primary School", "Edgefield Primary School", "Elias Park Primary School", "Endeavour Primary School", "Eunos Primary School", "Evergreen Primary School", "Fairfield Methodist School (Primary)", "Farrer Park Primary School", "Fengshan Primary School", "Fernvale Primary School", "First Toa Payoh Primary School", "Frontier Primary School", "Fuchun Primary School", "Fuhua Primary School", "Gan Eng Seng Primary School", "Geylang Methodist School (Primary)", "Gongshang Primary School", "Greendale Primary School", "Greenridge Primary School", "Greenwood Primary School", "Guangyang Primary School", "Haig Girls' School", "Holy Innocents' Primary School", "Henry Park Primary School", "Hong Wen School", "Horizon Primary School", "Hougang Primary School", "Huamin Primary School", "Innova Primary School", "Jiemin Primary School", "Jing Shan Primary School", "Junyuan Primary School", "Jurong Primary School", "Jurong West Primary School", "Juying Primary School", "Keming Primary School", "Kheng Cheng School", "Kong Hwa School", "Kranji Primary School", "Kuo Chuan Presbyterian Primary School", "Lakeside Primary School", "Lianhua Primary School", "Loyang Primary School", "MacPherson Primary School", "Maha Bodhi School", "Maris Stella High School", "Marsiling Primary School", "Marymount Convent School", "Mayflower Primary School", "Mee Toh School", "Meridian Primary School", "Methodist Girls' School (Primary)", "Montfort Junior School", "Nan Chiau Primary School", "Nan Hua Primary School", "Nanyang Primary School", "Ngee Ann Primary School", "Naval Base Primary School", "New Town Primary School", "Northland Primary School", "Northoaks Primary School", "North Spring Primary School", "North View Primary School", "North Vista Primary School", "Oasis Primary School", "Opera Estate Primary School", "Palm View Primary School", "Park View Primary School", "Pasir Ris Primary School", "Paya Lebar Methodist Girls' School (Primary)", "Pei Chun Public School", "Pei Hwa Presbyterian Primary School", "Pei Tong Primary School", "Peiying Primary School", "Pioneer Primary School", "Poi Ching School", "Princess Elizabeth Primary School", "Punggol Cove Primary School", "Punggol Green Primary School", "Punggol Primary School", "Punggol View Primary School", "Qifa Primary School", "Qihua Primary School", "Queenstown Primary School", "Radin Mas Primary School", "Raffles Girls' Primary School", "Red Swastika School", "Riverside Primary School", "River Valley Primary School", "Rivervale Primary School", "Rosyth School", "Rulang Primary School", "Sembawang Primary School", "Sengkang Green Primary School", "Seng Kang Primary School", "Shuqun Primary School", "Si Ling Primary School", "Singapore Chinese Girls' Primary School", "South View Primary School", "Springdale Primary School", "Stamford Primary School", "St. Andrew's Junior School", "St. Anthony's Canossian Primary School", "St. Anthony's Primary School", "St. Gabriel's Primary School", "St. Hilda's Primary School", "St. Joseph's Institution Junior", "St. Margaret's Primary School", "St. Stephen's School", "Tampines North Primary School", "Tampines Primary School", "Tanjong Katong Primary School", "Tao Nan School", "Teck Ghee Primary School", "Teck Whye Primary School", "Telok Kurau Primary School", "Temasek Primary School", "Townsville Primary School", "Unity Primary School", "Waterway Primary School", "Wellington Primary School", "West Grove Primary School", "West Spring Primary School", "Westwood Primary School", "West View Primary School", "White Sands Primary School", "Woodgrove Primary School", "Woodlands Primary School", "Woodlands Ring Primary School", "Xinghua Primary School", "Xingnan Primary School", "Xinmin Primary School", "Xishan Primary School", "Yangzheng Primary School", "Yew Tee Primary School", "Yio Chu Kang Primary School", "Yishun Primary School", "Yu Neng Primary School", "Yuhua Primary School", "Yumin Primary School", "Zhangde Primary School", "Zhenghua Primary School", "Zhonghua Primary School"};
    private String[] streetNames = {"Abingdon Road", "Adam Drive", "Adam Park", "Adam Road", "Adis Road", "Admiralty Drive", "Admiralty Lane", "Admiralty Link", "Admiralty Road East", "Admiralty Road West", "Admiralty Road", "Admiralty Street", "Ah Hood Road", "Ah Soo Garden", "Ah Soo Walk", "Aida Street", "Airline Road", "Airport Boulevard", "Airport Cargo Road", "Akyab Road", "Albert Street", "Alexandra Lane", "Alexandra Road", "Alexandra Terrace", "Aliwal Street", "Aljunied Avenue 1", "Aljunied Avenue 2", "Aljunied Avenue 3", "Aljunied Avenue 4", "Aljunied Avenue 5", "Aljunied Crescent", "Aljunied Road", "Alkaff Avenue", "Allamanda Grove", "Allanbrooke Road", "Allenby Road", "Almond Avenue", "Almond Crescent", "Almond Street", "Alnwick Road", "Ama Keng Road", "Amber Close", "Amber Gardens", "Amber Road", "Amberwood Close 1", "Amberwood Close 2", "Amberwood Close 3", "Amberwood Close 4", "Amberwood Close 5", "Amoy Street", "Anamalai Avenue", "Anchorvale Crescent", "Anchorvale Drive", "Anchorvale Lane", "Anchorvale Link", "Anchorvale Street", "Anderson Road", "Andover Road", "Andrew Avenue", "Andrew Road", "Ang Mo Kio Avenue 1", "Ang Mo Kio Avenue 10", "Ang Mo Kio Avenue 12", "Ang Mo Kio Avenue 2", "Ang Mo Kio Avenue 3", "Ang Mo Kio Avenue 4", "Ang Mo Kio Avenue 5", "Ang Mo Kio Avenue 6", "Ang Mo Kio Avenue 7", "Ang Mo Kio Avenue 8", "Ang Mo Kio Avenue 9", "Ang Mo Kio Central 1", "Ang Mo Kio Central 2", "Ang Mo Kio Central 2A", "Ang Mo Kio Central 3", "Ang Mo Kio Industrial Park 1", "Ang Mo Kio Industrial Park 2", "Ang Mo Kio Industrial Park 2A", "Ang Mo Kio Industrial Park 3", "Ang Mo Kio Street 11", "Ang Mo Kio Street 12", "Ang Mo Kio Street 13", "Ang Mo Kio Street 21", "Ang Mo Kio Street 22", "Ang Mo Kio Street 23", "Ang Mo Kio Street 24", "Ang Mo Kio Street 31", "Ang Mo Kio Street 32", "Ang Mo Kio Street 41", "Ang Mo Kio Street 42", "Ang Mo Kio Street 43", "Ang Mo Kio Street 4451", "Ang Mo Kio Street 52", "Ang Mo Kio Street 53", "Ang Mo Kio Street 54", "Ang Mo Kio Street 61", "Ang Mo Kio Street 62", "Ang Mo Kio Street 63", "Ang Mo Kio Street 64", "Ang Mo Kio Street 65", "Angklong Lane", "Angora Close", "Angsana Avenue", "Angullia Park", "Angus Street", "Ann Siang Hill", "Ann Siang Road", "Anson Road", "Anthony Road", "Arab Street", "Arcadia Road", "Architecture Drive", "Ardmore Park", "Armenian Street", "Arnasalam Chetty Road", "Aroozoo Avenue", "Aroozoo Lane", "Arthur Road", "Artillery Avenue", "Artillery Close", "Artillery Link", "Arts Link", "Arumugam Road", "Ascot Rise", "Ash Grove", "Ashwood Grove", "Asimont Lane", "Astrid Hill", "Attap Valley Road", "Auckland Road East", "Auckland Road West", "Ava Road", "Aviation Drive", "Avon Road", "Ayer Rajah Avenue", "Ayer Rajah Crescent", "", "Baboo Lane", "Baghdad Street", "Bah Soon Pah Road", "Bain Street", "Baker Street", "Balam Road", "Balestier Road", "Bali Lane", "Ballater Close", "Balmeg Hill", "Balmoral Crescent", "Balmoral Park", "Balmoral Road", "Ban San Street", "Banda Street", "Bangkit Road", "Barbary Walk", "Barker Road", "Bartley Road", "Bassein Road", "Bath Road", "Battery Road", "Bayfront Avenue", "Bayfront Drive", "Bays Water Road", "Bayshore Road", "Beach Road", "Beach View", "Beatty Road", "Beaulieu Road", "Bedford Road", "Bedok Avenue", "Bedok Close", "Bedok Garden", "Bedok Industrial Park C", "Bedok Industrial Park E", "Bedok Junction", "Bedok Lane", "Bedok North Avenue 1", "Bedok North Avenue 2", "Bedok North Avenue 3", "Bedok North Avenue 4", "Bedok North Drive", "Bedok North Interchange", "Bedok North Road", "Bedok North Street 1", "Bedok North Street 2", "Bedok North Street 3", "Bedok North Street 4", "Bedok North Street 5", "Bedok Reservoir Crescent", "Bedok Reservoir Ring Road", "Bedok Reservoir Road", "Bedok Reservoir View", "Bedok Ria Crescent", "Bedok Ria Drive", "Bedok Ria Place", "Bedok Ria Terrace", "Bedok Ria Walk", "Bedok Rise", "Bedok Road", "Bedok South Avenue 1", "Bedok South Avenue 2", "Bedok South Avenue 3", "Bedok South Road", "Bedok Terrace", "Bedok Walk", "Bee San Avenue", "Beechwood Grove", "Begonia Crescent", "Begonia Drive", "Begonia Lane", "Begonia Road", "Begonia Terrace", "Begonia Walk", "Belilios Lane", "Belilios Road", "Belimbing Avenue", "Belmont Road", "Belvedere Close", "Bencoolen Street", "Bendemeer Road", "Beng Huat Road", "Beng Wan Road", "Benoi Crescent", "Benoi Place", "Benoi Road", "Benoi Sector", "Beo Crescent", "Berkshire Road", "Bermuda Road", "Bernam Street", "Berrima Road", "Berwick Drive", "Bhamo Road", "Bideford Road", "Biggin Hill Road", "Bilal Lane", "Bin Tong Park", "Binchang Rise", "Binchang Walk", "Binjai Hill", "Binjai Park", "Binjai Rise", "Binjai Walk", "Birch Road", "Bird Park Drive", "Bishan Lane", "Bishan Place", "Bishan Road", "Bishan Street 11", "Bishan Street 12", "Bishan Street 13", "Bishan Street 14", "Bishan Street 21", "Bishan Street 22", "Bishan Street 23", "Bishan Street 24", "Bishops Place", "Bishopsgate", "Bishopswalk", "Blackmore Drive", "Blair Road", "Blandford Drive", "Bloxhome Drive", "Bo Seng Avenue", "Boat Quay", "Bodmin Drive", "Bond Terrace", "Bonham Street", "Boon Keng Road", "Boon Lay Avenue", "Boon Lay Drive", "Boon Lay Place", "Boon Lay Way", "Boon Leat Terrace", "Boon Tat Street", "Boon Teck Road", "Boon Tiong Road", "Borthwick Drive", "Boscombe Road", "Boundary Road", "Bournemouth Road", "Bowmont Gardens", "Braddell Hill", "Braddell Road", "Braemar Drive", "Branksome Road", "Bras Basah Road", "Bridport Avenue", "Bright Hill Crescent", "Bright Hill Drive", "Bright Hill Road", "Brighton Crescent", "Bristol Road", "Brizay Park", "Broadrick Close", "Broadrick Road", "Brockhampton Drive", "Brompton Road", "Brooke Road", "Brookvale Drive", "Brookvale Walk", "Buangkok Green", "Buckley Road", "Buffalo Lane", "Buffalo Road", "Bugis Street", "Bukit Arang Road", "Bukit Ayer Molek", "Bukit Batok Avenue 1", "Bukit Batok Central", "Bukit Batok East Avenue 2", "Bukit Batok East Avenue 3", "Bukit Batok East Avenue 4", "Bukit Batok East Avenue 5", "Bukit Batok East Avenue 6", "Bukit Batok Road", "Bukit Batok Street 11", "Bukit Batok Street 21", "Bukit Batok Street 22", "Bukit Batok Street 23", "Bukit Batok Street 24", "Bukit Batok Street 25", "Bukit Batok Street 31", "Bukit Batok Street 32", "Bukit Batok Street 33", "Bukit Batok Street 34", "Bukit Batok Street 51", "Bukit Batok Street 52", "Bukit Batok West Avenue 2", "Bukit Batok West Avenue 3", "Bukit Batok West Avenue 4", "Bukit Batok West Avenue 5", "Bukit Batok West Avenue 6", "Bukit Batok West Avenue 7", "Bukit Batok West Avenue 8", "Bukit Chermin Road", "Bukit Ho Swee Crescent", "Bukit Ho Swee Link", "Bukit Manis Central", "Bukit Manis Road", "Bukit Merah Lane 1", "Bukit Merah Lane 2", "Bukit Merah Lane 3", "Bukit Merah View", "Bukit Mugliston", "Bukit Panjang Link", "Bukit Panjang Ring Road", "Bukit Panjang Road", "Bukit Pasoh Road", "Bukit Purmei Avenue", "Bukit Purmei Road", "Bukit Purmei", "Bukit Sedap Road", "Bukit Teresa Close", "Bukit Teresa Road", "Bukit Timah Avenue", "Bukit Timah Link", "Bukit Timah Road", "Bukit Timah View", "Bukit Tinggi Road", "Bukit Tunggal Road", "Bunga Rampai Place", "Burghley Drive", "Burgundy Crescent", "Burgundy Rise", "Burmah Road", "Burn Road", "Burnfoot Terrace", "Buroh Lane", "Bury Road", "Business Link", "Bussorah Street", "Butterfly Avenue", "Butterworth Lane", "Buyong Road", "", "Cable Car Road", "Cable Road", "Cactus Crescent", "Cactus Drive", "Cactus Road", "Cairnhill Circle", "Cairnhill Rise", "Cairnhill Road", "Caldecott Close", "Calshot Road", "Camborne Road", "Cambridge Road", "Camden Park", "Cameron Court", "Camp Road", "Campbell Lane", "Camphor Avenue", "Canada Road", "Canberra Drive", "Canberra Lane", "Canberra Link", "Canberra Road", "Canberra Street", "Canning Lane", "Canning Rise", "Canning Walk Road", "Canterbury Road", "Canton Street", "Cantonment Link", "Cantonment Road", "Capricorn Drive", "Cardiff Grove", "Carisbrooke Grove", "Carlisle Road", "Carlton Avenue", "Carlton Walk", "Carmen Street", "Carmen Terrace", "Carmichael Road", "Carnation Drive", "Carpenter Street", "Carpmael Road", "Carver Street", "Caseen Street", "Cashew Crescent", "Cashew Link", "Cashew Road", "Cashew Terrace", "Cashin Street", "Cassia Crescent", "Cassia Drive", "Casuarina Road", "Casuarina Walk", "Catterick Road", "Cavan Road", "Cavenagh Road", "Cecil Street", "Cedar Avenue", "Cedarwood Grove", "Central Boulevard", "Central Circus", "Ceylon Lane", "Ceylon Road", "Chai Chee Avenue", "Chai Chee Drive", "Chai Chee Lane", "Chai Chee Road", "Chai Chee Street", "Chancery Hill Road", "Chancery Hill Walk", "Chancery Lane", "Chancery Road", "Chang Charn Road", "Change Alley", "Changi Coast Road", "Changi Coast Walk", "Changi Ferry Road", "Changi North Crescent", "Changi North Street 1", "Changi North Street 2", "Changi Road", "Changi South Avenue 1", "Changi South Avenue 2", "Changi South Avenue 3", "Changi South Avenue 4", "Changi South Lane", "Changi South Street 1", "Changi South Street 2", "Changi South Street 3", "Changi Village Road", "Chapel Close", "Chapel Road", "Charlton Lane", "Charlton Road", "Chartwell Drive", "Chatsworth Avenue", "Chatsworth Park", "Chatsworth Road", "Chay Yan Street", "Cheang Hong Lim Street", "Cheang Wan Seng Place", "Chee Hoon Avenue", "Chempaka Avenue", "Cheng Lim Farmway 2", "Cheng Lim Farmway 4", "Cheng Lim Farmway 5", "Cheng Lim Farmway 6", "Cheng Soon Crescent", "Cheng Soon Garden", "Cheng Soon Lane", "Cheng Yan Place", "Cheong Chin Nam Road", "Cheow Keng Road", "Chepstow Close", "Cherry Avenue", "Chestnut Avenue", "Chestnut Close", "Chestnut Crescent", "Chestnut Drive", "Chestnut Gardens", "Chestnut Lane", "Chestnut Terrace", "Cheviot Hill", "Chia Eng Say Road", "Chia Ping Road", "Chiap Guan Avenue", "Chiku Road", "Chiltern Drive", "Chin Bee Avenue", "Chin Bee Crescent", "Chin Bee Drive", "Chin Bee Road", "Chin Cheng Avenue", "Chin Chew Street", "Chin Hin Street", "Chin Swee Road", "Chin Terrace", "China Street", "Chinese Garden Road", "Chiselhurst Grove", "Chitty Road", "Choa Chu Kang Avenue 1", "Choa Chu Kang Avenue 2", "Choa Chu Kang Avenue 3", "Choa Chu Kang Avenue 4", "Choa Chu Kang Avenue 5", "Choa Chu Kang Central", "Choa Chu Kang Crescent", "Choa Chu Kang Drive", "Choa Chu Kang Link", "Choa Chu Kang Loop", "Choa Chu Kang North 5", "Choa Chu Kang North 6", "Choa Chu Kang North 7", "Choa Chu Kang Road", "Choa Chu Kang Street 51", "Choa Chu Kang Street 52", "Choa Chu Kang Street 53", "Choa Chu Kang Street 54", "Choa Chu Kang Street 62", "Choa Chu Kang Street 64", "Choa Chu Kang Terrace", "Choa Chu Kang Track 15", "Choa Chu Kang Track 33", "Choa Chu Kang Way", "Chong Kuo Road", "Choon Guan Street", "Chu Lin Road", "Chu Yen Street", "Chuan Drive", "Chuan Garden", "Chuan Hoe Avenue", "Chuan Lane", "Chuan Link", "Chuan Place", "Chuan Terrace", "Chuan View", "Chuan Walk", "Chulia Street", "Chun Tin Road", "Church Street", "Chwee Chian Road", "Chwee Chian View", "Circuit Link", "Circuit Road", "Circular Road", "Clacton Road", "Clarence Lane", "Clarke Quay", "Clarke Street", "Claymore Drive", "Claymore Hill", "Claymore Road", "Clemenceau Avenue", "Clementi Avenue 1", "Clementi Avenue 2", "Clementi Avenue 3", "Clementi Avenue 4", "Clementi Avenue 5", "Clementi Avenue 6", "Clementi Crescent", "Clementi Loop", "Clementi Road", "Clementi Street 11", "Clementi Street 12", "Clementi Street 13", "Clementi Street 14", "Clementi West Street 1", "Clementi West Street 2", "Clifton Vale", "Clive Street", "Clover Avenue", "Clover Close", "Clover Crescent", "Clover Rise", "Clover Way", "Club Street", "Clunny Hill", "Clunny Park", "Cluny Park Road", "Cluny Road", "Cochrane Crescent", "Colchester Grove", "Coldstream Avenue", "Coleman Street", "College Green", "College Road", "Collyer Quay", "Commonwealth Avenue West", "Commonwealth Avenue", "Commonwealth Close", "Commonwealth Crescent", "Commonwealth Drive", "Commonwealth Lane", "Commonwealth Link", "Compassvale Crescent", "Compassvale Drive", "Compassvale Lane", "Compassvale Road", "Compassvale Street", "Compassvale Walk", "Coniston Grove", "Connaught Drive", "Conway Circle", "Conway Grove", "Cook Street", "Cooling Close", "Coral Island", "Corfe Place", "Cornwall Gardens", "Cornwall Road", "Coronation Drive", "Coronation Road West", "Coronation Road", "Coronation Walk", "Cosford Road", "Cotswold Close", "Cottesmore Road", "Countryside Grove", "Countryside Link", "Countryside Place", "Countryside Road", "Countryside View", "Countryside Walk", "Court Road", "Cove Avenue", "Cove Drive", "Cove Grove", "Cove Way", "Cowdray Avenue", "Cox Terrace", "Craig Road", "Cranborne Road", "Crane Road", "Cranwell Road", "Crawford Lane", "Crawford Street", "Crescent Road", "Crichton Close", "Cross Street", "Croucher Road", "Crowhurst Drive", "Cuff Road", "Cumming Street", "Cuppage Road", "Cuscaden Road", "Cuscaden Walk", "Cypress Avenue", "Cyprus Road", "", "D'almeida Street", "Da Silva Lane", "Daffodil Drive", "Dafne Street", "Dahan Road", "Dairy Farm Crescent", "Dairy Farm Road", "Daisy Avenue", "Daisy Road", "Dakota Close", "Dakota Crescent", "Dalhousie Lane", "Dalkeith Road", "Dalvey Estate", "Dalvey Gate Road", "Dalvey Road", "Davidson Road", "Dawson Road", "De Souza Avenue", "De Souza Street", "Deal Road", "Dedap Link", "Dedap Place", "Dedap Road", "Defu Avenue 1", "Defu Avenue 2", "Defu Lane 1", "Defu Lane 10", "Defu Lane 11", "Defu Lane 2", "Defu Lane 3", "Defu Lane 4", "Defu Lane 5", "Defu Lane 6", "Defu Lane 7", "Defu Lane 8", "Defu Lane 9", "Delhi Road", "Delta Avenue", "Delta Road", "Dempsey Road", "Denham Close", "Denham Road", "Depot Close", "Depot Lane", "Depot Road", "Depot Walk", "Deptford Road", "Derbyshire Road", "Desker Road", "Devonshire Road", "Dickenson Hill Road", "Dickson Road", "Dido Street", "Digby Road", "Din Pang Avenue", "Dix Road", "Dock Road East", "Dock Road South", "Dock Road West", "Dorset Road", "Dover Avenue", "Dover Close East", "Dover Crescent", "Dover Drive", "Dover Rise", "Dover Road", "Draycott Drive", "Draycott Park", "Dryburgh Avenue", "Dublin Road", "Duchess Avenue", "Duchess Place", "Duchess Road", "Duchess Walk", "Duke's Road", "Duke Street", "Duku Lane", "Duku Place", "Duku Road", "Dunbar Walk", "Dundee Road", "Dunearn Close", "Dunearn Road", "Dunkirk Avenue", "Dunlop Street", "Dunman Lane", "Dunman Road", "Dunsfold Drive", "Durban Road", "Durham Road", "Duxton Hill", "Duxton Road", "Dyson Road", "", "Ean Kiam Place", "East Church Road", "East Coast Avenue", "East Coast Drive", "East Coast Road", "East Coast Terrace", "East Lagoon Link", "East Perimeter Road", "East Sussex Lane", "Eastern Link", "Eastwood Drive", "Eastwood Green", "Eastwood Place", "Eastwood Road", "Eastwood Terrace", "Eastwood Walk", "Eastwood Way", "Eaton Place", "Eaton Walk", "Eber Road", "Eden Grove", "Edgware Road", "Edinburgh Road", "Ee Teow Leng Road", "Elias Green", "Elias Road", "Elias Terrace", "Elite Park Avenue", "Elite Terrace", "Elizabeth Drive", "Ellenborough Street", "Ellington Square", "Elliot Road", "Elliot Walk", "Ellis Road", "Elm Avenue", "Emerald Hill Road", "Empress Place", "Empress Road", "Eng Hoon Street", "Eng Kong Crescent", "Eng Kong Drive", "Eng Kong Garden", "Eng Kong Lane", "Eng Kong Place", "Eng Kong Road", "Eng Kong Terrace", "Eng Neo Avenue", "Eng Watt Street", "Enggor Street", "Engineering Drive 1", "Engineering Drive 2", "Engineering Drive 3", "Engku Aman Road", "Enterprise Road", "Ernani Street", "Erskine Road", "Essex Road", "Estate Office Drive", "Ettrick Terrace", "Eu Chin Street", "Eu Tong Sen Street", "Eunos Avenue 1", "Eunos Avenue 2", "Eunos Avenue 3", "Eunos Avenue 4", "Eunos Avenue 5", "Eunos Avenue 5A", "Eunos Avenue 5B", "Eunos Avenue 6", "Eunos Avenue 7", "Eunos Avenue 7A", "Eunos Avenue 8", "Eunos Avenue 8A", "Eunos Crescent", "Eunos Link", "Eunos Road 1", "Eunos Road 2", "Eunos Road 3", "Eunos Road 4", "Eunos Road 5", "Eunos Road 6", "Eunos Road 7", "Eunos Road 8", "Eunos Terrace", "Evans Road", "Evelyn Road", "Evergreen Avenue", "Evergreen Gardens", "Everitt Road North", "Everitt Road", "Everton Park", "Everton Road", "Ewart Circus", "Ewart Park", "Ewe Boon Road", "Exeter Road", "Expo Drive", "", "Faber Avenue", "Faber Crescent", "Faber Drive", "Faber Green", "Faber Grove", "Faber Heights", "Faber Park", "Faber Terrace", "Faber Walk", "Faculty Avenue", "Fair Drive", "Fairways Drive", "Fairy Point Hill", "Fajar Road", "Falkland Road", "Fan Yoong Road", "Farleigh Avenue", "Farnborough Road", "Farrer Court", "Farrer Drive", "Farrer Garden", "Farrer Road", "Fernhill Close", "Fernhill Crescent", "Fernhill Road", "Fernwood Terrace", "Fidelio Street", "Fifth Avenue", "Fifth Lok Yang Road", "Figaro Street", "Fiji Road", "Finlayson Green", "Fir Avenue", "First Avenue", "First Hospital Avenue", "First Lok Yang Road", "First Street", "Fish Farm Road 1", "Fish Farm Road 2", "Fish Farm Road 3", "Fisher Street", "Fishery Port Road", "Five Foot Way", "Flanders Square", "Flint Street", "Flora Road", "Florence Close", "Florence Road", "Florida Road", "Florissa Park", "Flower Road", "Foch Road", "Folkestone Road", "Foo Kim Lin Road", "Ford Avenue", "Forfar Square", "Fort Canning Road", "Fort Road", "Fourth Avenue", "Fourth Chin Bee Road", "Fourth Lok Yang Road", "Fourth Street", "Fowlie Road", "Francis Thomas Drive", "Frankel Avenue", "Frankel Close", "Frankel Drive", "Frankel Place", "Frankel Street", "Frankel Terrace", "Frankel Walk", "French Road", "Fudu Park", "Fudu Walk", "Fullerton Road", "Fullerton Square", "Fulton Avenue", "Fulton Road", "", "Galistan Avenue", "Gallop Park Road", "Gallop Road", "Gallop Walk", "Gambas Avenue", "Gambir Walk", "Ganges Avenue", "Gangsa Road", "Garage Road", "Garden Avenue", "Garden Road", "Gardenia Road", "Garlick Avenue", "Gateway Avenue", "Gek Poh Road", "Gemmill Lane", "Genting Lane", "Genting Link", "Genting Road", "Gentle Drive", "Gentle Road", "George Street", "Gerald Crescent", "Gerald Drive", "Gerald Park", "Geylang Bahru Lane", "Geylang Bahru", "Geylang East Avenue 1", "Geylang East Avenue 2", "Geylang East Avenue 3", "Geylang East Central", "Geylang Road", "Geylang Serai", "Geylang Square", "Ghim Moh Road", "Gibraltar Crescent", "Gillman Heights", "Gilstead Road", "Gladiola Drive", "Glasgow Road", "Gloucester Road", "Goldhill Avenue", "Goldhill Drive", "Goldhill Place", "Goldhill Plaza", "Goldhill Rise", "Goldhill View", "Gombak Drive", "Gombak Rise", "Goodlink Park", "Goodman Road", "Goodwood Hill", "Goodwood Road", "Gopeng Street", "Gordon Road", "Gosport Road", "Grace Park", "Grace Walk", "Graham White Drive", "Grange Garden", "Grange Road", "Gray Lane", "Green Lane", "Greenbank Park", "Greendale Avenue", "Greendale Rise", "Greenfield Drive", "Greenleaf Avenue", "Greenleaf Drive", "Greenleaf Grove", "Greenleaf Lane", "Greenleaf Place", "Greenleaf Rise", "Greenleaf Road", "Greenleaf View", "Greenleaf Walk", "Greenmead Avenue", "Greenpark Avenue", "Greenridge Crescent", "Greenview Crescent", "Greenwood Avenue", "Greenwood Crescent", "Greenwood Grove", "Greenwood Lane", "Greenwood Link", "Greenwood Place", "Greenwood Walk", "Grove Avenue", "Grove Crescent", "Grove Drive", "Grove Lane", "Guan Chuan Street", "Guan Soon Avenue", "Guillemard Crescent", "Guillemard Lane", "Guillemard Road", "Gul Avenue", "Gul Circle", "Gul Crescent", "Gul Drive", "Gul Lane", "Gul Link", "Gul Road", "Gul Street 1", "Gul Street 2", "Gul Street 3", "Gul Street 4", "Gul Street 5", "Gul Way", "Gulega Road", "Gunner Lane", "Guok Avenue", "Gymkhana Avenue", "", "Hacienda Grove", "Hai Sing Crescent", "Hai Sing Road", "Haig Avenue", "Haig Lane", "Haig Road", "Haigsville Drive", "Haji Lane", "Halifax Road", "Halton Road", "Hamilton Place", "Hamilton Road", "Hampshire Road", "Hampstead Gardens", "Handy Road", "Happy Avenue Central", "Happy Avenue East", "Happy Avenue North", "Happy Avenue West", "Harbour Drive", "Harding Road", "Harlyn Road", "Harper Road", "Harrison Road", "Hartley Grove", "Harvey Avenue", "Harvey Close", "Harvey Crescent", "Harvey Road", "Hastings Road", "Havelock Link", "Havelock Road", "Havelock Square", "Hawkinge Road", "Hay Market", "Hazel Park Terrace", "Hemmant Road", "Hemsley Avenue", "Henderson Crescent", "Henderson Road", "Hendon Road", "Hendry Close", "Heng Mui Keng Terrace", "Hertford Road", "High Street", "Highgate Crescent", "Highgate Walk", "Highland Close", "Highland Road", "Highland Terrace", "Highland Walk", "Hill Street", "Hillcrest Road", "Hillside Drive", "Hillside Terrace", "Hillview Avenue", "Hillview Crescent", "Hillview Drive", "Hillview Link", "Hillview Road", "Hillview Terrace", "Hillview Way", "Hindhede Drive", "Hindhede Lane", "Hindhede Road", "Hindhede Walk", "Hindoo Road", "Ho Ching Road", "Hobart Road", "Hoe Chiang Road", "Hokien Street", "Holland Avenue", "Holland Close", "Holland Drive", "Holland Green", "Holland Grove Avenue", "Holland Grove Drive", "Holland Grove Lane", "Holland Grove Road", "Holland Grove Terrace", "Holland Grove View", "Holland Grove Walk", "Holland Heights", "Holland Hill", "Holland Lane", "Holland Park", "Holland Rise", "Holland Road North", "Holland Road South", "Holland Road West", "Holland Road", "Hollland Village", "Holt Road", "Hon Sui Sen Drive", "Hong Kah Circle", "Hong Kah Road", "Hong Lee Place", "Hong San Terrace", "Hong San Walk", "Hongkong Street", "Hooper Road", "Hoot Kiam Road", "Hornchurch Road", "Horne Road", "Horsham Road", "Hospital Drive", "Hougang Avenue 1", "Hougang Avenue 10", "Hougang Avenue 14", "Hougang Avenue 2", "Hougang Avenue 3", "Hougang Avenue 4", "Hougang Avenue 5", "Hougang Avenue 6", "Hougang Avenue 7", "Hougang Avenue 8", "Hougang Avenue 9", "Hougang Central", "Hougang Street 11", "Hougang Street 21", "Hougang Street 22", "Hougang Street 31", "Hougang Street 51", "Hougang Street 52", "Hougang Street 61", "Hougang Street 91", "Hougang Street 92", "Hougang Street 93", "How Sun Avenue", "How Sun Close", "How Sun Drive", "How Sun Road", "How Sun Walk", "Howard Road", "Hoy Fatt Road", "Hu Ching Road", "Hua Guan Avenue", "Hua Guan Crescent", "Huddington Avenue", "Hullet Road", "Hume Avenue", "Hun Yeang Road", "Hyde Park Gate", "Hyderabad Road", "Hylam Street", "Hythe Road", "", "Imbiah Hill Road", "Imbiah Walk", "Indus Road", "Inner Temple", "Institution Hill", "International Business Park", "International Road", "Ipoh Lane", "Iqbal Avenue", "Ironside Link", "Ironside Road", "Irrawaddy Road", "Irving Place", "Irving Road", "Irwell Bank Road", "Island Club Road", "Island Gardens Walk", "Istana Park", "", "Jacaranda Road", "Jago Close", "Jalan Adat", "Jalan Afifi", "Jalan Ahmad Ibrahim", "Jalan Ampang", "Jalan Ampas", "Jalan Anak Bukit", "Jalan Anak Patong", "Jalan Anggerek", "Jalan Angin Laut", "Jalan Angklong", "Jalan Antoi", "Jalan Arif", "Jalan Arnap", "Jalan Aruan", "Jalan Asas", "Jalan Asuhan", "Jalan Awan", "Jalan Awang", "Jalan Ayer", "Jalan Azam", "Jalan Bahagia", "Jalan Bahar", "Jalan Bahasa", "Jalan Bahtera", "Jalan Baiduri", "Jalan Bangau", "Jalan Bangket", "Jalan Bangsawan", "Jalan Basong", "Jalan Batai", "Jalan Batalong East", "Jalan Batalong", "Jalan Batu Nilam", "Jalan Batu", "Jalan Belangkas", "Jalan Belibas", "Jalan Bena", "Jalan Benaan Kapal", "Jalan Beringin", "Jalan Berjaya", "Jalan Berseh", "Jalan Berseri", "Jalan Besar", "Jalan Besut", "Jalan Bilal", "Jalan Binchang", "Jalan Bingka", "Jalan Binja", "Jalan Bintang Tiga", "Jalan Boon Lay", "Jalan Bukit Ho Swee", "Jalan Bukit Merah", "Jalan Buloh Perindu", "Jalan Bumbong", "Jalan Bunga Rampai", "Jalan Bunga Raya", "Jalan Bungar", "Jalan Buroh", "Jalan Chegar", "Jalan Chelagi", "Jalan Chempah", "Jalan Chempaka Kuning", "Jalan Chempaka Puteh", "Jalan Chempedak", "Jalan Chengam", "Jalan Chengkek", "Jalan Cherah", "Jalan Chermai", "Jalan Chermat", "Jalan Cherpen", "Jalan Chichau", "Jalan Chorak", "Jalan Chulek", "Jalan Daliah", "Jalan Damai", "Jalan Datoh", "Jalan Daud", "Jalan Demak", "Jalan Dermawan", "Jalan Derum", "Jalan Dinding", "Jalan Dondang Sayang", "Jalan Dua", "Jalan Dusan", "Jalan Elok", "Jalan Emas Urai", "Jalan Empat", "Jalan Enam", "Jalan Eunos", "Jalan Gaharu", "Jalan Gajus", "Jalan Gali Batu", "Jalan Gapis", "Jalan Gelam", "Jalan Gelegar", "Jalan Gelenggang", "Jalan Gemala", "Jalan Gembira", "Jalan Gendang", "Jalan Geneng", "Jalan Girang", "Jalan Gotong Royong", "Jalan Greja", "Jalan Grisek", "Jalan Gumilang", "Jalan Haji Alias", "Jalan Haji Salam", "Jalan Hajijah", "Jalan Hang Jebat", "Jalan Hari Raya", "Jalan Harom Setangkai", "Jalan Harum", "Jalan Hiboran", "Jalan Hikayat", "Jalan Hitam Manis", "Jalan Hock Chye", "Jalan Hussein", "Jalan Hwi Yoh", "Jalan Ibadat", "Jalan Ikan Merah", "Jalan Ilmu", "Jalan Inggu", "Jalan Insaf", "Jalan Intan", "Jalan Ishak", "Jalan Ismail", "Jalan Isnin", "Jalan Istimewa", "Jalan Jamal", "Jalan Jambu Ayer", "Jalan Jambu Batu", "Jalan Jambu Mawar", "Jalan Janggus", "Jalan Jarak", "Jalan Jati", "Jalan Jelita", "Jalan Jendela", "Jalan Jentera", "Jalan Jermin", "Jalan Jeruju", "Jalan Jintan", "Jalan Jitong", "Jalan Jong", "Jalan Joran", "Jalan Jurong Kechil", "Jalan Kakatua", "Jalan Kampong Chantek", "Jalan Kampong Siglap", "Jalan Kandis", "Jalan Kapal", "Jalan Kasau", "Jalan Kathi", "Jalan Kayu Manis", "Jalan Kayu", "Jalan Kebaya", "Jalan Kebun Limau", "Jalan Kechil", "Jalan Kechot", "Jalan Kechubong", "Jalan Kelabu Asap", "Jalan Kelawar", "Jalan Kelempong", "Jalan Keli", "Jalan Kelichap", "Jalan Kelulut", "Jalan Kemajuan", "Jalan Kemaman", "Jalan Kembang Melati", "Jalan Kembangan", "Jalan Kemboja", "Jalan Kemuning", "Jalan Kenarah", "Jalan Kerayong", "Jalan Keria", "Jalan Keris", "Jalan Keruing", "Jalan Kesoma", "Jalan Ketuka", "Jalan Ketumbit", "Jalan Khairuddin", "Jalan Khamis", "Jalan Kilang Barat", "Jalan Kilang Timor", "Jalan Kilang", "Jalan Klapa", "Jalan Kledek", "Jalan Klinik", "Jalan Korban", "Jalan Korma", "Jalan Krian", "Jalan Kuak", "Jalan Kuang", "Jalan Kubor", "Jalan kukoh", "Jalan Kumia", "Jalan Kuning", "Jalan Kupang", "Jalan Kuras", "Jalan Kwok Min", "Jalan Labu Ayer", "Jalan Labu Manis", "Jalan Labu Merah", "Jalan Labu Puteh", "Jalan Lakum", "Jalan Lam Huat", "Jalan Lam Sam", "Jalan Lana", "Jalan Langgar Bedok", "Jalan Lanjut", "Jalan Lapang", "Jalan Lateh", "Jalan Layang Layang", "Jalan Leban", "Jalan Lebat Daun", "Jalan Legundi", "Jalan Lekar", "Jalan Lekub", "Jalan Lembah Bedok", "Jalan Lembah Kallang", "Jalan Lembah Thomson", "Jalan Lempeng", "Jalan Lengkok Sembawang", "Jalan Lepas", "Jalan Lim Tai See", "Jalan Limau Balli", "Jalan Limau Kasturi", "Jalan Limau Manis", "Jalan Limau Nipis", "Jalan Limau Purut", "Jalan Limbok", "Jalan Lokam", "Jalan Loyang Besar", "Jalan Lye Kwee", "Jalan Ma'mor", "Jalan Machang", "Jalan Mahir", "Jalan Majapahit", "Jalan Malu Malu", "Jalan Manis", "Jalan Mariam", "Jalan Mas Kuning", "Jalan Mas Puteh", "Jalan Mashhor", "Jalan Masjid", "Jalan Mastuli", "Jalan Mat Jambol", "Jalan Mata Ayer", "Jalan Mawar", "Jalan Mayaanam", "Jalan Melati", "Jalan Melor", "Jalan Membina Barat", "Jalan Membina", "Jalan Mempurong", "Jalan Menarong", "Jalan Mendong", "Jalan Mengkudu", "Jalan Merah Saga", "Jalan Merbok", "Jalan Merdu", "Jalan Merlimau", "Jalan Mesin", "Jalan Mesra", "Jalan Minggu", "Jalan Minyak", "Jalan Muhibbah", "Jalan Mulia", "Jalan Murai", "Jalan Mutiara", "Jalan Naga Sari", "Jalan Nam Seng", "Jalan Naung", "Jalan Nipah", "Jalan Nira", "Jalan Novena Barat", "Jalan Novena Selatan", "Jalan Novena Timor", "Jalan Novena Utara", "Jalan Novena", "Jalan Nuri", "Jalan Pacheli", "Jalan Pakis", "Jalan Pandan", "Jalan Papan", "Jalan Paras", "Jalan Pari Burong", "Jalan Pari Dedap", "Jalan Pari Kikis", "Jalan Pari Unak", "Jalan Pasar Baru", "Jalan Pasir Ria", "Jalan Pasiran", "Jalan Payoh Lai", "Jalan Pelajau", "Jalan Pelangi", "Jalan Pelatina", "Jalan Pelatok", "Jalan Pelepah", "Jalan Pelikat", "Jalan Pemimpin", "Jalan Penjara", "Jalan Perahu", "Jalan Pergam", "Jalan Pernama", "Jalan Pesawat", "Jalan Piala", "Jalan Pinang", "Jalan Pintau", "Jalan Piring", "Jalan Pisang", "Jalan Pokok Serunai", "Jalan Punai", "Jalan Puteh Jerneh", "Jalan Puteh Jula Juli", "Jalan Puyoh", "Jalan Quee Chew", "Jalan Rabu", "Jalan Rahmat", "Jalan Raja Udang", "Jalan Rajah", "Jalan Rajawali", "Jalan Rakit", "Jalan Rama Rama", "Jalan Rasok", "Jalan Raya", "Jalan Rebana", "Jalan Redop", "Jalan Remaja", "Jalan Remis", "Jalan Rendang", "Jalan Rengas", "Jalan Rengkam", "Jalan Resak", "Jalan Ria", "Jalan Riang", "Jalan Rimau", "Jalan Rindu", "Jalan Rukam", "Jalan Rumah Tinggi", "Jalan Rumbia", "Jalan Rumia", "Jalan Sabit", "Jalan Sahabat", "Jalan Sajak", "Jalan Salang", "Jalan Sam Kongsi", "Jalan Samarinda", "Jalan Sampurna", "Jalan Samulun", "Jalan Sankam", "Jalan Sappan", "Jalan Satu", "Jalan Saudara Ku", "Jalan Sayang", "Jalan Seaview", "Jalan Sedap", "Jalan Segam", "Jalan Seh Chuan", "Jalan Sejarah", "Jalan Selamat", "Jalan Selanting", "Jalan Selaseh", "Jalan Selendang Delima", "Jalan Selimang", "Jalan Sembilang", "Jalan Semerbak", "Jalan Sempadan", "Jalan Senandong", "Jalan Senang", "Jalan Sendudok", "Jalan Seni", "Jalan Sentosa", "Jalan Senyum", "Jalan Seranggong Kechil", "Jalan Serene", "Jalan Serengam", "Jalan Seruling", "Jalan Setia", "Jalan Shaer", "Jalan Siantan", "Jalan Siap", "Jalan Sikudangan", "Jalan Simpang Bedok", "Jalan Sinar Bintang", "Jalan Sinar Bulan", "Jalan Sindor", "Jalan Singa", "Jalan Songket", "Jalan Soo Bee", "Jalan Sotong", "Jalan Suasa", "Jalan Suka", "Jalan Sukachita", "Jalan Sultan", "Jalan Sungei Belang", "Jalan Sungei Poyan", "Jalan Sungei", "Jalan Surau", "Jalan Taman", "Jalan Tamban", "Jalan Tambur", "Jalan Tampang", "Jalan Tan Tock Seng", "Jalan Tanah Puteh", "Jalan Tanah Rata", "Jalan Tani", "Jalan Tanjong", "Jalan Tapisan", "Jalan Tari Dulang", "Jalan Tari Lilin", "Jalan Tari Payong", "Jalan Tari Piring", "Jalan Tari Serimpi", "Jalan Tari Zapin", "Jalan Tarum", "Jalan Teban", "Jalan Teck Kee", "Jalan Teck Whye", "Jalan Tekad", "Jalan Tekukor", "Jalan Telang", "Jalan Telawi", "Jalan Telipok", "Jalan Teliti", "Jalan Tembusu", "Jalan Tempua", "Jalan Tenaga", "Jalan Tenang", "Jalan Tenggiri", "Jalan Tenon", "Jalan Tenteram", "Jalan Tepong", "Jalan Terang Bulan", "Jalan Terap", "Jalan Terentang", "Jalan Terubok", "Jalan Terusan", "Jalan Tiga Ratus", "Jalan Tiga", "Jalan Tiong", "Jalan Toa Payoh", "Jalan Todak", "Jalan Tua Kong", "Jalan Tukang", "Jalan Tumpu", "Jalan Tupai", "Jalan Turi", "Jalan Ubi", "Jalan Uji", "Jalan Ulu Seletar", "Jalan Ulu Sembawang", "Jalan Ulu Siglap", "Jalan Unggas", "Jalan Usaha", "Jalan Wajek", "Jalan Wak Selat", "Jalan Wakaff", "Jalan Wangi", "Jalan Waringin", "Jalan Woodbridge", "Jalan Yasin", "Jalan Zamrud", "Jambol Place", "Jambol Walk", "Jansen Close", "Jansen Road", "Japanese Garden Road", "Jasmine Road", "Java Road", "Jedburgh Gardens", "Jelapang Road", "Jelebu Road", "Jellicoe Road", "Jervois Close", "Jervois Hill", "Jervois Lane", "Jervois Road", "Jetty Road", "Jiak Chuan Road", "Jiak Kim Street", "Joan Road", "John Road", "Johore Road", "Joo Avenue", "Joo Chiat Avenue", "Joo Chiat Lane", "Joo Chiat Place", "Joo Chiat Road", "Joo Chiat Terrace", "Joo Chiat Walk", "Joo Hong Road", "Joo Koon Circle", "Joo Koon Crescent", "Joo Koon Road", "Joo Koon Way", "Joo Seng Road", "Joo Yee Road", "Joon Hiang Road", "Jubilee Road", "Jupiter Road", "Jurong East Avenue 1", "Jurong East Central", "Jurong East Street 11", "Jurong East Street 12", "Jurong East Street 13", "Jurong East Street 21", "Jurong East Street 24", "Jurong East Street 31", "Jurong East Street 32", "Jurong Hill", "Jurong Island Highway", "Jurong Pier Road", "Jurong Pier Way", "Jurong Port Road", "Jurong Road Track 18", "Jurong Road Track 20", "Jurong Road Track 22", "Jurong Road", "Jurong Town Hall Road", "Jurong West Avenue 1", "Jurong West Avenue 2", "Jurong West Avenue 4", "Jurong West Avenue 5", "Jurong West Central 1", "Jurong West Central 2", "Jurong West Central 3", "Jurong West Street 41", "Jurong West Street 42", "Jurong West Street 51", "Jurong West Street 52", "Jurong West Street 61", "Jurong West Street 62", "Jurong West Street 64", "Jurong West Street 71", "Jurong West Street 72", "Jurong West Street 73", "Jurong West Street 74", "Jurong West Street 75", "Jurong West Street 76", "Jurong West Street 81", "Jurong West Street 82", "Jurong West Street 91", "Jurong West Street 92", "Jurong West Street 93", "", "Kadayanallur Street", "Kaki Bukit Avenue 1", "Kaki Bukit Avenue 2", "Kaki Bukit Avenue 3", "Kaki Bukit Avenue 4", "Kaki Bukit Avenue 5", "Kaki Bukit Avenue 6", "Kaki Bukit Crescent", "Kaki Bukit Industrial Estate", "Kaki Bukit Industrial Terrace", "Kaki Bukit Place", "Kaki Bukit Road 1", "Kaki Bukit Road 2", "Kaki Bukit Road 3", "Kaki Bukit Road 4", "Kaki Bukit Road 5", "Kaki Bukit Road", "Kalidasa Avenue", "Kallang Avenue", "Kallang Bahru", "Kallang Junction", "Kallang Place", "Kallang Pudding Road", "Kallang Road", "Kallang Sector", "Kallang Tengah", "Kallang Walk", "Kallang Way 1", "Kallang Way 2", "Kallang Way 2A", "Kallang Way 3", "Kallang Way 4", "Kallang Way 5", "Kallang Way", "Kampong Ampat", "Kampong Arang Road", "Kampong Bahru Road", "Kampong Bugis", "Kampong Eunos", "Kampong Java Road", "Kampong Kapor Road", "Kampong Kayu Road", "Kampong Sireh", "Kampong Wak Hassan", "Kandahar Street", "Kang Ching Road", "Kang Choo Bin Road", "Kang Choo Bin Walk", "Kanisha Marican Road", "Karikal Lane", "Kasai Road", "Katmandu Road", "Kay Poh Road", "Kay Siang Road", "Kee Choe Avenue", "Kee Seng Street", "Kee Sun Avenue", "Kelantan Lane", "Kelantan Road", "Kellock Road", "Kelopak Road", "Kelulut Hill", "Kempas Road", "Kenanga Avenue", "Keng Cheow Street", "Keng Chin Road", "Keng Kiat Street", "Keng Lee Road", "Kensington Park Drive", "Kensington Park Road", "Kent Ridge Crescent", "Kent Ridge Drive", "Kent Ridge Road", "Kent Road", "Kenya Crescent", "Keong Saik Road", "Keppel Hill", "Keppel Road", "Keppel Terminal Avenue", "Keramat Road", "Kerbau Road", "Keris Drive", "Kew Avenue", "Kew Crescent", "Kew Drive", "Kew Heights", "Kew Lane", "Kew Terrace", "Kew Walk", "Khalsa Crescent", "Kheam Hock Road", "Khiang Guan Avenue", "Kian Teck Avenue", "Kian Teck Crescent", "Kian Teck Drive", "Kian Teck Road", "Kian Teck Way", "Killiney Road", "Kim Cheng Street", "Kim Chuan Avenue", "Kim Chuan Drive", "Kim Chuan Lane", "Kim Chuan Road", "Kim Chuan Terrace", "Kim Keat Avenue", "Kim Keat Close", "Kim Keat Lane", "Kim Keat Link", "Kim Keat Road", "Kim Pong Road", "Kim Seng Promenade", "Kim Seng Road", "Kim Seng Walk", "Kim Tian Place", "Kim Tian Road", "Kim Yam Road", "King's Avenue", "King's Close", "King's Drive", "King's Road", "King's Way", "King Albert Park", "King George's Avenue", "Kingsmead Road", "Kingswear Avenue", "Kinta Road", "Kirk Terrace", "Kismis Avenue", "Kismis Green", "Kismis Place", "Kismis Road", "Kitchener Road", "Klang Lane", "Klang Road", "Kloof Road", "Knights Bridge", "Koek Road", "Koh Sek Lim Road", "Koon Seng Road", "Kovan Close", "Kovan Road", "Kramat Lane", "Kramat Road", "Kranji Loop", "Kranji Road", "Kranji Way", "Kreta Ayer Road", "Kuala Loyang Road", "Kung Chong Road", "Kuo Chuan Avenue", "Kurau Grove", "Kurau Place", "Kurau Terrace", "Kwong Avenue", "Kwong Min Road", "", "La Salle Street", "Labrador Villa Road", "Lady Hill Road", "Lagos Circle", "Laguna Golf Green", "Lakepoint Drive", "Lakeshore View", "Lakme Street", "Lakme Terrace", "Lambeth Walk", "Lancaster Gate", "Lange Road", "Langsat Road", "Lantana Avenue", "Larkhill Road", "Larut Road", "Lasia Avenue", "Lavender Street", "Law Link", "Leedon Heights", "Leedon Park", "Leedon Road", "Leicester Road", "Leith Park", "Leith Road", "Lembu Road", "Lemon Avenue", "Lempeng Drive", "Leng Kee Road", "Lengkok Angsa", "Lengkok Bahru", "Lengkok Dua", "Lengkok Empat", "Lengkok Enam", "Lengkok Lima", "Lengkok Mariam", "Lengkok Merak", "Lengkok Saga", "Lengkok Satu", "Lengkok Tiga", "Lengkok Tujoh", "Lentor Avenue", "Lentor Crescent", "Lentor Drive", "Lentor Green", "Lentor Grove", "Lentor Lane", "Lentor Link", "Lentor Loop", "Lentor Place", "Lentor Road", "Lentor Street", "Lentor Terrace", "Lentor Vale", "Lentor Walk", "Lentor Way", "Leo Drive", "Leonie Hill Road", "Leonie Hill", "Lermit Road", "Leuchars Road", "Lew Lian Vale", "Lewin Terrace", "Lewis Road", "Leyden Hill", "Li Hwan Close", "Li Hwan Drive", "Li Hwan Place", "Li Hwan Terrace", "Li Hwan View", "Li Hwan Walk", "Li Po Avenue", "Liane Road", "Liang Seah Street", "Libra Drive", "Lichfield Road", "Lichi Avenue", "Lighthouse Beach Walk", "Lilac Drive", "Lilac Road", "Lilac Walk", "Lily Avenue", "Lim Ah Pin Road", "Lim Ah Woo Road", "Lim Chu Kang Lane 1", "Lim Chu Kang Lane 1A", "Lim Chu Kang Lane 2", "Lim Chu Kang Lane 3", "Lim Chu Kang Lane 3A", "Lim Chu Kang Lane 4", "Lim Chu Kang Lane 5", "Lim Chu Kang Lane 5A", "Lim Chu Kang Lane 6", "Lim Chu Kang Lane 6C", "Lim Chu Kang Lane 6D", "Lim Chu Kang Lane 6F", "Lim Chu Kang Lane 8", "Lim Chu Kang Lane 8A", "Lim Chu Kang Lane 9", "Lim Chu Kang Lane 9A", "Lim Chu Kang Road", "Lim Chu Kang Track 11", "Lim Chu Kang Track 13", "Lim Liak Street", "Lim Tai See Walk", "Lim Teck Boo Road", "Lim Teck Kim Road", "Lim Tua Tow Road", "Limau Garden", "Limau Grove", "Limau Rise", "Limau Terrace", "Limau Walk", "Lincoln Road", "Linden Drive", "Link Road", "Little India", "Little Road", "Liu Fang Road", "Lloyd Road", "Lock Road", "Loewen Road", "Lok Yang Way", "Loke Yew Street", "Lompang Road", "Lornie Road", "Lornie Walk", "Lorong 1 Geylang", "Lorong 1 Realty Park", "Lorong 1 Toa Payoh", "Lorong 10 Geylang", "Lorong 101 Changi", "Lorong 102 Changi", "Lorong 104 Changi", "Lorong 105 Changi", "Lorong 106 Changi", "Lorong 107 Changi", "Lorong 108 Changi", "Lorong 11 Geylang", "Lorong 110 Changi", "Lorong 12 Geylang", "Lorong 13 Geylang", "Lorong 14 Geylang", "Lorong 15 Geylang", "Lorong 16 Geylang", "Lorong 17 Geylang", "Lorong 18 Geylang", "Lorong 19 Geylang", "Lorong 1A Toa Payoh", "Lorong 2 Realty Park", "Lorong 2 Toa Payoh", "Lorong 20 Geylang", "Lorong 21 Geylang", "Lorong 21A Geylang", "Lorong 22 Geylang", "Lorong 23 Geylang", "Lorong 24 Geylang", "Lorong 24A Geylang", "Lorong 25 Geylang", "Lorong 25A Geylang", "Lorong 26 Geylang", "Lorong 27 Geylang", "Lorong 27A Geylang", "Lorong 28 Geylang", "Lorong 29 Geylang", "Lorong 3 Geylang", "Lorong 3 Realty Park", "Lorong 3 Toa Payoh", "Lorong 30 Geylang", "Lorong 31 Geylang", "Lorong 32 Geylang", "Lorong 33 Geylang", "Lorong 34 Geylang", "Lorong 35 Geylang", "Lorong 36 Geylang", "Lorong 37 Geylang", "Lorong 38 Geylang", "Lorong 39 Geylang", "Lorong 4 Geylang", "Lorong 4 Realty Park", "Lorong 4 Toa Payoh", "Lorong 40 Geylang", "Lorong 41 Geylang", "Lorong 42 Geylang", "Lorong 5 Geylang", "Lorong 5 Realty Park", "Lorong 5 Toa Payoh", "Lorong 6 Geylang", "Lorong 6 Realty Park", "Lorong 6 Toa Payoh", "Lorong 7 Geylang", "Lorong 7 Realty Park", "Lorong 7 Toa Payoh", "Lorong 8 Geylang", "Lorong 8 Toa Payoh", "Lorong 8A Toa Payoh", "Lorong 9 Geylang", "Lorong Abu Talib", "Lorong Ah Soo", "Lorong Ah Thia", "Lorong Ampas", "Lorong Asrama", "Lorong Ayam Hutan", "Lorong Ayam Katek", "Lorong Bachok", "Lorong Bakar Batu", "Lorong Baling", "Lorong Bandang", "Lorong Batawi", "Lorong Bekukong", "Lorong Bengkok", "Lorong Biawak", "Lorong Buang Kok", "Lorong Bunga", "Lorong Chamar", "Lorong Chencharu", "Lorong Chengai", "Lorong Chuan", "Lorong Chuntum", "Lorong Danau", "Lorong Dangai", "Lorong G Telok Kurau", "Lorong Gambas", "Lorong Gambir", "Lorong Gimbul", "Lorong H Telok Kurau", "Lorong Halus", "Lorong Halwa", "Lorong Handalan", "Lorong How Sun", "Lorong J Telok Kurau", "Lorong K Telok Kurau", "Lorong Kabong", "Lorong Kebasi", "Lorong Kembagan", "Lorong Kembang", "Lorong Kemunchup", "Lorong Kerepek", "Lorong Kesudian", "Lorong Kesum", "Lorong Kilat", "Lorong Kismis", "Lorong L Telok Kurau", "Lorong Lada Hitam", "Lorong Lada Merah", "Lorong Lada Pati", "Lorong Lew Lian", "Lorong Limau", "Lorong Liput", "Lorong Low Koon", "Lorong M Telok Kurau", "Lorong Malai", "Lorong Mambong", "Lorong Marican", "Lorong Marzuki", "Lorong Mega", "Lorong Melayu", "Lorong Melukut", "Lorong Mydin", "Lorong N Telok Kurau", "Lorong Nangka", "Lorong Napiri", "Lorong Ong Lye", "Lorong Panchar", "Lorong Pasu", "Lorong Paya Lebar", "Lorong Payah", "Lorong Penchalak", "Lorong Pendek", "Lorong Pisang Asam", "Lorong Pisang Batu", "Lorong Pisang Emas", "Lorong Pisang Hijau", "Lorong Pisang Raja", "Lorong Pisang Udang", "Lorong Puntong", "Lorong Rusuk", "Lorong Sahad", "Lorong Salleh", "Lorong Samak", "Lorong Sari", "Lorong Sarina", "Lorong Selangat", "Lorong Semangka", "Lorong Serambi", "Lorong Sesuai", "Lorong Siglap", "Lorong Sinaran", "Lorong Sireh Pinang", "Lorong Stangee", "Lorong Tahar", "Lorong Tai Seng", "Lorong Tanggam", "Lorong Tawas", "Lorong Telok", "Lorong Temechut", "Lorong Terigu", "Lorong Tongkol", "Lorong Tukang Dua", "Lorong Tukang Empat", "Lorong Tukang Lima", "Lorong Tukang Satu", "Lorong Tukang Tiga", "Lorong Tukol", "Lothian Terrace", "Lotus Avenue", "Lower Delta Road", "Lower Kent Ridge Road", "Lower Ring Road", "Lowland Road", "Loyang Avenue", "Loyang Besar Close", "Loyang Crescent", "Loyang Drive", "Loyang Lane", "Loyang Link", "Loyang Place", "Loyang Rise", "Loyang Street", "Loyang View", "Loyang Walk", "Loyang Way 1", "Loyang Way 2", "Loyang Way 4", "Loyang Way 6", "Loyang Way", "Lucky Crescent", "Lucky Gardens", "Lucky Heights", "Lucky Rise", "Lucky View", "Ludlow Place", "Lutheran Road", "Lyndhurst Road", "Lynwood Grove", "", "MacAlister Road", "MacKenzie Road", "MacKerrow Road", "MacPherson Lane", "MacPherson Road", "MacTaggart Road", "Madras Street", "Magazine Road", "Maida Vale", "Maidstone Road", "Main Gate Road", "Maju Avenue", "Maju Drive", "Makepeace Road", "Makeway Avenue", "Malabar Street", "Malacca Street", "Malan Road", "Malan Street", "Malcolm Park", "Malcolm Road", "Malta Crescent", "Mandai Avenue", "Mandai Estate", "Mandai Lake Road Track 15", "Mandai Lake Road Track 9", "Mandai Lake Road", "Mandai Road Track 16", "Mandai Road Track 3", "Mandai Road Track 7", "Mandai Road", "Mandalay Road", "Mangis Road", "Manila Street", "Manston Road", "Maple Avenue", "Maple Lane", "Mar Thoma Road", "Marang Road", "Maranta Avenue", "Margaret Drive", "Margate Road", "Margoliouth Road", "Maria Avenue", "Mariam Close", "Mariam Walk", "Mariam Way", "Marigold Drive", "Marina Bay Promenade", "Marina Boulevard", "Marina Coastal Drive", "Marina Green", "Marina Grove", "Marina Mall", "Marina Park", "Marina Place", "Marina Station Road", "Marina Street", "Marina Way", "Marine Crescent", "Marine Drive", "Marine Parade Central", "Marine Parade Road", "Marine Terrace", "Marine Vista", "Maritime Avenue", "Maritime Square", "Market Street", "Marlene Avenue", "Marne Road", "Marshall Lane", "Marshall Road", "Marsiling Crescent", "Marsiling Drive", "Marsiling Industrial Estate Road 1", "Marsiling Industrial Estate Road 10", "Marsiling Industrial Estate Road 2", "Marsiling Industrial Estate Road 3", "Marsiling Industrial Estate Road 4", "Marsiling Industrial Estate Road 5", "Marsiling Industrial Estate Road 6", "Marsiling Industrial Estate Road 7", "Marsiling Industrial Estate Road 8", "Marsiling Industrial Estate Road 9", "Marsiling Lane", "Marsiling Rise", "Marsiling Road", "Martaban Road", "Martia Road", "Martin Road", "Martlesham Road", "Maryland Drive", "Marymount Lane", "Marymount Road", "Marymount Terrace", "Mata Road", "Matlock Rise", "Mattar Road", "Maude Road", "Maxwell Link", "Maxwell Road", "May Road", "Mayfield Avenue", "Mayflower Avenue", "Mayflower Crescent", "Mayflower Drive", "Mayflower Lane", "Mayflower Place", "Mayflower Rise", "Mayflower Road", "Mayflower Terrace", "Mayflower Way", "Mayne Road", "Mayo Street", "McCallum Street", "McNair Road", "Medical Drive", "Medway Drive", "Mei Chin Road", "Mei Hwan Crescent", "Mei Hwan Drive", "Mei Hwan Road", "Mei Hwan View", "Melrose Drive", "Meng Suan Road", "Meragi Close", "Meragi Road", "Meragi Terrace", "Merbau Road", "Merbok Crescent", "Merchant Road", "Mergui Road", "Merino Crescent", "Merpati Road", "Merryn Avenue", "Merryn Close", "Merryn Drive", "Merryn Road", "Merryn Terrace", "Metropole Drive", "Meyappa Chettiar Road", "Meyer Place", "Meyer Road", "Middle Road", "Middlesex Road", "Miller Street", "Mimosa Crescent", "Mimosa Drive", "Mimosa Place", "Mimosa Road", "Mimosa Terrace", "Mimosa Vale", "Mimosa View", "Mimosa Walk", "Minbu Road", "Minden Road", "Ming Teck Park", "Minto Road", "Mistri Road", "Moh Guan Terrace", "Mohamad Ali Lane", "Mohamad Sultan Road", "Monk's Hill Road", "Monk's Hill Terrace", "Montreal Drive", "Montreal Road", "Moon Crescent", "Moonbeam Drive", "Moonbeam Terrace", "Moonbeam View", "Moonbeam Walk", "Moonstone Lane", "Moreton Close", "Morington Crescent", "Morley Road", "Morse Road", "Mosque Street", "Moulmein Rise", "Moulmein Road", "Mount Echo Park", "Mount Elizabeth Link", "Mount Elizabeth", "Mount Emily Road", "Mount Faber Loop", "Mount Faber Road", "Mount Pleasant Drive", "Mount Pleasant Road", "Mount Rosie Road", "Mount Rosie Terrace", "Mount Sinai Avenue", "Mount Sinai Crescent", "Mount Sinai Drive", "Mount Sinai Lane", "Mount Sinai Plain", "Mount Sinai Rise", "Mount Sinai Road", "Mount Sinai View", "Mount Sinai Walk", "Mount Sophia", "Mount Vernon Road", "Mountbatten Road", "Mugliston Gardens", "Mugliston Park", "Mugliston Road", "Mugliston walk", "Mulberry Avenue", "Munshi Abdullah Avenue", "Munshi Abdullah Walk", "Murai Farmway", "Murray Street", "Murray Terrace", "Muscat Street", "Muswell Hill", "Muthuraman Chetty Road", "", "Nallur Road", "Namly Avenue", "Namly Close", "Namly Crescent", "Namly Drive", "Namly Garden", "Namly Grove", "Namly Hill", "Namly Place", "Namly Rise", "Namly View", "Nankin Street", "Nanson Drive", "Nanson Road", "Nanyang Avenue", "Nanyang Circle", "Nanyang Crescent", "Nanyang Drive", "Nanyang Green", "Nanyang Hill", "Nanyang Link", "Nanyang Terrace", "Nanyang Valley", "Nanyang View", "Nanyang Walk", "Napier Road", "Narayanan Chetty Road", "Narooma Road", "Nassim Hill", "Nassim Road", "Nathan Road", "Nee Soon Road", "Neil Road", "Nemesu Avenue", "Neo Pee Teck Lane", "Neo Tiew Crescent", "Neo Tiew Lane 1", "Neo Tiew Lane 2", "Neo Tiew Lane", "Neo Tiew Road", "Neram Crescent", "Neram Road", "Netheravon Road", "New Bridge Road", "New Bugis Street", "New Industrial Road", "New Loyang Link", "New Market Road", "New Upper Changi Road", "Newton Circus", "Newton Road", "Neythal Road", "Nicoll Drive", "Nicoll Highway", "Nile Road", "Nim Crescent", "Nim Drive", "Nim Green", "Nim Road", "Niven Road", "Noordin Lane", "Norfolk Road", "Norma Terrace", "Normanton Park", "Norris Road", "North Boat Quay", "North Bridge Road", "North Buona Vista Road", "North Canal Road", "North Perimeter Road", "North Road", "North Woodlands Drive", "North Woodlands Link", "North Woodlands Way", "Northolt Road", "Northumberland Road", "Northwood Drive", "Northwood Lane", "Northwood Walk", "Novena Rise", "Novena Terrace", "Nutmeg Road", "", "Oak Avenue", "Oakwood Grove", "Ocean Drive", "Ocean Way", "Oei Tiong Ham Park", "Office Gate Road", "Office Ring Road", "Office Road", "Old Airport Road", "Old Birdcage Walk", "Old Jurong Road", "Old Lim Chu Kang Road", "Old Middle Road", "Old Nelson Road", "Old Pier Road", "Old Sarum Road", "Old Tampines Road", "Old Toh Tuck Road", "Old Upper Thomson Road", "Old Yio Chu Kang Road", "Oldham Lane", "Olive Road", "Omar Khayyam Avenue", "Onan Road", "One Tree Hill", "Onraet Road", "Ontario Avenue", "Opal Crescent", "Ophir Road", "Orange Grove Road", "Orchard Boulevard", "Orchard Link", "Orchard Road", "Orchard Spring Lane", "Orchard Turn", "Orchid Club Road", "Orchid Drive", "Ord Road", "Oriole Crescent", "Ottawa Road", "Outram Hill", "Outram Park", "Outram Road", "Owen Road", "Oxford Road", "Oxford Street", "Oxley Garden", "Oxley Rise", "Oxley Road", "Oxley Walk", "", "Padang Chancery", "Padang Jeringau", "Pagoda Street", "Pahang Street", "Pakistan Road", "Palm Avenue", "Palm Drive", "Palm Grove Avenue", "Palm Road", "Palm Valley Road", "Palmer Road", "Pandan Avenue", "Pandan Crescent", "Pandan Gardens", "Pandan Loop", "Pandan Road", "Pandan Valley", "Pang Seng Road", "Paradise Island", "Paranakan Place", "Parbury Avenue", "Park Crescent", "Park Lane", "Park Road", "Park Vale", "Park Villas Green", "Park Villas Rise", "Park Villas Terrace", "Parkstone Road", "Parliament Lane", "Parry Avenue", "Parry Road", "Parry Terrace", "Parry View", "Parry Walk", "Parsi Road", "Pasar Lane", "Pasir Laba Road", "Pasir Panjang Close", "Pasir Panjang Drive", "Pasir Panjang Hill", "Pasir Panjang RoadView", "Pasir Panjang Terminal Avenue 1", "Pasir Panjang Terminal Avenue 2", "Pasir Panjang Terminal Avenue 3", "Pasir Panjang Terminal Drive 1", "Pasir Panjang Terminal Drive 10", "Pasir Panjang Terminal Drive 11", "Pasir Panjang Terminal Drive 12", "Pasir Panjang Terminal Drive 14", "Pasir Panjang Terminal Drive 2", "Pasir Panjang Terminal Drive 3", "Pasir Panjang Terminal Drive 4", "Pasir Panjang Terminal Drive 5", "Pasir Panjang Terminal Drive 6", "Pasir Panjang Terminal Drive 7", "Pasir Panjang Terminal Drive 8", "Pasir Panjang Terminal Drive 9", "Pasir Panjang Terminal Road A", "Pasir Panjang Terminal Road B", "Pasir Panjang Terminal Road C", "Pasir Panjang Terminal Road D", "Pasir Panjang Terminal Road E", "Pasir Panjang Terminal Road F", "Pasir Panjang Terminal Road G", "Pasir Panjang Terminal Road H", "Pasir Panjang Terminal Road J", "Pasir Panjang Terminal Road K", "Pasir Panjang Terminal Road L", "Pasir Ris Avenue", "Pasir Ris Central", "Pasir Ris Close", "Pasir Ris Coast Ind Park 1", "Pasir Ris Coast Ind Park 2", "Pasir Ris Coast Ind Park 3", "Pasir Ris Coast Ind Park 4", "Pasir Ris Coast Ind Park 5", "Pasir Ris Coast Ind Park 6", "Pasir Ris Drive 1", "Pasir Ris Drive 10", "Pasir Ris Drive 12", "Pasir Ris Drive 2", "Pasir Ris Drive 3", "Pasir Ris Drive 4", "Pasir Ris Drive 6", "Pasir Ris Drive 8", "Pasir Ris Farmway 1", "Pasir Ris Farmway 2", "Pasir Ris Green", "Pasir Ris Heights", "Pasir Ris Lane", "Pasir Ris Road", "Pasir Ris Street 1", "Pasir Ris Street 11", "Pasir Ris Street 12", "Pasir Ris Street 13", "Pasir Ris Street 21", "Pasir Ris Street 4", "Pasir Ris Street 51", "Pasir Ris Street 52", "Pasir Ris Street 71", "Pasir Ris Street 72", "Pasir Ris Terrace", "Pasir Ris View", "Pasir Ris Way", "Paterson Hill", "Paterson Road", "Paya Lebar Close", "Paya Lebar Crescent", "Paya Lebar Place", "Paya Lebar Rise", "Paya Lebar Road", "Paya Lebar Street", "Paya Lebar Walk", "Paya Lebar Way", "Peach Garden", "Peakville Avenue", "Peakville Grove", "Peakville Terrace", "Peakville Walk", "Pearl's Hill Road", "Pearl's Hill Terrace", "Pearl Bank", "Pearl Island", "Pebble Lane", "Peck Hay Road", "Peck Seah Street", "Peel Road", "Pegu Road", "Pei Wah Avenue", "Peirce Drive", "Peirce Hill", "Peirce Road", "Pekin Street", "Pemimpin Drive", "Pemimpin Place", "Pemimpin Terrace", "Penang Lane", "Penang Road", "Pender Road", "Pending Road", "Peng Nguan Street", "Penhas Road", "Penjuru Close", "Penjuru Lane", "Penjuru Road", "Pennefather Road", "Penshurst Place", "Pepys Road", "Perak Road", "Percival Road", "Pereira Road", "Perumal Road", "Pesari Walk", "Petain Road", "Petir Road", "Pheng Geck Avenue", "Philip Street", "Phillips Avenue", "Phoenix Avenue", "Phoenix Garden", "Phoenix Park", "Phoenix Rise", "Phoenix Road", "Phoenix Walk", "Piccadilly Circus", "Piccadilly", "Pickering Street", "Pillai Road", "Pine Close", "Pine Grove", "Pine Lane", "Pine Walk", "Pinewood Grove", "Pioneer Crescent", "Pioneer Lane", "Pioneer Road North", "Pioneer Road", "Pioneer Sector 1", "Pioneer Sector 2", "Pioneer Sector 3", "Pioneer Sector Lane", "Pioneer Sector Walk", "Pipit Road", "Pitt Street", "Plantation Avenue", "Platina Road", "Playfair Road", "Plumer Road", "Plymouth Avenue", "Poh Huat Crescent", "Poh Huat Drive", "Poh Huat Road", "Poh Huat Terrace", "Ponggol Seventeenth Avenue", "Ponggol Twenty Fourth Avenue", "Poole Road", "Port Road", "Portchester Avenue", "Portsdown Road", "Potong Pasir Avenue 1", "Potong Pasir Avenue 2", "Potong Pasir Avenue 3", "Preston Road", "Primrose Avenue", "Prince Charles Crescent", "Prince Charles Square", "Prince Edward Link", "Prince Edward Road", "Prince George's Park", "Prince of Wales Road", "Prince Philip Avenue", "Prince Road", "Prinsep Court", "Prinsep Link", "Prinsep Street", "Prome Road", "Puay Hee Avenue", "Pulasan Road", "Punggol Port Road", "Punggol Road Track 13", "Punggol Road Track 19", "Punggol Road Track 24", "Punggol Road", "Purvis Street", "", "Quality Road", "Queen's Avenue", "Queen's Close", "Queen's Crescent", "Queen's Roa", "Queen Astrid Gardens", "Queen Astrid Park", "Queen Elizabeth Walk", "Queen Street", "Queensway", "Quemoy Road", "", "Race Course Lane", "Race Course Road", "Raeburn Park", "Raffles Avenue", "Raffles Boulevard", "Raffles Institution Lane", "Raffles Link", "Raffles Place", "Raffles Quay", "Raglan Grove", "Rambai Road", "Rambau Street", "Rambutan Road", "Ramsgate Road", "Rangoon Lane", "Rangoon Road", "Ratus Road", "Read Crescent", "Read Street", "Rebecca Road", "Recreation Lane", "Recreation Road", "Redhill Close", "Redhill Lane", "Redhill Road", "Redwood Avenue", "Redwood Grove", "Refinery Road", "Regent Street", "Republic Avenue", "Republic Boulevard", "Reservoir Road", "Rhu Cross", "Richards Avenue", "Richards Place", "Ridgewood Close", "Ridley Park", "Ridout Road", "Rienzi Street", "Rifle Range Road", "Ring Road", "Ringwood Road", "Ripley Crescent", "River Valley Close", "River Valley Grove", "River Valley Road", "Riverina Crescent", "Riverina View", "Riverina Walk", "Riverside Crescent", "Riverside Drive", "Riverside Lane", "Riverside Link", "Riverside Road", "Riverside Street", "Riverside Walk", "Riviera Drive", "Roberts Lane", "Robertson Quay", "Robey Crescent", "Robin Close", "Robin Drive", "Robin Lane", "Robin Road", "Robin Walk", "Robinson Road", "Rochalie Drive", "Rochdale Road", "Rochester Park", "Rochor Canal Road", "Rochor Road", "Rodyk Street", "Rose Lane", "Roseburn Avenue", "Rosewood Close", "Rosewood Drive", "Rosewood Grove", "Ross Avenue", "Rosyth Avenue", "Rosyth Road", "Rosyth Terrace", "Rotan Lane", "Rowell Road", "Royal Road", "Ruby Lane", "Russels Road", "Rutland Road", "", "Sago Street", "Saiboo Street", "Sakra Avenue", "Sakra Road", "Salam Walk", "Sallim Road", "Sam Leong Road", "Sampan Place", "Sandilands Road", "Sandown Place", "Sandwich Road", "Sandy Island", "Sandy Lane", "Saraca Drive", "Saraca Hill", "Saraca Place", "Saraca Road", "Saraca View", "Saraca Walk", "Sarkies Road", "Saujana Road", "Saunders Road", "Science Centre Road", "Science Park Drive", "Science Park Road", "Scotts Road", "Sea Avenue", "Sea Breeze Avenue", "Sea Breeze Grove", "Sea Breeze Road", "Sea Breeze Walk", "Seagull Walk", "Seah Im Road", "Seah Street", "Sealand Road", "Second Avenue", "Second Chin Bee Road", "Second Hospital Avenue", "Second Lok Yang Road", "Second Street", "Selarang Park Road", "Selarang Ring Road", "Selarang Way", "Selegie Road", "Seletar Close", "Seletar Club Road", "Seletar Court", "Seletar Crescent", "Seletar East Farmway 1", "Seletar East Farmway 2", "Seletar East Farmway 3", "Seletar East Farmway 4", "Seletar East Farmway 5", "Seletar Hills Drive", "Seletar Road", "Seletar Satellite Station Road", "Seletar Terrace", "Seletar West Farmway 1", "Seletar West Farmway 10", "Seletar West Farmway 2", "Seletar West Farmway 3", "Seletar West Farmway 4", "Seletar West Farmway 5", "Seletar West Farmway 6", "Seletar West Farmway 7", "Seletar West Farmway 8", "Seletar West Farmway 9", "Sembawang Avenue", "Sembawang Close", "Sembawang Crescent", "Sembawang Drive", "Sembawang Hills Drive", "Sembawang Lane", "Sembawang Link", "Sembawang Place", "Sembawang Road", "Sembawang Street", "Sembawang Terminal Avenue 1", "Sembawang Terminal Avenue 2", "Sembawang Terminal Avenue 3", "Sembawang Terminal Road A", "Sembawang Terminal Road B", "Sembawang Terminal Road C", "Sembawang Terminal Road D", "Sembawang Terminal Road E", "Sembawang Terminal Road F", "Sembawang Terminal Road G", "Sembawang Terminal Road H", "Sembawang Vista", "Sembawang Walk", "Sembawang Way", "Sembong Road", "Senang Crescent", "Seng Poh Lane", "Seng Poh Road", "Sengkang Central", "Sengkang East Avenue 6", "Sengkang East Avenue", "Sengkang East Drive", "Sengkang East Road", "Sengkang East Way", "Senja Link", "Senja Road", "Sennett Avenue", "Sennett Close", "Sennett Drive", "Sennett Lane", "Sennett Place", "Sennett Road", "Sennett Terrace", "Senoko Avenue", "Senoko Crescent", "Senoko Drive", "Senoko Link", "Senoko Loop", "Senoko Road", "Senoko South Road", "Senoko Way", "Sentosa East Mall", "Serangoon Avenue 1", "Serangoon Avenue 2", "Serangoon Avenue 3", "Serangoon Avenue 4", "Serangoon Central Drive", "Serangoon Central", "Serangoon Garden Circus", "Serangoon Garden Way", "Serangoon Lane", "Serangoon North Avenue 1", "Serangoon North Avenue 2", "Serangoon North Avenue 3", "Serangoon North Avenue 4", "Serangoon North Avenue 5", "Serangoon North Avenue 6", "Serangoon Road", "Serangoon Terrace", "Serapong Course Road", "Serapong Hill Road", "Seraya Avenue", "Seraya Crescent", "Seraya Lane", "Seraya Road", "Serenade Walk", "Seton Close", "Shamah Terrace", "Shan Road", "Shanghai Road", "Shangri La Close", "Shangri La Walk", "Shaw Road", "Shelford Road", "Shenton Way", "Shepherd's Drive", "Sherwood Road", "Shipyard Road", "Short Street", "Shrewsbury Road", "Shunfu Road", "Siak Kew Avenue", "Sian Tuan Avenue", "Siang Kuang Avenue", "Siang Lim Park", "Siglap Avenue South", "Siglap Avenue", "Siglap Bank", "Siglap Close", "Siglap Drive", "Siglap Gardens", "Siglap Hill", "Siglap Link", "Siglap Plain", "Siglap Rise", "Siglap Road", "Siglap Terrace", "Siglap Valley", "Siglap View", "Siglap Walk", "Silat Avenue", "Silat Road", "Silat Walk", "Silosa Beach", "Siloso Road", "Sime Park Hill", "Sime Road", "Simei Avenue", "Simei Road", "Simei Street 1", "Simei Street 2", "Simei Street 3", "Simei Street 4", "Simei Street 5", "Simei Street 6", "Simon Lane", "Simon Place", "Simon Road", "Simon Walk", "Sims Avenue East", "Sims Avenue", "Sims Drive", "Sims Lane", "Sims Place", "Sims View", "Sin Ming Avenue", "Sin Ming Drive", "Sin Ming Industrial Estate Sector A", "Sin Ming Industrial Estate Sector B", "Sin Ming Industrial Estate Sector C", "Sin Ming Industrial Estate Sector D", "Sin Ming Road", "Sin Ming Walk", "Sing Avenue", "Sing Joo Walk", "Siok Wan Close", "Sirat Place", "Sirat Road", "Sit Wah Road", "Sixth Avenue", "Sixth Crescent", "Sixth Lok Yang Road", "Smith Street", "Solomon Street", "Somerset Road", "Somme Road", "Sommerville Estate Road", "Sommerville Estate", "Sommerville Road", "Sommerville Walk", "Soo Chow Drive", "Soo Chow Garden Road", "Soo Chow Rise", "Soo Chow View", "Soo Chow Walk", "Soo Chow Way", "Soon Lee Road", "Soon Wing Road", "Sophia Road", "South Bridge Road", "South Buona Vista Road", "South Canal Road", "South Perimeter Road", "South Woodlands Drive", "Spooner Road", "Sports Drive 1", "Sports Drive 2", "Spottiswoode Park Road", "Spring Street", "Springleaf Avenue", "Springleaf Crescent", "Springleaf Drive", "Springleaf Garden", "Springleaf Height", "Springleaf Lane", "Springleaf Link", "Springleaf Rise", "Springleaf Road", "Springleaf View", "Springleaf Walk", "Springside Avenue", "Springside Drive", "Springside Green", "Springside Link", "Springside Walk", "Springwood Avenue", "Springwood Close", "Springwood Crescent", "Springwood Height", "Springwood Terrace", "Springwood Walk", "St. John's Crescent", "St. John's Road", "St. Margaret's Road", "St. Martin's Drive", "St. Martin's Lane", "St. Michael's Road", "St. Nicholas View", "St. Patrick's Road", "St. Thomas Walk", "St. Wilfred Road", "St. Anne's Wood", "St. Xavier's Lane", "St. Barnabas Lane", "St. Francis Road", "St. George's Lane", "St. George's Road", "St. Gregory's Place", "St. Helena Road", "St. Helier's Avenue", "St. Andrew's Road", "Stadium Boulevard", "Stadium Crescent", "Stadium Drive", "Stadium Lane", "Stadium Link", "Stadium Road", "Stadium Walk", "Stagmont Ring", "Stagmont Road", "Stamford Road", "Stangee Close", "Stangee Place", "Stanley Street", "Starlight Road", "Starlight Terrace", "Stephen Lee Road", "Stevens Close", "Stevens Drive", "Stevens Road", "Still Lane", "Still Road South", "Still Road", "Stirling Road", "Stockport Road", "Stokesay Drive", "Stone Avenue", "Stores Road", "Strathmore Avenue", "Stratton Drive", "Stratton Place", "Stratton Road", "Stratton Walk", "Students Walk", "Sturdee Road", "Sudan Road", "Suffolk Road", "Sultan Gate", "Sum Wah Chee Drive", "Summer Place", "Sunbird Avenue", "Sunbird Circle", "Sunbird Road", "Sundrige Park Road", "Sungei Gedong Road", "Sungei Kadut Avenue", "Sungei Kadut Crescent", "Sungei Kadut Drive", "Sungei Kadut Loop", "Sungei Kadut Street 1", "Sungei Kadut Street 2", "Sungei Kadut Street 3", "Sungei Kadut Street 4", "Sungei Kadut Street 5", "Sungei Kadut Street 6", "Sungei Kadut Way", "Sungei Road", "Sungei Tengah Road", "Sunrise Avenue", "Sunrise Close", "Sunrise Drive", "Sunrise Lane", "Sunrise Place", "Sunrise Terrace", "Sunrise Villa Estate", "Sunrise Way", "Sunset Avenue", "Sunset Close", "Sunset Crescent", "Sunset Drive", "Sunset Grove", "Sunset Heights", "Sunset Lane", "Sunset Place", "Sunset Square", "Sunset Terrace", "Sunset Vale", "Sunset View", "Sunset Walk", "Sunset Way", "Sunshine Terrace", "Surin Avenue", "Surin Lane", "Surin Road", "Surrey Road", "Sussex Garden", "Swallow Street", "Swan Lake Avenue", "Swanage Road", "Swettenham Close", "Swettenham Road", "Swiss Club Avenue", "Swiss Club Lane", "Swiss Club Link", "Swiss Club Road", "Swiss Cottage Estate", "Swiss View", "Syed Alwi Road", "Synagogue Street", "", "Tagore Avenue", "Tagore Drive", "Tagore Industrial Avenue", "Tagore Industrial Estate", "Tagore Lane", "Tagore Road", "Tah Ching Road", "Tai Gin Road", "Tai Hwan Avenue", "Tai Hwan Close", "Tai Hwan Crescent", "Tai Hwan Drive", "Tai Hwan Grove", "Tai Hwan Heights", "Tai Hwan Lane", "Tai Hwan Place", "Tai Hwan Terrace", "Tai Hwan Walk", "Tai Keng Avenue", "Tai Keng Gardens", "Tai Keng Lane", "Tai Keng Place", "Tai Keng Terrace", "Tai Seng Avenue", "Tai Seng Drive", "Tai Seng Industrial Estate", "Tai Thong Crescent", "Tai Yuan Heights", "Talma Road", "Taman Bedok", "Taman Ho Swee", "Taman Kembangan", "Taman Mas Merah", "Taman Nakhoda", "Taman Permata", "Taman Selamat", "Taman Siglap", "Taman Sireh", "Taman Warna", "Tamarind Avenue", "Tamarind Place", "Tamarind Road", "Tampines Avenue 1", "Tampines Avenue 10", "Tampines Avenue 12", "Tampines Avenue 2", "Tampines Avenue 3", "Tampines Avenue 4", "Tampines Avenue 5", "Tampines Avenue 6", "Tampines Avenue 7", "Tampines Avenue 8", "Tampines Avenue 9", "Tampines Central 1", "Tampines Central 2", "Tampines Central 3", "Tampines Central 4", "Tampines Central 5", "Tampines Central 6", "Tampines Ind Avenue 1", "Tampines Ind Avenue 2", "Tampines Ind Avenue 3", "Tampines Ind Avenue 4", "Tampines Ind Avenue 5", "Tampines Ind Crescent", "Tampines Street 11", "Tampines Street 12", "Tampines Street 21", "Tampines Street 22", "Tampines Street 23", "Tampines Street 24", "Tampines Street 31", "Tampines Street 32", "Tampines Street 33", "Tampines Street 34", "Tampines Street 41", "Tampines Street 42", "Tampines Street 43", "Tampines Street 44", "Tampines Street 45", "Tampines Street 71", "Tampines Street 72", "Tampines Street 73", "Tampines Street 81", "Tampines Street 82", "Tampines Street 83", "Tampines Street 84", "Tampines Street 9", "Tampines Street 91", "Tampines Street 92", "Tampines Street 93", "Tan Boon Chong Avenue", "Tan Kim Cheng Road", "Tan Quee Lan Street", "Tan Sim Boh Road", "Tan Tye Place", "Tanah Merah Besar Lane", "Tanah Merah Besar Road", "Tanah Merah Ferry Road", "Tanah Merah Kechil Avenue", "Tanah Merah Kechil Link", "Tanah Merah Kechil Rise", "Tanah Merah Kechil Road South", "Tanah Merah Kechil Road", "Tanglin Halt Close", "Tanglin Halt Road", "Tanglin Hill", "Tanglin Rise", "Tanglin Road", "Tanglin Village", "Tanglin Walk", "Tangmere Road", "Tanjong Beach Walk", "Tanjong Katong Road South", "Tanjong Katong Road", "Tanjong Kling Road", "Tanjong Pagar Plaza", "Tanjong Pagar Road", "Tanjong Pagar Terminal Avenue", "Tanjong Pagar Terminal Road A", "Tanjong Pagar Terminal Road B", "Tanjong Pagar Terminal Road C", "Tanjong Pagar Terminal Road D", "Tanjong Pagar Terminal Road E", "Tanjong Pagar Terminal Road F", "Tanjong Pagar Terminal Road G", "Tanjong Pagar Terminal Road H", "Tanjong Pagar Terminal Road J", "Tanjong Pagar Terminal Road K", "Tanjong Pagar Terminal Street 1", "Tanjong Pagar Terminal Street 2", "Tanjong Pagar Terminal Street 3", "Tanjong Pagar Terminal Street 4", "Tanjong Pagar Terminal Street 5", "Tanjong Penjuru Crescent", "Tanjong Penjuru", "Tanjong Rhu Lane", "Tanjong Rhu Place", "Tanjong Rhu Road", "Tanjong Rhu View", "Tank Road", "Tannery Lane", "Tannery Road", "Tao Ching Road", "Tavistock Avenue", "Tay Lian Teck Drive", "Tay Lian Teck Road", "Teachers' Housing Estate", "Teban Gardens Crescent", "Teban Gardens Road", "Tech Park Crescent", "Technology Crescent", "Teck Chye Terrace", "Teck Guan Street", "Teck Lim Road", "Teck Whye Avenue", "Teck Whye Crescent", "Teck Whye Lane", "Telegraph Street", "Telok Ayer Street", "Telok Blangah Crescent", "Telok Blangah Drive", "Telok Blangah Green", "Telok Blangah Heights", "Telok Blangah Industrial Estate", "Telok Blangah Rise", "Telok Blangah Road", "Telok Blangah Street 31", "Telok Blangah Street 32", "Telok Blangah Way", "Telok Kurau Road", "Telok Paku Road", "Temasek Avenue", "Temasek Boulevard", "Tembeling Road", "Temenggong Road", "Temple Hill Road", "Temple Street", "Teng Tong Road", "Tengah Avenue", "Teo Hong Road", "Teo Kim Eng Road", "Teow Hock Avenue", "Terang Bulan Avenue", "Tessensohn Road", "Tew Chew Road", "Tham Soong Avenue", "The Inglewood", "The Oval", "Thiam Siew Avenue", "Third Avenue", "Third Chin Bee Road", "Third Hospital Avenue", "Third Lok Yang Road", "Third Street", "Thomson Close", "Thomson Garden Estate", "Thomson Green", "Thomson Heights", "Thomson Hill Estate", "Thomson Hill", "Thomson Hills Drive", "Thomson Ridge Estate", "Thomson Ridge", "Thomson Rise Estate", "Thomson Road", "Thomson Terrace", "Thomson View", "Thomson Walk", "Thong Bee Road", "Thong Soon Avenue", "Thong Soon Green", "Thong Soon Road", "Thrift Drive", "Tiong Bahru Industrial Estate", "Tiong Bahru Road", "Tiong Poh Avenue", "Tiong Poh Road", "Tiverton Lane", "Toa Payoh Central", "Toa Payoh Ease", "Toa Payoh Industrial Estate", "Toa Payoh Industrial Park", "Toa Payoh North", "Toa Payoh Rise", "Toa Payoh West Industiral Estate", "Toa Payoh West", "Toh Avenue", "Toh Close", "Toh Crescent", "Toh Drive", "Toh Guan Road", "Toh Heights", "Toh Street", "Toh Tuck Avenue", "Toh Tuck Close", "Toh Tuck Crescent", "Toh Tuck Drive", "Toh Tuck Hill Estate", "Toh Tuck Industrial Estate", "Toh Tuck Link", "Toh Tuck Place", "Toh Tuck Rise", "Toh Tuck Road", "Toh Tuck Terrace", "Toh Tuck Walk", "Toh Yi Drive", "Toh Yi Garden", "Toh Yi Road", "Tomlinson Road", "Tong Lee Road", "Tong Watt Road", "Topaz Road", "Toronto Road", "Tosca Street", "Tosca Terrace", "Towner Road", "Townshend Road", "Tractor Road", "Trafalgar Street", "Transit Road", "Tras Street", "Trengganu Street", "Trevose Crescent", "Trevose Place", "Tronoh Road", "Truro Road", "Tu Fu Avenue", "Tua Kong Green", "Tua Kong Place", "Tua Kong Terrace", "Tua Kong Walk", "Tuah Road", "Tuas Avenue 1", "Tuas Avenue 10", "Tuas Avenue 11", "Tuas Avenue 12", "Tuas Avenue 13", "Tuas Avenue 14", "Tuas Avenue 16", "Tuas Avenue 18", "Tuas Avenue 18A", "Tuas Avenue 2", "Tuas Avenue 20", "Tuas Avenue 3", "Tuas Avenue 4", "Tuas Avenue 5", "Tuas Avenue 6", "Tuas Avenue 7", "Tuas Avenue 8", "Tuas Avenue 9", "Tuas Basin Close", "Tuas Basin Link", "Tuas Crescent", "Tuas Drive 1", "Tuas Drive 2", "Tuas Lane", "Tuas Link 1", "Tuas Link 2", "Tuas Link 3", "Tuas Link 4", "Tuas Road", "Tuas South Avenue 1", "Tuas South Avenue 2", "Tuas South Avenue 3", "Tuas South Avenue 4", "Tuas South Avenue 5", "Tuas South Avenue 6", "Tuas South Avenue 7", "Tuas South Avenue 8", "Tuas South Avenue 9", "Tuas South Street 1", "Tuas South Street 2", "Tuas South Street 3", "Tuas South Street 5", "Tuas South Street 6", "Tuas South Street 7", "Tuas Street", "Tuas View Link", "Tuas View Place", "Tuas West Avenue", "Tuas West Drice", "Tuas West Road", "Tudor Close", "Tung Po Avenue", "Turf Club Avenue", "Turf Club Road", "Turnhouse Road", "Turut Track", "Tyersall Avenue", "Tyersall Road", "Tyrwhitt Road", "", "Ubi Avenue 1", "Ubi Avenue 2", "Ubi Avenue 3", "Ubi Avenue 4", "Ubi Close", "Ubi Road 1", "Ubi Road 2", "Ubi Road 3", "Ubi Road 4", "Ulu Pandan Road", "Unity Street", "University Road", "University Walk", "Upavon Road", "Upper Aljunied Lane", "Upper Aljunied Link", "Upper Aljunied Road", "Upper Bedok Road", "Upper Boon Keng Road", "Upper Bukit Timah Road", "Upper Changi Road East", "Upper Changi Road North", "Upper Changi Road Track 39", "Upper Changi Road", "Upper Circular Road", "Upper Cross Street", "Upper Dickson Road", "Upper East Coast Road", "Upper Hokien Street", "Upper Jurong Road", "Upper Neram Road", "Upper Paya Lebar Road", "Upper Pickering Street", "Upper Ring Road", "Upper Serangoon Road", "Upper Thomson Road", "Upper Toh Tuck Terrace", "Upper Weld Road", "Upper Wilkie Road", "", "Valley Road", "Vanda Avenue", "Vanda Crescent", "Vanda Drive", "Vanda Link", "Vanda Road", "Vaughan Road", "Veeragoo Close", "Veerasamy Road", "Venus Drive", "Venus Road", "Verdun Road", "Vernon Park", "Victoria Lane", "Victoria Park Close", "Victoria Park Road", "Victoria Street", "View Road", "Vigilante Drive", "Viking Road", "Vista Terrace", "", "Wajek Walk", "Wallace Way", "Wallich Street", "Walmer Drive", "Walshe Road", "Walton Road", "Wan Lee Road", "Wan Shih Road", "Wan Tho Avenue", "Wareham Road", "Waringin Park", "Waringin Walk", "Warwick Road", "Waterloo Street", "Watten Close", "Watten Drive", "Watten Estate Road", "Watten Estate", "Watten Heights", "Watten Park", "Watten Rise", "Watten Terrace", "Watten View", "Wee Nam Road", "Weld Road", "Wellington Circle", "Wellington Link", "Wellington Road", "Wessex Estate", "West Camp Road", "West Coast Avenue", "West Coast Crescent", "West Coast Drive", "West Coast Ferry Road", "West Coast Green", "West Coast Grove", "West Coast Highway", "West Coast Lane", "West Coast Link", "West Coast Park", "West Coast Place", "West Coast Rise", "West Coast Road", "West Coast Terrace", "West Coast View", "West Coast Way", "West Perimeter Road", "Westbourne Road", "Westerhout Road", "Western Avenue", "Westlake Avenue", "Westridge Walk", "Westwood Avenue", "Westwood Crescent", "Westwood Road", "Westwood Terrace", "Westwood Walk", "Weyhill Close", "Whampoa Drive", "Whampoa East", "Whampoa North", "Whampoa Road", "Whampoa South", "Whampoa West", "Whitchurch Road", "White House Park", "White House Road", "Whitley Heights", "Whitley Road", "Wholesale Centre", "Wilby Road", "Wilkie Road", "Wilkie Terrace", "Wilkinson Road", "Willow Avenue", "Wilmonar Avenue", "Wilton Close", "Wilton Gardens", "Wiltshire Road", "Wimborne Road", "Winchester Road", "Windsor Park Estate", "Windsor Park Road", "Winsor Park Hill", "Winstedt Drive", "Winstedt Road", "Wishart Road", "Wittering Road", "Woking Road", "Wolskel Road", "Wong Chin Yoke Road", "Woo Mon Chew Road", "Woodgrove Avenue", "Woodgrove Drive", "Woodgrove Estate", "Woodlands Avenue 1", "Woodlands Avenue 2", "Woodlands Avenue 3", "Woodlands Avenue 4", "Woodlands Avenue 5", "Woodlands Avenue 6", "Woodlands Avenue 7", "Woodlands Avenue 8", "Woodlands Avenue 9", "Woodlands Centre Road", "Woodlands Circle", "Woodlands Close", "Woodlands Crescent", "Woodlands Drive 14", "Woodlands Drive 16", "Woodlands Drive 40", "Woodlands Drive 42", "Woodlands Drive 43", "Woodlands Drive 44", "Woodlands Drive 50", "Woodlands Drive 52", "Woodlands Drive 53", "Woodlands Drive 60", "Woodlands Drive 61", "Woodlands Drive 62", "Woodlands Drive 63", "Woodlands Drive 65", "Woodlands Drive 70", "Woodlands Drive 71", "Woodlands Drive 72", "Woodlands Drive 73", "Woodlands Industrial Park D", "Woodlands Industrial Park E", "Woodlands Industrial Park E1", "Woodlands Industrial Park E2", "Woodlands Industrial Park E3", "Woodlands Industrial Park E4", "Woodlands Industrial Park E5", "Woodlands Industrial Park E6", "Woodlands Industrial Park E7", "Woodlands Industrial Park E8", "Woodlands Industrial Park E9", "Woodlands Link", "Woodlands Loop", "Woodlands Ring Road", "Woodlands Road", "Woodlands Sector 1", "Woodlands Sector 3", "Woodlands Square", "Woodlands Street 11", "Woodlands Street 12", "Woodlands Street 13", "Woodlands Street 31", "Woodlands Street 32", "Woodlands Street 41", "Woodlands Street 81", "Woodlands Street 82", "Woodlands Street 83", "Woodlands Terrace", "Woodlands Walk", "Woodleigh Close", "Woodleigh Park", "Woodsville Close", "Woollerton Drive", "Woollerton Park", "Woolwich Road", "Worcester Road", "Worthing Road", "", "Xilin Avenue", "", "Yan Kit Road", "Yarrow Gardens", "Yarwood Avenue", "Yee Tee Industrial Estate", "Yew Siang Road", "Yew Tee Close", "Yio Chu Kang Gardens", "Yio Chu Kang Link", "Yio Chu Kang Road", "Yio Chu Kang Terrace", "Yio Chu Kang Track 14", "Yishun Avenue 1", "Yishun Avenue 11", "Yishun Avenue 2", "Yishun Avenue 3", "Yishun Avenue 4", "Yishun Avenue 5", "Yishun Avenue 6", "Yishun Avenue 7", "Yishun Avenue 9", "Yishun Central 1", "Yishun Central 2", "Yishun Central", "Yishun Industrial Park A", "Yishun Northview Drive", "Yishun Ring Road", "Yishun Street 11", "Yishun Street 20", "Yishun Street 21", "Yishun Street 22", "Yishun Street 61", "Yishun Street 71", "Yishun Street 72", "Yishun Street 81", "Yong Siak Street", "York Hill", "York Place", "York Road", "Youngberg Terrace", "Yuan Ching Road", "Yuk Tong Avenue", "Yung An Road", "Yung Ho Road", "Yung Kuang Road", "Yung Loh Road", "Yung Ping Road", "Yung Sheng Road", "Yunnan Crescent", "Yunnan Drive", "Yunnan Road", "Yunnan Walk", "Zehnder Road", "Zion Close", "Zion Road"};
}