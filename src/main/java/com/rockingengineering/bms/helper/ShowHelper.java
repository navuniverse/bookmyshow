/**
 * 
 */
package com.rockingengineering.bms.helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Join;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.rockingengineering.bms.model.MovieEntity;
import com.rockingengineering.bms.model.ShowEntity;
import com.rockingengineering.bms.model.TheaterEntity;

import lombok.experimental.UtilityClass;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
@UtilityClass
public class ShowHelper {

	public static Specification<ShowEntity> createSpecification(String movieName, String city, LocalDate showDate, LocalTime showTime) {

		List<Specification<ShowEntity>> specifications = new ArrayList<>();

		specifications.add(getCurrentAndFutureShowSpec());

		if (StringUtils.isNotBlank(movieName)) {
			specifications.add(getShowByMovieNameSpec(movieName));
		}

		if (StringUtils.isNotBlank(city)) {
			specifications.add(getShowByCitySpec(city));
		}

		if (Objects.nonNull(showDate)) {
			specifications.add(getShowByDateSpec(showDate));
		}

		if (Objects.nonNull(showTime)) {
			specifications.add(getShowByTimeSpec(showTime));
		}

		return createSpecification(specifications);

	}

	private static Specification<ShowEntity> createSpecification(List<Specification<ShowEntity>> specs) {

		Specification<ShowEntity> result = specs.get(0);

		for (int i = 1; i < specs.size(); i++) {
			result = Specification.where(result).and(specs.get(i));
		}

		return result;
	}

	private static Specification<ShowEntity> getCurrentAndFutureShowSpec() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("showDate"), LocalDate.now());
	}

	private static Specification<ShowEntity> getShowByMovieNameSpec(String movieName) {
		return (root, query, criteriaBuilder) -> {
			Join<ShowEntity, MovieEntity> movieJoin = root.join("movie");
			return criteriaBuilder.equal(movieJoin.get("name"), movieName);
		};
	}

	private static Specification<ShowEntity> getShowByCitySpec(String city) {
		return (root, query, criteriaBuilder) -> {
			Join<ShowEntity, TheaterEntity> theaterJoin = root.join("theater");
			return criteriaBuilder.equal(theaterJoin.get("city"), city);
		};
	}

	private static Specification<ShowEntity> getShowByDateSpec(LocalDate showDate) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("showDate"), showDate);
	}

	private static Specification<ShowEntity> getShowByTimeSpec(LocalTime showTime) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("showTime"), showTime);
	}

}