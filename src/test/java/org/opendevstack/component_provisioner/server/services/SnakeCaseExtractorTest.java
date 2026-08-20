package org.opendevstack.component_provisioner.server.services;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SnakeCaseExtractorTest {

    private final SnakeCaseExtractor extractor = new SnakeCaseExtractor();

    @Test
    void givenObjectWithGetters_whenToSnakeCaseMap_thenReturnsSnakeCaseMap() {
        // given
        var obj = new TestObject("John", "Doe");

        // when
        var result = extractor.toSnakeCaseMap(obj);

        // then
        assertThat(result)
                .containsEntry("first_name", "John")
                .containsEntry("last_name", "Doe");
    }

    private static class TestObject {
        private final String firstName;
        private final String lastName;

        public TestObject(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}
