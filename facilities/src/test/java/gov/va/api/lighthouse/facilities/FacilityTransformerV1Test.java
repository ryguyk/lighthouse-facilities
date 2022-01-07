package gov.va.api.lighthouse.facilities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import gov.va.api.lighthouse.facilities.api.cms.DetailedService;
import gov.va.api.lighthouse.facilities.api.v1.Facility;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class FacilityTransformerV1Test {
  private DatamartFacility datamartFacility() {
    return DatamartFacility.builder()
        .id("vha_123GA")
        .type(DatamartFacility.Type.va_facilities)
        .attributes(
            DatamartFacility.FacilityAttributes.builder()
                .facilityType(DatamartFacility.FacilityType.va_health_facility)
                .address(
                    DatamartFacility.Addresses.builder()
                        .mailing(
                            DatamartFacility.Address.builder()
                                .address1("505 N John Rodes Blvd")
                                .city("Melbourne")
                                .state("FL")
                                .zip("32934")
                                .build())
                        .physical(
                            DatamartFacility.Address.builder()
                                .address1("505 N John Rodes Blvd")
                                .city("Melbourne")
                                .state("FL")
                                .zip("32934")
                                .build())
                        .build())
                .hours(
                    DatamartFacility.Hours.builder()
                        .sunday("Closed")
                        .monday("9AM-5PM")
                        .tuesday("9AM-5PM")
                        .wednesday("9AM-5PM")
                        .thursday("9AM-5PM")
                        .friday("9AM-5PM")
                        .saturday("Closed")
                        .build())
                .latitude(BigDecimal.valueOf(99.99))
                .longitude(BigDecimal.valueOf(123.45))
                .name("test_name")
                .phone(
                    DatamartFacility.Phone.builder()
                        .main("202-555-1212")
                        .pharmacy("202-555-1213")
                        .patientAdvocate("202-555-1214")
                        .fax("202-555-1215")
                        .afterHours("202-555-1216")
                        .mentalHealthClinic("202-555-1217")
                        .enrollmentCoordinator("202-555-1218")
                        .build())
                .website("http://test.facilities.website.gov")
                .classification("test_classification")
                .timeZone("America/New_York")
                .mobile(false)
                .services(
                    DatamartFacility.Services.builder()
                        .benefits(
                            List.of(
                                DatamartFacility.BenefitsService.EducationClaimAssistance,
                                DatamartFacility.BenefitsService.FamilyMemberClaimAssistance))
                        .other(List.of(DatamartFacility.OtherService.OnlineScheduling))
                        .health(
                            List.of(
                                DatamartFacility.HealthService.PrimaryCare,
                                DatamartFacility.HealthService.UrgentCare,
                                DatamartFacility.HealthService.EmergencyCare))
                        .lastUpdated(LocalDate.parse("2018-01-01"))
                        .build())
                .activeStatus(DatamartFacility.ActiveStatus.A)
                .visn("20")
                .satisfaction(
                    DatamartFacility.Satisfaction.builder()
                        .health(
                            DatamartFacility.PatientSatisfaction.builder()
                                .primaryCareRoutine(BigDecimal.valueOf(0.85))
                                .primaryCareUrgent(BigDecimal.valueOf(0.86))
                                .specialtyCareRoutine(BigDecimal.valueOf(0.87))
                                .specialtyCareUrgent(BigDecimal.valueOf(0.88))
                                .build())
                        .effectiveDate(LocalDate.parse("2018-02-01"))
                        .build())
                .waitTimes(
                    DatamartFacility.WaitTimes.builder()
                        .health(
                            List.of(
                                DatamartFacility.PatientWaitTime.builder()
                                    .service(DatamartFacility.HealthService.Cardiology)
                                    .establishedPatientWaitTime(BigDecimal.valueOf(5))
                                    .newPatientWaitTime(BigDecimal.valueOf(10))
                                    .build(),
                                DatamartFacility.PatientWaitTime.builder()
                                    .service(DatamartFacility.HealthService.Covid19Vaccine)
                                    .establishedPatientWaitTime(BigDecimal.valueOf(4))
                                    .newPatientWaitTime(BigDecimal.valueOf(9))
                                    .build()))
                        .effectiveDate(LocalDate.parse("2018-03-05"))
                        .build())
                .operatingStatus(
                    DatamartFacility.OperatingStatus.builder()
                        .code(DatamartFacility.OperatingStatusCode.NORMAL)
                        .additionalInfo("additional operating status info")
                        .build())
                .detailedServices(
                    List.of(
                        DetailedService.builder()
                            .active(true)
                            .name("COVID-19 vaccines")
                            .path("https://www.melbourne.va.gov/services/covid-19-vaccines.asp")
                            .phoneNumbers(
                                List.of(
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("937-268-6511")
                                        .label("Main phone")
                                        .type("tel")
                                        .extension("71234")
                                        .build(),
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("321-213-4253")
                                        .label("After hours phone")
                                        .type("tel")
                                        .extension("12345")
                                        .build()))
                            .walkInsAccepted("true")
                            .referralRequired("false")
                            .appointmentLeadIn(
                                "Your VA health care team will contact you if you???re eligible to get a vaccine "
                                    + "during this time. As the supply of vaccine increases, we'll work with our care "
                                    + "teams to let Veterans know their options.")
                            .descriptionFacility("facility description")
                            .onlineSchedulingAvailable("true")
                            .serviceLocations(
                                List.of(
                                    DetailedService.DetailedServiceLocation.builder()
                                        .additionalHoursInfo(
                                            "Location hours times may vary depending on staff availability")
                                        .facilityServiceHours(
                                            DetailedService.DetailedServiceHours.builder()
                                                .sunday("Closed")
                                                .monday("9AM-5PM")
                                                .tuesday("9AM-5PM")
                                                .wednesday("9AM-5PM")
                                                .thursday("9AM-5PM")
                                                .friday("9AM-5PM")
                                                .saturday("Closed")
                                                .build())
                                        .emailContacts(
                                            List.of(
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("georgea@va.gov")
                                                    .emailLabel("George Anderson")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("john.doe@va.gov")
                                                    .emailLabel("John Doe")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("jane.doe@va.gov")
                                                    .emailLabel("Jane Doe")
                                                    .build()))
                                        .appointmentPhoneNumbers(
                                            List.of(
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("932-934-6731")
                                                    .type("tel")
                                                    .label("Main Phone")
                                                    .extension("3245")
                                                    .build(),
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("956-862-6651")
                                                    .type("mobile")
                                                    .label("Mobile phone")
                                                    .build()))
                                        .serviceLocationAddress(
                                            DetailedService.DetailedServiceAddress.builder()
                                                .address1("50 Irving Street, Northwest")
                                                .buildingNameNumber("Baxter Building")
                                                .city("Washington")
                                                .state("DC")
                                                .zipCode("20422-0001")
                                                .countryCode("US")
                                                .clinicName("Baxter Clinic")
                                                .wingFloorOrRoomNumber("Wing East")
                                                .build())
                                        .build()))
                            .changed("2021-02-04T22:36:49+00:00")
                            .build(),
                        DetailedService.builder()
                            .active(true)
                            .name("Cardiology")
                            .path("https://www.melbourne.va.gov/services/cardiology.asp")
                            .phoneNumbers(
                                List.of(
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("924-268-4253")
                                        .label("Main phone")
                                        .type("tel")
                                        .extension("71432")
                                        .build(),
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("321-726-6526")
                                        .label("After hours phone")
                                        .type("tel")
                                        .extension("17525")
                                        .build()))
                            .walkInsAccepted("true")
                            .referralRequired("false")
                            .appointmentLeadIn(
                                "Do not consume caffeinated beverages 24 hours prior to your appointment.")
                            .descriptionFacility("cardiology facility description")
                            .onlineSchedulingAvailable("true")
                            .serviceLocations(
                                List.of(
                                    DetailedService.DetailedServiceLocation.builder()
                                        .additionalHoursInfo(
                                            "Location hours times may vary depending on staff availability")
                                        .facilityServiceHours(
                                            DetailedService.DetailedServiceHours.builder()
                                                .sunday("Closed")
                                                .monday("9AM-5PM")
                                                .tuesday("9AM-5PM")
                                                .wednesday("9AM-5PM")
                                                .thursday("9AM-5PM")
                                                .friday("9AM-5PM")
                                                .saturday("Closed")
                                                .build())
                                        .emailContacts(
                                            List.of(
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("georgea@va.gov")
                                                    .emailLabel("George Anderson")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("john.doe@va.gov")
                                                    .emailLabel("John Doe")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("jane.doe@va.gov")
                                                    .emailLabel("Jane Doe")
                                                    .build()))
                                        .appointmentPhoneNumbers(
                                            List.of(
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("932-934-6731")
                                                    .type("tel")
                                                    .label("Main Phone")
                                                    .extension("3245")
                                                    .build(),
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("956-862-6651")
                                                    .type("mobile")
                                                    .label("Mobile phone")
                                                    .build()))
                                        .serviceLocationAddress(
                                            DetailedService.DetailedServiceAddress.builder()
                                                .address1("2513 Irving Street, Northwest")
                                                .buildingNameNumber("Baxter Building")
                                                .city("Washington")
                                                .state("DC")
                                                .zipCode("20422-0001")
                                                .countryCode("US")
                                                .clinicName("Walter Read Medical Facility")
                                                .wingFloorOrRoomNumber("Wing East")
                                                .build())
                                        .build()))
                            .changed("2021-02-04T22:36:49+00:00")
                            .build()))
                .operationalHoursSpecialInstructions(
                    "Vet center 1 is available. | Vet center 2 is available. | Vet center 3 is available.")
                .build())
        .build();
  }

  @Test
  public void datamartFacilityRoundtrip() {
    DatamartFacility datamartFacility = datamartFacility();
    assertThat(
            FacilityTransformerV1.toVersionAgnostic(
                FacilityTransformerV1.toFacility(datamartFacility)))
        .usingRecursiveComparison()
        .isEqualTo(datamartFacility);
  }

  private Facility facility() {
    return Facility.builder()
        .id("vha_123GA")
        .type(Facility.Type.va_facilities)
        .attributes(
            Facility.FacilityAttributes.builder()
                .facilityType(Facility.FacilityType.va_health_facility)
                .address(
                    Facility.Addresses.builder()
                        .mailing(
                            Facility.Address.builder()
                                .address1("505 N John Rodes Blvd")
                                .city("Melbourne")
                                .state("FL")
                                .zip("32934")
                                .build())
                        .physical(
                            Facility.Address.builder()
                                .address1("505 N John Rodes Blvd")
                                .city("Melbourne")
                                .state("FL")
                                .zip("32934")
                                .build())
                        .build())
                .hours(
                    Facility.Hours.builder()
                        .sunday("Closed")
                        .monday("9AM-5PM")
                        .tuesday("9AM-5PM")
                        .wednesday("9AM-5PM")
                        .thursday("9AM-5PM")
                        .friday("9AM-5PM")
                        .saturday("Closed")
                        .build())
                .latitude(BigDecimal.valueOf(99.99))
                .longitude(BigDecimal.valueOf(123.45))
                .name("test_name")
                .phone(
                    Facility.Phone.builder()
                        .main("202-555-1212")
                        .pharmacy("202-555-1213")
                        .patientAdvocate("202-555-1214")
                        .fax("202-555-1215")
                        .afterHours("202-555-1216")
                        .mentalHealthClinic("202-555-1217")
                        .enrollmentCoordinator("202-555-1218")
                        .build())
                .website("http://test.facilities.website.gov")
                .classification("test_classification")
                .timeZone("America/New_York")
                .mobile(false)
                .services(
                    Facility.Services.builder()
                        .benefits(
                            List.of(
                                Facility.BenefitsService.EducationClaimAssistance,
                                Facility.BenefitsService.FamilyMemberClaimAssistance))
                        .other(List.of(Facility.OtherService.OnlineScheduling))
                        .health(
                            List.of(
                                Facility.HealthService.PrimaryCare,
                                Facility.HealthService.UrgentCare,
                                Facility.HealthService.EmergencyCare))
                        .lastUpdated(LocalDate.parse("2018-01-01"))
                        .build())
                .activeStatus(Facility.ActiveStatus.A)
                .visn("20")
                .satisfaction(
                    Facility.Satisfaction.builder()
                        .health(
                            Facility.PatientSatisfaction.builder()
                                .primaryCareRoutine(BigDecimal.valueOf(0.85))
                                .primaryCareUrgent(BigDecimal.valueOf(0.86))
                                .specialtyCareRoutine(BigDecimal.valueOf(0.87))
                                .specialtyCareUrgent(BigDecimal.valueOf(0.88))
                                .build())
                        .effectiveDate(LocalDate.parse("2018-02-01"))
                        .build())
                .waitTimes(
                    Facility.WaitTimes.builder()
                        .health(
                            List.of(
                                Facility.PatientWaitTime.builder()
                                    .service(Facility.HealthService.Cardiology)
                                    .establishedPatientWaitTime(BigDecimal.valueOf(5))
                                    .newPatientWaitTime(BigDecimal.valueOf(10))
                                    .build(),
                                Facility.PatientWaitTime.builder()
                                    .service(Facility.HealthService.Covid19Vaccine)
                                    .establishedPatientWaitTime(BigDecimal.valueOf(4))
                                    .newPatientWaitTime(BigDecimal.valueOf(9))
                                    .build()))
                        .effectiveDate(LocalDate.parse("2018-03-05"))
                        .build())
                .operatingStatus(
                    Facility.OperatingStatus.builder()
                        .code(Facility.OperatingStatusCode.NORMAL)
                        .additionalInfo("additional operating status info")
                        .build())
                .detailedServices(
                    List.of(
                        DetailedService.builder()
                            .active(true)
                            .name("COVID-19 vaccines")
                            .path("https://www.melbourne.va.gov/services/covid-19-vaccines.asp")
                            .phoneNumbers(
                                List.of(
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("937-268-6511")
                                        .label("Main phone")
                                        .type("tel")
                                        .extension("71234")
                                        .build(),
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("321-213-4253")
                                        .label("After hours phone")
                                        .type("tel")
                                        .extension("12345")
                                        .build()))
                            .walkInsAccepted("true")
                            .referralRequired("false")
                            .appointmentLeadIn(
                                "Your VA health care team will contact you if you???re eligible to get a vaccine "
                                    + "during this time. As the supply of vaccine increases, we'll work with our care "
                                    + "teams to let Veterans know their options.")
                            .descriptionFacility("facility description")
                            .onlineSchedulingAvailable("true")
                            .serviceLocations(
                                List.of(
                                    DetailedService.DetailedServiceLocation.builder()
                                        .additionalHoursInfo(
                                            "Location hours times may vary depending on staff availability")
                                        .facilityServiceHours(
                                            DetailedService.DetailedServiceHours.builder()
                                                .sunday("Closed")
                                                .monday("9AM-5PM")
                                                .tuesday("9AM-5PM")
                                                .wednesday("9AM-5PM")
                                                .thursday("9AM-5PM")
                                                .friday("9AM-5PM")
                                                .saturday("Closed")
                                                .build())
                                        .emailContacts(
                                            List.of(
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("georgea@va.gov")
                                                    .emailLabel("George Anderson")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("john.doe@va.gov")
                                                    .emailLabel("John Doe")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("jane.doe@va.gov")
                                                    .emailLabel("Jane Doe")
                                                    .build()))
                                        .appointmentPhoneNumbers(
                                            List.of(
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("932-934-6731")
                                                    .type("tel")
                                                    .label("Main Phone")
                                                    .extension("3245")
                                                    .build(),
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("956-862-6651")
                                                    .type("mobile")
                                                    .label("Mobile phone")
                                                    .build()))
                                        .serviceLocationAddress(
                                            DetailedService.DetailedServiceAddress.builder()
                                                .address1("50 Irving Street, Northwest")
                                                .buildingNameNumber("Baxter Building")
                                                .city("Washington")
                                                .state("DC")
                                                .zipCode("20422-0001")
                                                .countryCode("US")
                                                .clinicName("Baxter Clinic")
                                                .wingFloorOrRoomNumber("Wing East")
                                                .build())
                                        .build()))
                            .changed("2021-02-04T22:36:49+00:00")
                            .build(),
                        DetailedService.builder()
                            .active(true)
                            .name("Cardiology")
                            .path("https://www.melbourne.va.gov/services/cardiology.asp")
                            .phoneNumbers(
                                List.of(
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("924-268-4253")
                                        .label("Main phone")
                                        .type("tel")
                                        .extension("71432")
                                        .build(),
                                    DetailedService.AppointmentPhoneNumber.builder()
                                        .number("321-726-6526")
                                        .label("After hours phone")
                                        .type("tel")
                                        .extension("17525")
                                        .build()))
                            .walkInsAccepted("true")
                            .referralRequired("false")
                            .appointmentLeadIn(
                                "Do not consume caffeinated beverages 24 hours prior to your appointment.")
                            .descriptionFacility("cardiology facility description")
                            .onlineSchedulingAvailable("true")
                            .serviceLocations(
                                List.of(
                                    DetailedService.DetailedServiceLocation.builder()
                                        .additionalHoursInfo(
                                            "Location hours times may vary depending on staff availability")
                                        .facilityServiceHours(
                                            DetailedService.DetailedServiceHours.builder()
                                                .sunday("Closed")
                                                .monday("9AM-5PM")
                                                .tuesday("9AM-5PM")
                                                .wednesday("9AM-5PM")
                                                .thursday("9AM-5PM")
                                                .friday("9AM-5PM")
                                                .saturday("Closed")
                                                .build())
                                        .emailContacts(
                                            List.of(
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("georgea@va.gov")
                                                    .emailLabel("George Anderson")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("john.doe@va.gov")
                                                    .emailLabel("John Doe")
                                                    .build(),
                                                DetailedService.DetailedServiceEmailContact
                                                    .builder()
                                                    .emailAddress("jane.doe@va.gov")
                                                    .emailLabel("Jane Doe")
                                                    .build()))
                                        .appointmentPhoneNumbers(
                                            List.of(
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("932-934-6731")
                                                    .type("tel")
                                                    .label("Main Phone")
                                                    .extension("3245")
                                                    .build(),
                                                DetailedService.AppointmentPhoneNumber.builder()
                                                    .number("956-862-6651")
                                                    .type("mobile")
                                                    .label("Mobile phone")
                                                    .build()))
                                        .serviceLocationAddress(
                                            DetailedService.DetailedServiceAddress.builder()
                                                .address1("2513 Irving Street, Northwest")
                                                .buildingNameNumber("Baxter Building")
                                                .city("Washington")
                                                .state("DC")
                                                .zipCode("20422-0001")
                                                .countryCode("US")
                                                .clinicName("Walter Read Medical Facility")
                                                .wingFloorOrRoomNumber("Wing East")
                                                .build())
                                        .build()))
                            .changed("2021-02-04T22:36:49+00:00")
                            .build()))
                .operationalHoursSpecialInstructions(
                    new ArrayList<String>() {
                      {
                        add("Vet center 1 is available.");
                        add("Vet center 2 is available.");
                        add("Vet center 3 is available.");
                      }
                    })
                .build())
        .build();
  }

  @Test
  public void facilityRoundtrip() {
    Facility facility = facility();
    assertThat(FacilityTransformerV1.toFacility(FacilityTransformerV1.toVersionAgnostic(facility)))
        .usingRecursiveComparison()
        .isEqualTo(facility);
  }

  @Test
  public void facilityVisitorRoundtrip() {
    Facility facility = facility();
    assertThat(
            FacilityTransformerV1.toFacility(
                FacilityTransformerV0.toVersionAgnostic(
                    FacilityTransformerV0.toFacility(
                        FacilityTransformerV1.toVersionAgnostic(facility)))))
        .usingRecursiveComparison()
        .isEqualTo(facility);
  }

  @Test
  @SneakyThrows
  public void nullArgs() {
    assertThrows(NullPointerException.class, () -> FacilityTransformerV1.toFacility(null));
    assertThrows(NullPointerException.class, () -> FacilityTransformerV1.toVersionAgnostic(null));

    final Method transformDatmartFacilityBenefitsServiceMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityBenefitsService", DatamartFacility.BenefitsService.class);
    transformDatmartFacilityBenefitsServiceMethod.setAccessible(true);
    DatamartFacility.BenefitsService nullBenefits = null;
    assertThrows(
        InvocationTargetException.class,
        () -> transformDatmartFacilityBenefitsServiceMethod.invoke(null, nullBenefits));
    final Method transformFacilityBenefitsServiceMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityBenefitsService", Facility.BenefitsService.class);
    transformFacilityBenefitsServiceMethod.setAccessible(true);
    Facility.BenefitsService nullBenefitsV1 = null;
    assertThrows(
        InvocationTargetException.class,
        () -> transformFacilityBenefitsServiceMethod.invoke(null, nullBenefitsV1));

    final Method transformDatmartFacilityHealthServiceMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityHealthService", DatamartFacility.HealthService.class);
    transformDatmartFacilityHealthServiceMethod.setAccessible(true);
    DatamartFacility.HealthService nullHealth = null;
    assertThrows(
        InvocationTargetException.class,
        () -> transformDatmartFacilityHealthServiceMethod.invoke(null, nullHealth));
    final Method transformFacilityHealthServiceMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityHealthService", Facility.HealthService.class);
    transformFacilityHealthServiceMethod.setAccessible(true);
    Facility.HealthService nullHealthV1 = null;
    assertThrows(
        InvocationTargetException.class,
        () -> transformFacilityHealthServiceMethod.invoke(null, nullHealthV1));

    final Method transformDatmartFacilityServicesMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityServices", DatamartFacility.Services.class);
    transformDatmartFacilityServicesMethod.setAccessible(true);
    DatamartFacility.Services nullServices = null;
    assertThat(transformDatmartFacilityServicesMethod.invoke(null, nullServices))
        .usingRecursiveComparison()
        .isEqualTo(Facility.Services.builder().build());
    final Method transformFacilityServicesMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityServices", Facility.Services.class);
    transformFacilityServicesMethod.setAccessible(true);
    Facility.Services nullServicesV1 = null;
    assertThat(transformFacilityServicesMethod.invoke(null, nullServicesV1))
        .usingRecursiveComparison()
        .isEqualTo(DatamartFacility.Services.builder().build());

    final Method transformDatmartFacilitySatisfactionMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilitySatisfaction", DatamartFacility.Satisfaction.class);
    transformDatmartFacilitySatisfactionMethod.setAccessible(true);
    DatamartFacility.Satisfaction nullSatisfaction = null;
    assertThat(transformDatmartFacilitySatisfactionMethod.invoke(null, nullSatisfaction))
        .usingRecursiveComparison()
        .isEqualTo(Facility.Satisfaction.builder().build());
    final Method transformFacilitySatisfactionMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilitySatisfaction", Facility.Satisfaction.class);
    transformFacilitySatisfactionMethod.setAccessible(true);
    Facility.Satisfaction nullSatisfactionV1 = null;
    assertThat(transformFacilitySatisfactionMethod.invoke(null, nullSatisfactionV1))
        .usingRecursiveComparison()
        .isEqualTo(DatamartFacility.Satisfaction.builder().build());

    final Method transformDatmartFacilityPhoneMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityPhone", DatamartFacility.Phone.class);
    transformDatmartFacilityPhoneMethod.setAccessible(true);
    DatamartFacility.Satisfaction nullPhone = null;
    assertThat(transformDatmartFacilityPhoneMethod.invoke(null, nullPhone))
        .usingRecursiveComparison()
        .isEqualTo(Facility.Phone.builder().build());
    final Method transformFacilityPhoneMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityPhone", Facility.Phone.class);
    transformFacilityPhoneMethod.setAccessible(true);
    Facility.Satisfaction nullPhoneV1 = null;
    assertThat(transformFacilityPhoneMethod.invoke(null, nullPhoneV1))
        .usingRecursiveComparison()
        .isEqualTo(DatamartFacility.Phone.builder().build());

    final Method transformDatmartFacilityHoursMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityHours", DatamartFacility.Hours.class);
    transformDatmartFacilityHoursMethod.setAccessible(true);
    DatamartFacility.Hours nullHours = null;
    assertThat(transformDatmartFacilityHoursMethod.invoke(null, nullHours))
        .usingRecursiveComparison()
        .isEqualTo(Facility.Hours.builder().build());
    final Method transformFacilityHoursMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityHours", Facility.Hours.class);
    transformFacilityHoursMethod.setAccessible(true);
    Facility.Hours nullHoursV1 = null;
    assertThat(transformFacilityHoursMethod.invoke(null, nullHoursV1))
        .usingRecursiveComparison()
        .isEqualTo(DatamartFacility.Hours.builder().build());

    final Method transformDatmartFacilityAddressesMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityAddresses", DatamartFacility.Addresses.class);
    transformDatmartFacilityAddressesMethod.setAccessible(true);
    DatamartFacility.Addresses nullAddresses = null;
    assertThat(transformDatmartFacilityAddressesMethod.invoke(null, nullAddresses))
        .usingRecursiveComparison()
        .isEqualTo(Facility.Addresses.builder().build());
    final Method transformFacilityAddressesMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityAddresses", Facility.Addresses.class);
    transformFacilityAddressesMethod.setAccessible(true);
    Facility.Addresses nullAddressesV1 = null;
    assertThat(transformFacilityAddressesMethod.invoke(null, nullAddressesV1))
        .usingRecursiveComparison()
        .isEqualTo(DatamartFacility.Addresses.builder().build());

    final Method transformDatmartFacilityWaitTimesMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityWaitTimes", DatamartFacility.WaitTimes.class);
    transformDatmartFacilityWaitTimesMethod.setAccessible(true);
    DatamartFacility.WaitTimes nullWaitTimes = null;
    assertThat(transformDatmartFacilityWaitTimesMethod.invoke(null, nullWaitTimes))
        .usingRecursiveComparison()
        .isEqualTo(Facility.WaitTimes.builder().build());
    final Method transformFacilityWaitTimesMethod =
        FacilityTransformerV1.class.getDeclaredMethod(
            "transformFacilityWaitTimes", Facility.WaitTimes.class);
    transformFacilityWaitTimesMethod.setAccessible(true);
    Facility.WaitTimes nullWaitTimesV1 = null;
    assertThat(transformFacilityWaitTimesMethod.invoke(null, nullWaitTimesV1))
        .usingRecursiveComparison()
        .isEqualTo(DatamartFacility.WaitTimes.builder().build());
  }

  @Test
  public void transformDatamartFacility() {
    Facility expected = facility();
    DatamartFacility datamartFacility = datamartFacility();
    assertThat(FacilityTransformerV1.toFacility(datamartFacility))
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  @Test
  public void transformEmptyFacility() {
    Facility facility = Facility.builder().build();
    DatamartFacility datamartFacility = DatamartFacility.builder().build();
    assertThat(FacilityTransformerV1.toVersionAgnostic(facility))
        .usingRecursiveComparison()
        .isEqualTo(datamartFacility);
    assertThat(FacilityTransformerV1.toFacility(datamartFacility))
        .usingRecursiveComparison()
        .isEqualTo(facility);
  }

  @Test
  public void transformFacility() {
    DatamartFacility expected = datamartFacility();
    Facility facility = facility();
    assertThat(FacilityTransformerV1.toVersionAgnostic(facility))
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  @Test
  public void transformFacilityWithEmptyAttributes() {
    Facility facility =
        Facility.builder().id("vha_123GA").type(Facility.Type.va_facilities).build();
    DatamartFacility datamartFacility =
        DatamartFacility.builder()
            .id("vha_123GA")
            .type(DatamartFacility.Type.va_facilities)
            .build();
    assertThat(FacilityTransformerV1.toVersionAgnostic(facility))
        .usingRecursiveComparison()
        .isEqualTo(datamartFacility);
    assertThat(FacilityTransformerV1.toFacility(datamartFacility))
        .usingRecursiveComparison()
        .isEqualTo(facility);
  }
}
