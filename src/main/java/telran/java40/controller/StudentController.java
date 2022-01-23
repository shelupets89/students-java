package telran.java40.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.java40.dto.ScoreDTO;
import telran.java40.dto.StudentCredentialDTO;
import telran.java40.dto.StudentDTO;
import telran.java40.dto.UpdateStudentDTO;
import telran.java40.service.StudentService;

@RestController
public class StudentController {
	StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@PostMapping("/student")
	public boolean studentRegister(@RequestBody StudentCredentialDTO studentCredentialDTO) {
		return studentService.addStudent(studentCredentialDTO);
	}

	@GetMapping("/student/{id}")
	public StudentDTO findStudentById(@PathVariable Integer id) {
		return studentService.findStudent(id);
	}

	@DeleteMapping("/student/{id}")
	public StudentDTO removeStudentById(@PathVariable Integer id) {
		return studentService.removeStudent(id);
	}

	@PutMapping("/student/{id}")
	public StudentCredentialDTO updateStudent(@PathVariable Integer id,
			@RequestBody UpdateStudentDTO updateStudentDTO) {
		return studentService.updateStudent(id, updateStudentDTO);
	}

	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDTO score) {
		return studentService.addScore(id, score);
	}

	@GetMapping("/students/name/{name}")
	public List<StudentDTO> findStudentsByName(@PathVariable String name) {
		return studentService.findStudentsByName(name);
	}

	@PostMapping("/quantity/students")
	public long studentsQuantity(@RequestBody List<String> names) {
		return studentService.studentsQuantity(names);
	}

	
	@GetMapping("students/exam/{exam}/minscore/{score}")
	public List<StudentDTO> studentsByExamScore(@PathVariable String exam, @PathVariable int score) {
		return studentService.findStudentsByMinScore(exam, score);
	}

}
