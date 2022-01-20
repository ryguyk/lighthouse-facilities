package gov.va.api.lighthouse.facilities.api.v1.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import gov.va.api.lighthouse.facilities.api.v1.Facility.Phone;
import lombok.SneakyThrows;

public class PhoneSerializer extends NonEmptySerializer<Phone> {

  public PhoneSerializer() {
    this(null);
  }

  public PhoneSerializer(Class<Phone> t) {
    super(t);
  }

  @Override
  @SneakyThrows
  public void serialize(Phone value, JsonGenerator jgen, SerializerProvider provider) {
    jgen.writeStartObject();
    writeNonEmpty(jgen, "fax", value.fax());
    writeNonEmpty(jgen, "main", value.main());
    writeNonEmpty(jgen, "pharmacy", value.pharmacy());
    writeNonEmpty(jgen, "after_hours", value.afterHours());
    writeNonEmpty(jgen, "patient_advocate", value.patientAdvocate());
    writeNonEmpty(jgen, "mental_health_clinic", value.mentalHealthClinic());
    writeNonEmpty(jgen, "enrollment_coordinator", value.enrollmentCoordinator());
    jgen.writeEndObject();
  }
}
