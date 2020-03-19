package gov.va.api.lighthouse.facilitiescollector;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.ImmutableMap;
import gov.va.api.lighthouse.facilities.api.v0.Facility;
import java.util.Collections;
import org.junit.Test;

public class StateCemeteryTransformerTest {
  @Test
  public void asAddress_parseLine1() {
    assertThat(StateCemeteryTransformer.asAddress("AL", "Spanish Fort, AL, 36577-1234", "", ""))
        .isEqualTo(
            Facility.Address.builder().zip("36577-1234").city("Spanish Fort").state("AL").build());

    assertThat(StateCemeteryTransformer.asAddress("AL", "blah", "", ""))
        .isEqualTo(Facility.Address.builder().address1("blah").build());

    assertThat(StateCemeteryTransformer.asAddress("AL", "", "", "")).isNull();
  }

  @Test
  public void asAddress_parseLine2() {
    assertThat(
            StateCemeteryTransformer.asAddress(
                "AL", "34904 State Highway 225", "Spanish Fort, AL, 365771234", ""))
        .isEqualTo(
            Facility.Address.builder()
                .zip("365771234")
                .city("Spanish Fort")
                .state("AL")
                .address1("34904 State Highway 225")
                .build());

    assertThat(StateCemeteryTransformer.asAddress("AL", "34904 State Highway 225", "blah", ""))
        .isEqualTo(
            Facility.Address.builder().state("AL").address1("34904 State Highway 225").build());

    assertThat(StateCemeteryTransformer.asAddress("AL", "", "blah", "")).isNull();
  }

  @Test
  public void asAddress_parseLine3() {
    assertThat(
            StateCemeteryTransformer.asAddress(
                "AL", "34904 State Highway 225", "blah", "Spanish Fort, AL 36577"))
        .isEqualTo(
            Facility.Address.builder()
                .zip("36577")
                .city("Spanish Fort")
                .state("AL")
                .address1("34904 State Highway 225")
                .address2("blah")
                .build());
    assertThat(StateCemeteryTransformer.asAddress("AL", "34904 State Highway 225", "blah", "blah"))
        .isEqualTo(
            Facility.Address.builder()
                .state("AL")
                .address1("34904 State Highway 225")
                .address2("blah")
                .build());
    assertThat(StateCemeteryTransformer.asAddress("AL", "blah", "blah", "blah"))
        .isEqualTo(
            Facility.Address.builder().state("AL").address1("blah").address2("blah").build());
    assertThat(StateCemeteryTransformer.asAddress("AL", "", "", "blah")).isNull();
  }

  @Test
  public void empty() {
    assertThat(
            StateCemeteryTransformer.builder()
                .xml(StateCemeteries.StateCemetery.builder().build())
                .websites(Collections.emptyMap())
                .build()
                .toFacility())
        .isNull();
    assertThat(
            StateCemeteryTransformer.builder()
                .xml(StateCemeteries.StateCemetery.builder().facId("aBc123").build())
                .websites(Collections.emptyMap())
                .build()
                .toFacility())
        .isEqualTo(Facility.builder().id("nca_saBc123").build());
  }

  @Test
  public void website() {
    assertThat(
            StateCemeteryTransformer.builder()
                .xml(
                    StateCemeteries.StateCemetery.builder()
                        .facId("aBc123")
                        .cemUrl("orig-website")
                        .build())
                .websites(ImmutableMap.of("nca_saBc123", "csv-website"))
                .build()
                .website())
        .isEqualTo("orig-website");

    assertThat(
            StateCemeteryTransformer.builder()
                .xml(StateCemeteries.StateCemetery.builder().facId("abc123").build())
                .websites(ImmutableMap.of("nca_sabc123", "csv-website"))
                .build()
                .website())
        .isEqualTo("csv-website");
  }
}
