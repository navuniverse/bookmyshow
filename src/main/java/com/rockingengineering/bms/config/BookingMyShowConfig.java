/**
 * 
 */
package com.rockingengineering.bms.config;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author naveen
 *
 * @date 04-Sep-2019
 */
@Configuration
public class BookingMyShowConfig {

	@Bean
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {

		ObjectMapper mapper = builder.createXmlMapper(false).build();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

		SimpleModule module = new SimpleModule();

		module.addSerializer(new Java8LocalDateStdSerializer());
		module.addDeserializer(LocalDate.class, new Java8LocalDateStdDeserializer());

		mapper.registerModule(module);

		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		return mapper;

	}

	class Java8LocalDateStdSerializer extends StdSerializer<LocalDate> {

		private static final long serialVersionUID = 1L;

		public Java8LocalDateStdSerializer() {
			this(LocalDate.class);
		}

		public Java8LocalDateStdSerializer(Class<LocalDate> t) {
			super(t);
		}

		@Override
		public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
			gen.writeObject(value.toString());
		}

	}

	class Java8LocalDateStdDeserializer extends StdDeserializer<LocalDate> {

		private static final long serialVersionUID = 1L;

		public Java8LocalDateStdDeserializer() {
			this(LocalDate.class);
		}

		public Java8LocalDateStdDeserializer(Class<LocalDate> t) {
			super(t);
		}

		@Override
		public LocalDate deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
			return LocalDate.parse(jsonparser.getText());
		}
	}
}