/*
6510450569 Panithan Champahom
*/

package ku.cs.kafe.service;


import ku.cs.kafe.entity.Member;
import ku.cs.kafe.request.SignupRequest;
import ku.cs.kafe.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    // check duplicate is using CONTROLLER to validate, but name, len, null etc., are checking on the VIEW
    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }

    public void createUser(SignupRequest user) {
        Member record = modelMapper.map(user, Member.class);
        record.setRole("ROLE_USER");

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);

        repository.save(record);
    }

    public Member getUser(String username) {
        return repository.findByUsername(username);
    }
}
