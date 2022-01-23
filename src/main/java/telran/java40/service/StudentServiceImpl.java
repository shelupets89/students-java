package telran.java40.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java40.dao.StudentsRepository;
import telran.java40.dto.ScoreDTO;
import telran.java40.dto.StudentCredentialDTO;
import telran.java40.dto.StudentDTO;
import telran.java40.dto.UpdateStudentDTO;
import telran.java40.dto.sxceptions.StudentNotFoundException;
import telran.java40.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	StudentsRepository studentsRepository;
	ModelMapper modelMapper;

	@Autowired
	public StudentServiceImpl(StudentsRepository studentsRepository, ModelMapper modelMapper) {
		super();
		this.studentsRepository = studentsRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean addStudent(StudentCredentialDTO credential) {
		if (studentsRepository.existsById(credential.getId())) {
			return false;
		}
		Student student = modelMapper.map(credential, Student.class);
		studentsRepository.save(student);
		return true;
	}

	@Override
	public StudentDTO findStudent(Integer id) {
		Student student = studentsRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		return modelMapper.map(student, StudentDTO.class);
	}

	@Override
	public StudentDTO removeStudent(Integer id) {
		Student student = studentsRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		studentsRepository.deleteById(id);
		return modelMapper.map(student, StudentDTO.class);
	}

	@Override
	public StudentCredentialDTO updateStudent(Integer id, UpdateStudentDTO credential) {
		Student student = studentsRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		student.setName(credential.getName());
		student.setPassword(credential.getPassword());
		studentsRepository.save(student);
		return modelMapper.map(student, StudentCredentialDTO.class);

	}

	@Override
	public boolean addScore(Integer id, ScoreDTO score) {
		Student student = studentsRepository.findById(id).orElseThrow(StudentNotFoundException::new);
		boolean res = student.addScore(score.getExamName(), score.getScore());
		studentsRepository.save(student);
		return res;
	}

	@Override
	public List<StudentDTO> findStudentsByName(String name) {
		return studentsRepository.findByNameIgnoreCase(name)
				.map(student -> modelMapper.map(student, StudentDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public long studentsQuantity(List<String> names) {
		if (names == null || names.size() == 0) {
			return 0;
		}
		return studentsRepository.countByNameInIgnoreCase(names);
	}

	@Override
	public List<StudentDTO> findStudentsByMinScore(String exam, int min) {

		return studentsRepository
				.findByExamAndScoreGreateEqualsThan(exam, min)
				.map(student -> modelMapper.map(student, StudentDTO.class))
				.collect(Collectors.toList());
	}
}
