package org.opendevstack.component_provisioner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(value = "testing")
class ComponentProvisionerApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
		// Empty, test will fail only if Spring Boot context load also fails
	}

	@Test
	void givenConfiguredObjectMapper_whenSerializingAndParsingDate_thenUsesUtcRfc3339() throws Exception {
		// given
		var epochDate = new Date(0L);

		// when
		var serializedDate = objectMapper.writeValueAsString(epochDate);
		var parsedDate = objectMapper.readValue("\"1970-01-01T00:00:00.000Z\"", Date.class);

		// then
		assertThat(serializedDate).isEqualTo("\"1970-01-01T00:00:00.000+00:00\"");
		assertThat(parsedDate).isEqualTo(epochDate);
	}

}
