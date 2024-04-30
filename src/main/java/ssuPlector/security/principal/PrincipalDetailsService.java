package ssuPlector.security.principal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ssuPlector.domain.Developer;
import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.code.GlobalErrorCode;
import ssuPlector.repository.developer.DeveloperRepository;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final DeveloperRepository developerRepository;

    @Override
    public UserDetails loadUserByUsername(String developerId) throws UsernameNotFoundException {
        Developer developer =
                developerRepository
                        .findById(Long.parseLong(developerId))
                        .orElseThrow(
                                () -> new GlobalException(GlobalErrorCode.DEVELOPER_NOT_FOUND));
        return new PrincipalDetails(developer);
    }
}
