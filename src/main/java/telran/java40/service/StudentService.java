package telran.java40.service;

import java.util.List;

import telran.java40.dto.ScoreDTO;
import telran.java40.dto.StudentCredentialDTO;
import telran.java40.dto.StudentDTO;
import telran.java40.dto.UpdateStudentDTO;

public interface StudentService {

	boolean addStudent(StudentCredentialDTO credential);

	StudentDTO findStudent(Integer id);

	StudentDTO removeStudent(Integer id);

	StudentCredentialDTO updateStudent(Integer id, UpdateStudentDTO credential);

	boolean addScore(Integer id, ScoreDTO score);

	List<StudentDTO> findStudentsByName(String name);

	long studentsQuantity(List<String> names);

	List<StudentDTO> findStudentsByMinScore(String exam, int min);

}
