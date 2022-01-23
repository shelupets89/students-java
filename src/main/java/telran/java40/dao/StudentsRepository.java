package telran.java40.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java40.model.Student;

public interface StudentsRepository extends MongoRepository<Student, Integer> {
	Stream<Student> findBy();

	Stream<Student> findByNameIgnoreCase(String name);

	long countByNameInIgnoreCase(List<String> names);
	
	@Query("{'scores.?0' : {$gt: ?1} }")
	Stream<Student>  findByExamAndScoreGreateEqualsThan(String score, int min);
	
	
} 